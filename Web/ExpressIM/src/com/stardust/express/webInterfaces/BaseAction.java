package com.stardust.express.webInterfaces;

import com.stardust.express.actions.ActionExecutor;
import com.stardust.express.tools.ResponseObject;

/**
 * Created by Sylar on 15/5/13.
 */
public class BaseAction extends ActionExecutor {

    public ResponseObject responseData;


    public ResponseObject getResponseData() {
        return responseData;
    }
}
