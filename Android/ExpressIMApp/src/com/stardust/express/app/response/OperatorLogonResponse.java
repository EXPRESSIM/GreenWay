package com.stardust.express.app.response;

import com.stardust.express.app.BaseResponse;
import com.stardust.express.app.entity.UserEntity;
import com.stardust.express.app.utils.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sylar on 15/5/13.
 */
public class OperatorLogonResponse extends BaseResponse<UserEntity> {

    public OperatorLogonResponse(String response) {
        super(response);
    }

    @Override
    protected UserEntity parseData(String jsonStr) {
        if (!StringUtils.isNotNull(jsonStr)) return null;
        UserEntity userEntity = new UserEntity();
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            userEntity.id = jsonObject.optLong("id");
            userEntity.name = jsonObject.optString("name");
            userEntity.username = jsonObject.optString("username");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userEntity;
    }
}
