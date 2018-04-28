package com.sergey.michael.sergey;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.sergey.michael.sergey.Engine.Util.Toolbox;

import java.text.MessageFormat;

public class Stats extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Toolbox.setupNavigation(this,this,"Stats");

        TextView clicks = findViewById(R.id.tv_clicks);
        TextView items = findViewById(R.id.tv_items);
        TextView time = findViewById(R.id.tv_time);

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.servey_preference_file), Context.MODE_PRIVATE);

        clicks.setText(MessageFormat.format("{0}",
                sharedPref.getInt(getString(R.string.click_key), 0)));

        items.setText(MessageFormat.format("{0}",
                sharedPref.getInt(getString(R.string.items_key), 0)));

        long milliseconds = sharedPref.getLong(getString(R.string.timeplayed_key),0);
        long seconds = milliseconds/1000;
        long s = seconds % 60;
        long m = (seconds / 60) % 60;
        long h = (seconds / (60 * 60)) % 24;
        String duration = String.format("%d:%02d:%02d", h,m,s);

        time.setText(duration);


    }
    @Override
    public void onResume(){
        super.onResume();
        Toolbox.activiateFullscreen(this);
    }

}
