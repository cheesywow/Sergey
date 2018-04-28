package com.sergey.michael.sergey.Engine.Navigation;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.Toast;

import com.sergey.michael.sergey.R;

/**
 * Sets up the bottom bottom_nav_menu menu
 */
public class BottomNavigation{
    private Context context;
    public BottomNavigation(Activity activity, int bottom_nav){
        this.context = activity.getBaseContext();
        BottomNavigationView navigation = activity.findViewById(bottom_nav);
        navigation.setSaveEnabled(true);
        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return createChoices(item);
            }
        };
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private boolean createChoices(MenuItem item){
        switch (item.getItemId()) {
            case R.id.bottom_nav_camera:
                Toast.makeText(context,"Camera",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.bottom_nav_gallary:
                Toast.makeText(context,"Gallary",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.bottom_nav_slideshow:
                Toast.makeText(context,"Slideshow",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.bottom_nav_send:
                Toast.makeText(context,"Send",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.bottom_nav_share:
                Toast.makeText(context,"Share",Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

}
