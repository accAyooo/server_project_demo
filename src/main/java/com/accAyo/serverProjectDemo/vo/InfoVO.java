package com.accAyo.serverProjectDemo.vo;

import com.accAyo.serverProjectDemo.common.EnumInfoMessage;
import com.accAyo.serverProjectDemo.common.EnumResponseCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 *
 * @author shixiangyu
 * @date 2018/5/28
 */
public class InfoVO {
    private Map<String, Object> map;
    private Object object;

    public InfoVO() {
        map = new HashMap<>();
    }

    public InfoVO(EnumResponseCode code,EnumInfoMessage message) {
        map.put("code", code.getCode());
        map.put("message", message.getValue());
    }

    public InfoVO(EnumResponseCode code, EnumInfoMessage message, Object result) {
        map.put("code", code.getCode());
        map.put("message", message.getValue());
        map.put("result", result);
    }

    public Map<String, Object> createError(String message) {
        map.put("code", EnumResponseCode.ERROR_CODE.getCode());
        map.put("message", message);
        return map;
    }

    public Map<String, Object> createSuccess(String message) {
        map.put("code", EnumResponseCode.SUCCESS_CODE.getCode());
        map.put("message", message);
        return map;
    }

    public Map<String, Object> createSuccess(String message, Object result) {
        map.put("code", EnumResponseCode.SUCCESS_CODE.getCode());
        map.put("message", message);
        map.put("result", result);
        return map;
    }

    public Map<String, Object> createSuccess(Object result) {
        map.put("code", EnumResponseCode.SUCCESS_CODE.getCode());
        map.put("result", result);
        return map;
    }

    public Map<String, Object> toJsonData() {
        return map;
    }
}
