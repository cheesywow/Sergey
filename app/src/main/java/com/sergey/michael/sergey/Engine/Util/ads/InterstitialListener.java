package com.sergey.michael.sergey.Engine.Util.ads;


import android.app.Activity;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class InterstitialListener extends AdListener {
    private Activity activity;
    private InterstitialAd interstitialAd;

    InterstitialListener(Activity activity, InterstitialAd interstitialAd){
        this.activity = activity;
        this.interstitialAd = interstitialAd;
    }

    @Override
    public void onAdLoaded() {
        Toast.makeText(activity, "Interstitial Loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAdFailedToLoad(int errorCode) {
        Toast.makeText(activity, "Interstitial Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAdOpened() {
        Toast.makeText(activity, "Interstitial Opened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAdLeftApplication() {

    }

    @Override
    public void onAdClosed() {
        Toast.makeText(activity, "Interstitial Closed", Toast.LENGTH_SHORT).show();
        interstitialAd.loadAd(new AdRequest.Builder().build());
    }
}
