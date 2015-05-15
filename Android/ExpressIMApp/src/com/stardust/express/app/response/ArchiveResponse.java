package com.stardust.express.app.response;

import com.stardust.express.app.BaseResponse;

/**
 * Created by Sylar on 15/5/14.
 */
public class ArchiveResponse extends BaseResponse {
    public ArchiveResponse(String response) {
        super(response);
    }

    @Override
    protected Object parseData(String jsonStr) {
        return null;
    }
}
