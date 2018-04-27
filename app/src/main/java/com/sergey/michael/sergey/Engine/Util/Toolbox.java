package com.sergey.michael.sergey.Engine.Util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.sergey.michael.sergey.Engine.Listeners.SpinnerListener;
import com.sergey.michael.sergey.Engine.Navigation.BottomNavigation;
import com.sergey.michael.sergey.Engine.Navigation.Drawer;
import com.sergey.michael.sergey.Engine.TCP.Sender;
import com.sergey.michael.sergey.Engine.Util.ads.AdManager;
import com.sergey.michael.sergey.Engine.Util.visual.Features;
import com.sergey.michael.sergey.MainActivity;
import com.sergey.michael.sergey.R;
import com.sergey.michael.sergey.Shop;


public class Toolbox {

    public FirebaseAnalytics firebaseAnalytics;
    private Toolbar toolbar;
    private Activity activity;

    private Sender sender;
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
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
            |View.SYSTEM_UI_FLAG_LOW_PROFILE;
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

    public void setup_Toolbar(MainActivity myActivity, int toolbar_view, int app_bar_layout){
        if(Features.toolbar){
            toolbar = activity.findViewById(toolbar_view);         //in app_bar_main.xml
            myActivity.setSupportActionBar(toolbar);
        }
        else{
            AppBarLayout applay = activity.findViewById(app_bar_layout);
            applay.setVisibility(View.GONE);
        }
    }

    public void setup_Drawer(Activity activity, int nav_view, int drawer_layout, int open, int close){
        if(Features.drawer) {
            new Drawer(activity,nav_view,drawer_layout);
            DrawerLayout drawerlayout = activity.findViewById(drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    activity, drawerlayout, toolbar, open, close);
            drawerlayout.addDrawerListener(toggle);
            toggle.syncState();
        }
        else{
            DrawerLayout drawlay = activity.findViewById(drawer_layout);
            drawlay.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    public void setupBottomNavigationView(Activity activity, int bottom_nav){
        if(Features.bottom_nav) {
            BottomNavigation navigation = new BottomNavigation(activity, bottom_nav);
        }
        else{
            BottomNavigationView bnv = activity.findViewById(bottom_nav);
            bnv.setVisibility(View.GONE);

        }
    }

    public void setup_Spinner(int id, String[] items, int spinner){
        Spinner dropdown = activity.findViewById(id);
        SpinnerListener listener = new SpinnerListener();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, spinner, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(listener);
    }

    public Sender getSender(){
        return sender;
    }

    public static void setupNavigation(final Context context, final Activity activity, final String current){
        ImageView img_face      =   activity.findViewById(R.id.face_box);
        ImageView img_shop      =   activity.findViewById(R.id.shop_box);
        ImageView img_stat      =   activity.findViewById(R.id.stat_box);
        ImageView img_setting   =   activity.findViewById(R.id.setting_box);

        img_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!current.equals("Face")){
                    context.startActivity(new Intent(context, MainActivity.class));
                    activity.finish();
                }
            }
        });

        img_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!current.equals("Shop")){
                    context.startActivity(new Intent(context, Shop.class));
                    activity.finish();
                }
            }
        });

        img_stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        img_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public void onBackPressed() {
        /*if(Features.drawer){
            drawer.onBackPressed();
        }*/
    }
}
