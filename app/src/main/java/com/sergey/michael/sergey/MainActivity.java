package com.sergey.michael.sergey;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sergey.michael.sergey.Engine.Audio.MusicLoop;
import com.sergey.michael.sergey.Engine.Util.Toolbox;
import com.sergey.michael.sergey.Engine.Util.visual.Features;

import java.text.DecimalFormat;
import java.text.MessageFormat;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private int high_score = 0;
    private float speed = 0f;
    private float rotation = 0f;

    private Toolbox toolbox;
    private ImageView img;
    private TextView tv_score;
    //private TextView tv_speed;
    private Bundle state;
    private MusicLoop loop;
    private volatile boolean activityStopped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        state = savedInstanceState;
        toolbox = new Toolbox(this);

            toolbox.setup_Toolbar(this,R.id.toolbar,R.id.app_bar_layout);
            toolbox.setup_Drawer(this, R.id.nav_view,R.id.drawer_layout,
                    R.string.navigation_drawer_open,R.string.navigation_drawer_close);
            toolbox.setupBottomNavigationView(this, R.id.bottom_nav_view);


        tv_score = findViewById(R.id.tvScore);

        //tv_speed = findViewById(R.id.tvSpeed);


        Toolbox.setupNavigation(this,this,"Face");

        img = findViewById(R.id.sergey);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickSergey();
            }
        });

        beginRotationLoop();
        if (savedInstanceState == null) {
            loop = new MusicLoop();
            loop.makebackgroundloop(getBaseContext(), R.raw.particle);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        activityStopped = true;
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.servey_preference_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = sharedPref.edit();
        editor.putInt(getString(R.string.score_key), high_score);
        editor.putInt(getString(R.string.speed_key), (int) speed);
        editor.apply();
    }
    @Override
    public void onResume(){
        super.onResume();
        Toolbox.activiateFullscreen(this);

        int defaultValue = 0;
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.servey_preference_file), Context.MODE_PRIVATE);
        high_score  = sharedPref.getInt(getString(R.string.score_key), defaultValue);
        if(state != null){
            speed   = sharedPref.getInt(getString(R.string.speed_key), defaultValue);
        }
        tv_score.setText(MessageFormat.format("Score: {0}", high_score));
        //tv_speed.setText(MessageFormat.format("Speed: {0}", speed));
    }
    @Override
    public void onRestart() {
        super.onRestart();
    }
    @Override
    public void onBackPressed(){
        toolbox.onBackPressed();
    }

    public void clickSergey(){
        high_score +=1;
        if(speed < 5000){speed += 50;}
        //tv_speed.setText(MessageFormat.format("Speed: {0}", speed));
        tv_score.setText(MessageFormat.format("Score: {0}", high_score));
    }

    public void beginRotationLoop(){
        new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                while(!activityStopped){
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
                //tv_speed.setText(MessageFormat.format("Speed: {0}", (int) speed));
            }
        });
    }


}

