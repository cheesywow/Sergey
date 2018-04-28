package com.sergey.michael.sergey.Engine.Util.ads;

import android.app.Activity;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;

public class BannerListener extends AdListener {
    private Activity activity;

    BannerListener(Activity activity){
        this.activity = activity;
    }

    @Override
    public void onAdLoaded() {
        Toast.makeText(activity, "Banner Loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAdFailedToLoad(int errorCode) {
        Toast.makeText(activity, "Banner Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAdOpened() {
        Toast.makeText(activity, "Banner Opened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAdLeftApplication() {

    }

    @Override
    public void onAdClosed() {
        Toast.makeText(activity, "Banner Closed", Toast.LENGTH_SHORT).show();
    }
}
