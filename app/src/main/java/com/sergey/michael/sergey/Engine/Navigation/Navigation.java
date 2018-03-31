package com.sergey.michael.sergey.Engine.Navigation;

import android.app.Activity;
import android.support.design.widget.NavigationView;

import com.sergey.michael.sergey.R;


public abstract class Navigation implements NavigationView.OnNavigationItemSelectedListener{

    Activity activity;

    Navigation(Activity activity, int nav_view){
        this.activity = activity;
        NavigationView navigationView = activity.findViewById(nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public abstract boolean onBackPressed();
}
