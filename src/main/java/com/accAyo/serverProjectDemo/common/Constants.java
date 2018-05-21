package com.accAyo.serverProjectDemo.common;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午10:54 2018/5/20
 */
public class Constants {

    public static final String WEB_HTTP = "http://";
    public static final String WEB_HOST = "localhost:8081";

    public static final String STATIC_SERVER_HTTP = "http://";
    public static final String IMAGE_SERVER_DOMAIN = STATIC_SERVER_HTTP + "localhost:8080";
    public static final String JS_SERVER_DOMAIN = STATIC_SERVER_HTTP + "localhost:8080";

    // 充值最大金额和最小金额
    public static int PAY_MIN_TOPUP_MONEY = 1; // 10
    public static int PAY_MAX_TOPUP_MONEY = 999;
}
