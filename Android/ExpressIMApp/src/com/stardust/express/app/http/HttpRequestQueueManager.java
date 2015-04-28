package com.stardust.express.app.http;

import android.content.Context;
import com.android.volley.*;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.apache.http.protocol.HTTP;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Sylar on 15/2/25.
 */
public class HttpRequestQueueManager {

    private static HttpRequestQueueManager manager = null;
    private RequestQueue requestQueue;

    private HttpRequestQueueManager() {
    }


    public static HttpRequestQueueManager getRequestQueueManager() {
        if (manager == null) {
            synchronized (HttpRequestQueueManager.class) {
                if (manager == null)
                    manager = new HttpRequestQueueManager();
            }
        }
        return manager;
    }

    public void init(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public void stringRequest(int method, String url, final Map<String, String> params, final StringResponseListener listener) {
        if (requestQueue == null) return;
        StringRequest request = new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (listener != null)
                    listener.onResponse(s);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (listener != null)
                    listener.onError(volleyError.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (params != null) {
                    return params;
                }
                return super.getParams();
            }

            @Override
            protected String getParamsEncoding() {
                return HTTP.UTF_8;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String str = null;
                try {
                    str = new String(response.data, HTTP.UTF_8);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return Response.success(str, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        request.addMarker("request");
        requestQueue.add(request);
    }

    public void stringRequest(String url, final Map<String, String> params, final StringResponseListener listener) {
        stringRequest(Request.Method.POST, url, params, listener);
    }

    public void stringRequest(String url, final StringResponseListener listener) {
        stringRequest(url, null, listener);
    }

    public void stringRequest(String url) {
        stringRequest(url, null, null);
    }

    public void release() {
        manager = null;
        if (requestQueue != null) {
            requestQueue.cancelAll("request");
        }
    }
}
