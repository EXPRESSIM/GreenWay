package com.stardust.express.app.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Sylar on 15/5/14.
 */
public class ScreenUtils {

    public static int dp2px(Context context, int dpValue) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return (int) (dpValue * dm.density + 0.5f);
    }

    public static ScreenResolution getScreenResolution(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return new ScreenResolution(display.getWidth(), display.getHeight());
    }

    public static class ScreenResolution {
        private int width;
        private int height;

        ScreenResolution() {
        }

        ScreenResolution(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }
    }
}
