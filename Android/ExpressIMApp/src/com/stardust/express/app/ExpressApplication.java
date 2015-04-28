package com.stardust.express.app;

import android.app.Application;
import com.stardust.express.app.http.HttpRequestQueueManager;

/**
 * Created by Gyb on 2015/4/27.
 */
public class ExpressApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HttpRequestQueueManager.getRequestQueueManager().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        HttpRequestQueueManager.getRequestQueueManager().release();
    }
}
