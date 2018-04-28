package com.sergey.michael.sergey.Engine.Services;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.util.Log;

import com.sergey.michael.sergey.R;


public class BackgroundMusic extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */

    public BackgroundMusic() {
        super("Sergey music");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.particle);
        mp.setLooping(true);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                Log.d("Music", "An error occured in the music loop");
            }
        });
        mp.start();
    }
}
