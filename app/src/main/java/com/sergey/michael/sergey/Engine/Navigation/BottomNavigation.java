package com.sergey.michael.sergey.Engine.Navigation;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.Toast;

import com.sergey.michael.sergey.R;

/**
 * Sets up the bottom navigation menu
 */
public class BottomNavigation extends Navigation{
    protected BottomNavigationView navigation;
    Context context;
    public BottomNavigation(Activity activity, int nav_view){
        super(activity, nav_view);
        this.context = activity.getBaseContext();
        navigation = activity.findViewById(R.id.navigation);
        navigation.setSaveEnabled(true);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    protected BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return createChoices(item);
        }
    };

    private boolean createChoices(MenuItem item){
        switch (item.getItemId()) {
            case R.id.nav_camera:
                Toast.makeText(context,"Camera",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_gallary:
                Toast.makeText(context,"Gallary",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_slideshow:
                Toast.makeText(context,"Slideshow",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_send:
                Toast.makeText(context,"Send",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_share:
                Toast.makeText(context,"Share",Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
