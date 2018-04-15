package com.sergey.michael.sergey.Engine.Audio;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import com.sergey.michael.sergey.R;

public class MusicLoop {


    public void makebackgroundloop(Context context, int musicId){
        MediaPlayer  mp = MediaPlayer.create(context, musicId);
        mp.setLooping(true);
        Music music = new Music();
        music.execute(mp);

    }
}
