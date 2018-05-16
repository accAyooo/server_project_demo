package com.accAyo.serverProjectDemo.common;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 上午12:17 2018/5/11
 */
public enum ServiceCode {
    ERROR(0, "ERROR"),                                  //错误
    SUCCESS(1, "SUCCESS"),                              //成功
    NEED_LOGGIN(10, "NEED_LOGIN"),                      //需要登录
    ILLEGAL_ARGUMENT(3, "ILLEGAL_ARGUMENT");            //参数错误

    private final int code;
    private final String desc;

    ServiceCode(int code, String desc){
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
