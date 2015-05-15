package com.stardust.express.app.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Sylar on 15/5/14.
 */
public class ScreenUtils {

    public static int dp2px(Context context, int dpValue) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return (int) (dpValue * dm.density + 0.5f);
    }
}
