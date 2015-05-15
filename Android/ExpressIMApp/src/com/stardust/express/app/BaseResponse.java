package com.stardust.express.app;

import com.stardust.express.app.utils.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sylar on 15/5/13.
 */
public abstract class BaseResponse<T> {

    public boolean success;

    public String message;

    public T data;

    public BaseResponse(String response) {
        if (StringUtils.isNotNull(response)) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                this.success = jsonObject.optBoolean("success");
                this.message = jsonObject.optString("message");
                this.data = parseData(jsonObject.optString("data"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    protected abstract T parseData(String jsonStr);
}
