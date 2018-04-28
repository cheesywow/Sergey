package com.sergey.michael.sergey.Engine.Util.ads;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.sergey.michael.sergey.Engine.Util.Toolbox;

public class RewardAdListener implements RewardedVideoAdListener {
    private RewardedVideoAd rewardedVideoAd;
    private String ad_id;
    private Activity activity;
    private Toolbox toolbox;
    private AdHandler handler;

    RewardAdListener(Activity activity, AdHandler handler, Toolbox toolbox){
        this.activity = activity;
        this.toolbox = toolbox;
        this.handler = handler;
    }

    void setAd(RewardedVideoAd rewardedVideoAd, String ad_id){
        this.rewardedVideoAd = rewardedVideoAd;
        this.ad_id = ad_id;
    }

    @Override
    public void onRewarded(RewardItem reward) {
        Toast.makeText(activity, "onRewarded! currency: " + reward.getType() + "  amount: " +
                reward.getAmount(), Toast.LENGTH_SHORT).show();
        handler.AdsImpossible();
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
        toolbox.adManager.loadRewardVideoAd(activity,handler,rewardedVideoAd,ad_id);
        handler.AdsImpossible();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        Toast.makeText(activity, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoCompleted() {
        Toast.makeText(activity, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Toast.makeText(activity, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
       handler.AdsPossible();
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
