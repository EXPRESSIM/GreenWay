package com.stardust.express.app;

import android.content.Context;
import android.util.Log;
import com.stardust.express.app.http.HttpRequestQueueManager;
import com.stardust.express.app.http.StringResponseListener;

import java.util.Map;

/**
 * Created by Sylar on 15/5/13.
 */
public abstract class BaseRequest {

    protected abstract String getRequestUrl();

    protected abstract Map<String, String> getRequestParams();

    protected Context context;
    protected StringResponseListener listener;

    public BaseRequest(Context context, StringResponseListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void execute() {
        Log.e("TAG", "url = " + getRequestUrl());
        HttpRequestQueueManager.getRequestQueueManager().stringRequest(getRequestUrl(), getRequestParams(), listener);
    }
}
