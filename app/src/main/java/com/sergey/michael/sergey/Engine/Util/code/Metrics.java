package com.sergey.michael.sergey.Engine.Util.code;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class Metrics {

    public static int convertDpToPixel(Context context, float dp){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return (int) px;
    }
}
