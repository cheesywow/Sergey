package com.sergey.michael.sergey;

import android.annotation.TargetApi;
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
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    static float scale = 1f;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView img = findViewById(R.id.sergey);
        hideBars();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                img.animate().rotationBy(360).withEndAction(this).setDuration(3000).setInterpolator(new LinearInterpolator()).start();

            }
        };

        img.animate().rotationBy(360).withEndAction(runnable).setDuration(3000).setInterpolator(new LinearInterpolator()).start();
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.sergey_scream);
                mp.start();
                if (scale < 10) {
                    scale += 0.5f;
                    img.animate().scaleX(scale);
                    img.animate().scaleY(scale);
                }
            }
        });

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
}
