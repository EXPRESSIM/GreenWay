package com.stardust.express.app.request;

import android.content.Context;
import com.stardust.express.app.BaseRequest;
import com.stardust.express.app.Constants;
import com.stardust.express.app.http.StringResponseListener;
import com.stardust.express.app.utils.SharedUtil;

import java.util.Map;

/**
 * Created by Sylar on 16/1/1.
 */
public class CargoListRequest extends BaseRequest {

    public CargoListRequest(Context context, StringResponseListener listener) {
        super(context, listener);
    }

    @Override
    protected String getRequestUrl() {
        return SharedUtil.getString(context, "Host") + Constants.CARGO_URL;
    }

    @Override
    protected Map<String, String> getRequestParams() {
        return null;
    }
}
