package com.accAyo.serverProjectDemo.common;

/**
 * Desc:
 *
 * @author shixiangyu
 * @date 2018/5/28
 */
public enum EnumResponseCode {
    SUCCESS_CODE(1, "SUCCESS"),
    ERROR_CODE(0, "ERROR");

    private int code;
    private String desc;

    EnumResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
