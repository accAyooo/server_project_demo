package com.accAyo.serverProjectDemo.common;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:38 2018/5/27
 */
public class Constants {

    public static final Integer ONE_SECOND_TIMESTAMP = 1;
    public static final Integer ONE_MINUTE_TIMESTAMP = 60 * ONE_SECOND_TIMESTAMP;
    public static final Integer ONE_HOUR_TIMESTAMP = 60 * ONE_MINUTE_TIMESTAMP;
    public static final Integer ONE_DAY_TIMESTAMP = 24 * ONE_HOUR_TIMESTAMP;
    public static final Integer ONE_MONTH_TIMESTAMP = 30 * ONE_DAY_TIMESTAMP;
    public static final Integer ONE_YEAR_TIMESTAMP = 365 * ONE_DAY_TIMESTAMP;

    public static final int RANDOM_ONE_LENGTH = 16;

    public static final String REQUEST_LOGIN_USER = "LOGIN_USER";

    public static final byte STATUS_DELETE = -1;
    public static final byte STATUS_NORMAL = 0;

    public static final int BOOK_MAX_LENTH = 15;
    public static final int BOOK_MIN_LENTH = 1;

    public static final String SPIDER_BOOK_SYMBOL = "00000";
}
