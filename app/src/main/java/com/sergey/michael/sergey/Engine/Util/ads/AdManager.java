package com.sergey.michael.sergey.Engine.Util.ads;

import android.app.Activity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.sergey.michael.sergey.Engine.Util.Toolbox;
import com.sergey.michael.sergey.MainActivity;
import com.sergey.michael.sergey.R;

public class AdManager {

    Toolbox toolbox;
    String app_id ="ca-app-pub-8954833416551696~1983175835";

    public AdManager(Activity activity, Toolbox toolbox){
        this.toolbox = toolbox;
        MobileAds.initialize(activity, app_id);
    }

    public AdView loadBanner(Activity activity, String ad_id, int adView){
        BannerListener bl = new BannerListener(activity);
        AdView bannerAd = activity.findViewById(adView);
        bannerAd.setAdListener(bl);
        return bannerAd;
    }

    public void loadBannerAd(AdView bannerAd){
        bannerAd.loadAd(new AdRequest.Builder().build());
    }

    public InterstitialAd loadInterstitual(Activity activity, String ad_id){
        InterstitialAd interstitialAd = new InterstitialAd(activity);
        InterstitialListener interstitialListener = new InterstitialListener(activity,interstitialAd);
        interstitialAd.setAdUnitId(ad_id);
        interstitialAd.setAdListener(interstitialListener);
        return interstitialAd;
    }

    public void loadInterstitualAd(InterstitialAd interstitialAd){
        interstitialAd.loadAd(new AdRequest.Builder().build());
    }

    public RewardedVideoAd loadRewardVideo(Activity activity, String ad_id){
        RewardedVideoAd rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(activity);

        return rewardedVideoAd;
    }
    public void loadRewardVideoAd(Activity activity, AdHandler handler, RewardedVideoAd rewardedVideoAd, String ad_id) {
        RewardAdListener ra = new RewardAdListener(activity,handler,toolbox);
        rewardedVideoAd.setRewardedVideoAdListener(ra);
        rewardedVideoAd.loadAd(ad_id, new AdRequest.Builder().build());
        ra.setAd(rewardedVideoAd,ad_id);
    }

}
