package com.sergey.michael.sergey.Engine.Navigation;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.sergey.michael.sergey.R;

public class Drawer extends Navigation{

    int drawer_layout;

    public Drawer(Activity activity, int nav_view, int drawer_layout) {
        super(activity,nav_view);
        this.drawer_layout = drawer_layout;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle bottom_nav_menu view item clicks here.
        int id = item.getItemId();

        if (id == R.id.drawer_nav_camera) {

        } else if (id == R.id.drawer_nav_gallery) {

        } else if (id == R.id.drawer_nav_slideshow) {

        } else if (id == R.id.drawer_nav_manage) {

        } else if (id == R.id.drawer_nav_share) {

        }

        DrawerLayout drawer = activity.findViewById(drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean onBackPressed(){
        DrawerLayout drawer = activity.findViewById(drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }
}
