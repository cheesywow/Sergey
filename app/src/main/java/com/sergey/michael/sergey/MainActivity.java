package com.sergey.michael.sergey;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.audiofx.AudioEffect;
import android.media.audiofx.EnvironmentalReverb;
import android.media.audiofx.Virtualizer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import static java.lang.Math.log;
import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    static float scale = 1f;
    int highscore = 0;
    float speed = 0f;
    float rotation = 0f;
    ImageView img;
    Bitmap myImg;
    Matrix matrix;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        int defaultValue = 0;
        highscore = sharedPref.getInt(getString(R.string.preference_file_key), defaultValue);
        TextView score = findViewById(R.id.tvScore);
        score.setText("Score: "+highscore);


        img = findViewById(R.id.sergey);
        hideBars();


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                img.animate().rotationBy(360).withEndAction(this).setDuration(3000).setInterpolator(new LinearInterpolator()).start();

            }
        };



        myImg = BitmapFactory.decodeResource(getResources(), R.drawable.sergey);

        matrix = new Matrix();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    //Log.d("Rotation",""+rotation);
                    try {
                        if(speed > 0) {
                            sleep((long) (10));
                            rotateImage();
                            speed -= 0.5f;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();

        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                highscore+=1;
                TextView score = findViewById(R.id.tvScore);
                score.setText("Score: "+highscore);
                MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.sergey_scream);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
                mp.start();
                if(speed < 5000){
                    speed += 50;
                    Log.d("Not Limit",""+speed);
                }
                else{
                    Log.d("Limit",""+speed);
                }
                //img.animate().rotationBy(360).withEndAction(null).setDuration((long) (3000/rotation)).setInterpolator(new LinearInterpolator()).start();
                //rotation += 1;
                /*if (scale < 10) {
                    scale += 0.5f;
                    img.animate().scaleX(scale);
                    img.animate().scaleY(scale);
                }*/
            }
        });
        /*while(true){
            rotateImage();
        }*/
    }

    @Override
    public void onPause(){
        super.onPause();
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.preference_file_key), highscore);
        editor.commit();
    }

    @Override
    public void onResume(){
        super.onResume();
        hideBars();
        int defaultValue = 0;
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        highscore = sharedPref.getInt(getString(R.string.preference_file_key), defaultValue);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void hideBars(){
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }

    public void rotateImage(){
        rotation += 0.05 + speed/100;
        if(rotation > 360){
            rotation -= 360;
        }
        //matrix.postRotate(rotation);
        //final Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
        //        matrix, true);
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                img.setRotation(rotation);
            }
        });
    }
}
