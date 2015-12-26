package com.stardust.express.app.request;

import android.content.Context;
import com.stardust.express.app.BaseRequest;
import com.stardust.express.app.Constants;
import com.stardust.express.app.http.StringResponseListener;
import com.stardust.express.app.utils.SharedUtil;

import java.util.Map;

/**
 * Created by Sylar on 15/12/26.
 */
public class TollCollectorListRequest extends BaseRequest {
    public TollCollectorListRequest(Context context, StringResponseListener listener) {
        super(context, listener);
    }

    @Override
    protected String getRequestUrl() {
        return SharedUtil.getString(context, "Host") + Constants.TOLL_COLLECTOR_URL;
//        return "http://192.168.1.3:8080" + Constants.TOLL_COLLECTOR_URL;
    }

    @Override
    protected Map<String, String> getRequestParams() {
        return null;
    }
}
