package com.sergey.michael.sergey.Engine.Util;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.sergey.michael.sergey.Engine.Listeners.SpinnerListener;
import com.sergey.michael.sergey.Engine.Navigation.Drawer;
import com.sergey.michael.sergey.Engine.TCP.Sender;
import com.sergey.michael.sergey.Engine.Util.ads.AdManager;
import com.sergey.michael.sergey.Engine.Util.visual.Features;
import com.sergey.michael.sergey.MainActivity;
import com.sergey.michael.sergey.R;


public class Toolbox {

    public FirebaseAnalytics firebaseAnalytics;
    private Toolbar toolbar;
    private Activity activity;

    private Sender sender;
    private Drawer drawer;
    public AdManager adManager;
    public static String approach = "Averaged Approach";

    public Toolbox(Activity activity){
        this.activity = activity;
    }

    public static void activiateFullscreen(Activity activity){
        View decorView = activity.getWindow().getDecorView();

        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

        if (Build.VERSION.SDK_INT >= 16) {
            uiOptions ^= View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            uiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }
        decorView.setSystemUiVisibility(uiOptions);
    }

    public void setupConnections(){
        sender = new Sender();
    }

    public void setupAnalytics(){
        firebaseAnalytics = FirebaseAnalytics.getInstance(activity);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "0");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Toolbox loaded");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle);
    }

    public void setupAdvertisements(Activity activity){
       adManager = new AdManager(activity,this);
    }

    public void setup_Toolbar(MainActivity myActivity, int toolbar_view){
        //ALSO SET @style/AppTheme.NoActionBar IN THE MANIFEST
        if(!Features.toolbar){
            toolbar = activity.findViewById(toolbar_view);         //in app_bar_main.xml
            myActivity.setSupportActionBar(toolbar);
        }
    }

    public void setup_Drawer(int nav_view, int drawer_layout, int open, int close){
        if(Features.drawer) {
            drawer = new Drawer(activity,nav_view,drawer_layout);
            DrawerLayout drawer = activity.findViewById(drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    activity, drawer, toolbar, open, close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
        }
    }

    public void setup_Spinner(int id, String[] items){
        Spinner dropdown = activity.findViewById(id);
        SpinnerListener listener = new SpinnerListener();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(listener);
    }

    public Sender getSender(){
        return sender;
    }

    public void onBackPressed() {
        if(Features.drawer){
            drawer.onBackPressed();
        }
    }
}