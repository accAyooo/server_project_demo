package com.accAyo.serverProjectDemo.common;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:19 2018/5/20
 */
public class WingsException extends Exception {
    private EnumInfo info;

    public WingsException(String message) {
        super(message);
    }

    public EnumInfo getInfo() {
        return info;
    }

    public WingsException(EnumInfo info, String msg) {
        super(msg);
        this.info = info;
    }

    public WingsException(EnumInfo info, String msg, Throwable cause) {
        super(msg, cause);
        this.info = info;
    }
}
