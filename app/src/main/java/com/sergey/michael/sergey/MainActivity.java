package com.sergey.michael.sergey;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sergey.michael.sergey.Engine.Audio.MusicLoop;
import com.sergey.michael.sergey.Engine.Util.Toolbox;
import com.sergey.michael.sergey.Engine.Util.data.MyRoom;
import com.sergey.michael.sergey.Engine.Util.data.User;

import java.text.MessageFormat;
import java.util.List;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private Bundle state;

    private float high_score = 0;
    private float speed = 0f;
    private float rotation = 0f;
    private int clicks = 0;
    private int pps = 0;

    private long time;
    private Toolbox toolbox;

    private ImageView img;
    private TextView tv_score;

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
        Toolbox.setupBottomNavigationView(this, R.id.bottom_nav_view);
        Toolbox.setupNavigation(this,this,"Face");

        tv_score = findViewById(R.id.tvScore);
        img = findViewById(R.id.sergey);


        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickSergey();
            }
        });

        beginRotationLoop();
        if (savedInstanceState == null) {
            MusicLoop loop = new MusicLoop();
            loop.makebackgroundloop(getBaseContext(), R.raw.particle);
        }

        //loadDataBase();
    }

    @Override
    public void onPause() {
        super.onPause();
        activityStopped = true;
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.servey_preference_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = sharedPref.edit();
        //Save Score
        editor.putInt(getString(R.string.score_key), (int) high_score);
        //Save Speed
        editor.putInt(getString(R.string.speed_key), (int) speed);
        //Save Clicks
        int old_clicks = sharedPref.getInt(getString(R.string.click_key),0);
        editor.putInt(getString(R.string.click_key),old_clicks+clicks);
        //Save Time Played
        long time_before = sharedPref.getLong(getString(R.string.timeplayed_key),0);
        long total_time = time_before+(System.currentTimeMillis()-time);
        editor.putLong(getString(R.string.timeplayed_key), total_time);
        editor.apply();
    }


    @Override
    public void onResume(){
        super.onResume();
        Toolbox.activiateFullscreen(this);
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.servey_preference_file), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = sharedPref.edit();
        //Retrieve all items in the inventory
        int item1  = sharedPref.getInt(getString(R.string.item1_key), 0);
        int item2  = sharedPref.getInt(getString(R.string.item2_key), 0);
        int item3  = sharedPref.getInt(getString(R.string.item3_key), 0);
        int item4  = sharedPref.getInt(getString(R.string.item4_key), 0);
        int item5  = sharedPref.getInt(getString(R.string.item5_key), 0);
        int item6  = sharedPref.getInt(getString(R.string.item6_key), 0);
        int item7  = sharedPref.getInt(getString(R.string.item7_key), 0);
        int item8  = sharedPref.getInt(getString(R.string.item8_key), 0);

        //Save current pps
        pps = item1 + 3*item2 + 5*item3 + 10*item4 + 25*item5 + 60*item6 + 150*item7 + 400*item8;
        editor.putInt(getString(R.string.pointpersec), pps);
        TextView pointpersec = findViewById(R.id.tv_poitpersec);
        pointpersec.setText(MessageFormat.format("{0} pps", pps));
        high_score  = sharedPref.getInt(getString(R.string.score_key), 0);
        if(state != null){
            if(high_score > 0){
                speed = 150;
            }
            else{
                speed   = sharedPref.getInt(getString(R.string.speed_key), 0);
            }
        }
        //Update the UI
        tv_score.setText(MessageFormat.format("Score: {0}", (int) high_score));
        //Reset the Clock
        time = System.currentTimeMillis();

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
        high_score++;
        if(speed < 5000){speed += 50;}
        tv_score.setText(MessageFormat.format("Score: {0}", (int) high_score));
        clicks++;
    }

    public void beginRotationLoop(){
        new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                while(!activityStopped){
                    try {
                        sleep(10);
                        rotateImage();
                        if(speed > 300){
                            speed -= 0.5f;
                        }
                        if(speed != 0 && speed % 200 == 0){
                            speed -= 0.5f;
                            playScream();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void rotateImage(){
        rotation += speed/100;
        if(rotation > 360) {
            rotation -= 360;
        }
        MainActivity.this.runOnUiThread(new Runnable() {public void run() {
                img.setRotation(rotation);
                high_score += pps /100f;
                tv_score.setText(MessageFormat.format("Score: {0}", (int) high_score));
                //tv_speed.setText(MessageFormat.format("Speed: {0}", (int) speed));
            }
        });
    }

    public void playScream(){
        MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.sergey_scream);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
        mp.start();
    }

    public void loadDataBase(){
        new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                    MyRoom db = Room.databaseBuilder(getApplicationContext(),
                            MyRoom.class, "sergey-database").build();
                    User user = new User();
                    user.setUid(55);
                    user.setFirstName("Bob");
                    user.setLastName("adilla");
                    db.userDao().insertAll(user);
                    User same = db.userDao().findByName("Bob","adilla");
                    int i =0;
                    for(User each: db.userDao().getAll()){
                        Log.d("USER ID",i+": "+each.getUid());
                        i++;
                }

            }
        }).start();

    }
}

