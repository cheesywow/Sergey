package com.sergey.michael.sergey;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sergey.michael.sergey.Engine.Navigation.BottomNavigation;
import com.sergey.michael.sergey.Engine.Util.Toolbox;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    int high_score = 0;
    float speed = 0f;
    float rotation = 0f;

    ImageView img;
    TextView tv_score;
    TextView tv_speed;
    Toolbox toolbox;

    boolean spinning = true;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_view);

        toolbox = new Toolbox(this);
        toolbox.setup_Toolbar(this,R.id.toolbar,R.id.app_bar_layout);
        toolbox.setup_Drawer(this, R.id.nav_view,R.id.drawer_layout,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        BottomNavigation navigation = new BottomNavigation(this,R.id.nav_view);

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        high_score = sharedPref.getInt(getString(R.string.preference_file_key), 0);

        tv_score = findViewById(R.id.tvScore);
        tv_score.setText("Score: "+ high_score);
        tv_speed = findViewById(R.id.tvSpeed);
        tv_speed.setText("Speed: "+ speed);

        img = findViewById(R.id.sergey);
        manipulateImage();
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickSergey();
            }
        });
    }

    @Override
    public void onPause(){
        super.onPause();
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = sharedPref.edit();
        editor.putInt(getString(R.string.preference_file_key), high_score);
        editor.apply();
    }
    @Override
    public void onResume(){
        super.onResume();
        Toolbox.activiateFullscreen(this);
        int defaultValue = 0;
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        high_score = sharedPref.getInt(getString(R.string.preference_file_key), defaultValue);
    }

    @Override
    public void onBackPressed(){
        //super.onBackPressed();
        toolbox.onBackPressed();
    }

    public void clickSergey(){
        high_score +=1;
        if(speed < 5000){
            speed += 50;
        }
        tv_speed.setText("Speed: "+ speed);
        tv_score.setText("Score: "+ high_score);
    }

    public void manipulateImage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(spinning){
                    try {
                        sleep(10);
                        if(speed > 0){
                            rotateImage();
                            speed -= 0.5f;
                        }
                        if(speed != 0 && speed % 200 == 0){
                            MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.sergey_scream);
                            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    mp.release();
                                }
                            });
                            mp.start();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }

    public void rotateImage(){
        rotation += 0.05 + speed/100;
        if(rotation > 360) {
            rotation -= 360;
        }
        MainActivity.this.runOnUiThread(new Runnable() {public void run() {
                img.setRotation(rotation);
                tv_speed.setText("Speed: "+ speed);
            }
        });
    }

    public void AdsImpossible() {
    }

    public void AdsPossible() {
    }
}
