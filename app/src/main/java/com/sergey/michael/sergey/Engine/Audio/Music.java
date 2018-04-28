package com.sergey.michael.sergey.Engine.Audio;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;

public class Music extends AsyncTask<Object,Integer,String> {


    @Override
    protected String doInBackground(Object[] params) {
        MediaPlayer mp = (MediaPlayer) params[0];
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                    Log.d("Music", "An error occured in the music loop");
                }
            });
            mp.start();
        return null;
    }
}
