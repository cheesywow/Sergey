package com.sergey.michael.sergey.Engine.Util.ads;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.sergey.michael.sergey.Engine.Util.Toolbox;
import com.sergey.michael.sergey.MainActivity;

public class RewardAdListener implements RewardedVideoAdListener {
    RewardedVideoAd rewardedVideoAd;
    String ad_id;
    Activity activity;
    Toolbox toolbox;

    MainActivity myactivity;

    public RewardAdListener(Activity activity, MainActivity myactivity, Toolbox toolbox){
        this.activity = activity;
        this.myactivity = myactivity;
        this.toolbox = toolbox;
    }

    public void setAd(RewardedVideoAd rewardedVideoAd, String ad_id){
        this.rewardedVideoAd = rewardedVideoAd;
        this.ad_id = ad_id;
    }

    @Override
    public void onRewarded(RewardItem reward) {
        Toast.makeText(activity, "onRewarded! currency: " + reward.getType() + "  amount: " +
                reward.getAmount(), Toast.LENGTH_SHORT).show();
        myactivity.AdsImpossible();
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
        bundle.putString("reward_amount", ""+reward);
        toolbox.firebaseAnalytics.logEvent("completed_video", bundle);

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Toast.makeText(activity, "onRewardedVideoAdLeftApplication",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Toast.makeText(activity, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
        toolbox.adManager.loadRewardVideoAd(activity,myactivity,rewardedVideoAd,ad_id);
        myactivity.AdsImpossible();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        Toast.makeText(activity, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoCompleted() {

    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Toast.makeText(activity, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
        myactivity.AdsPossible();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Toast.makeText(activity, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
        Toast.makeText(activity, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }


}
