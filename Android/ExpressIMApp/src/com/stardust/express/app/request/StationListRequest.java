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
public class StationListRequest extends BaseRequest {

    public StationListRequest(Context context, StringResponseListener listener) {
        super(context, listener);
    }

    @Override
    protected String getRequestUrl() {
        return SharedUtil.getString(context, "Host") + Constants.TOLL_STATION_URL;
    }

    @Override
    protected Map<String, String> getRequestParams() {
        return null;
    }
}
