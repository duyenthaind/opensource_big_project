package com.group7.fruitswebsite.util;

import com.group7.fruitswebsite.common.Constants;
import org.json.JSONObject;

/**
 * @author duyenthai
 */
public class JsonUtil {
    private JsonUtil(){
    }

    public static JSONObject createResponseMessage(int errorCode, String message, JSONObject customData){
        JSONObject jsonRes = new JSONObject();
        jsonRes.put(Constants.CustomMessageField.ERROR_CODE, errorCode);
        jsonRes.put(Constants.CustomMessageField.MESSAGE, message);
        jsonRes.put(Constants.CustomMessageField.CUSTOM_DATA, customData);
        return jsonRes;
    }
}
