package com.stardust.express.app.http;

/**
 * Created by Sylar on 15/2/25.
 */
public interface StringResponseListener {

    void onResponse(String response);

    void onError(String errorMessage);
}
