package com.stardust.express.app.request;

import android.content.Context;
import com.stardust.express.app.BaseRequest;
import com.stardust.express.app.Constants;
import com.stardust.express.app.http.StringResponseListener;
import com.stardust.express.app.utils.SharedUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sylar on 15/5/13.
 */
public class OperatorLogonRequest extends BaseRequest {

    private String username;
    private String password;

    public OperatorLogonRequest(Context context,
                                String username,
                                String password,
                                StringResponseListener listener) {
        super(context, listener);
        this.username = username;
        this.password = password;
    }

    @Override
    protected String getRequestUrl() {
        return SharedUtil.getString(context, "Host") + Constants.OPERATOR_LOGON_URL;
    }

    @Override
    protected Map<String, String> getRequestParams() {
        Map<String, String> params = new HashMap<String, String>(3);
        params.put("username", username);
        params.put("password", password);
        return params;
    }
}
