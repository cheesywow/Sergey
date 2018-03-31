package com.sergey.michael.sergey.Engine.TCP;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Connector {

    public static boolean isConnected(Activity activity){
        ConnectivityManager connMgr = (ConnectivityManager) activity.getSystemService(Activity.CONNECTIVITY_SERVICE);
        assert connMgr != null;
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
