package com.accAyo.serverProjectDemo.framwork.Exception;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:19 2018/5/20
 */
public class MainException extends Exception {
    private EnumInfo info;

    public MainException(String message) {
        super(message);
    }

    public EnumInfo getInfo() {
        return info;
    }

    public MainException(EnumInfo info, String msg) {
        super(msg);
        this.info = info;
    }

    public MainException(EnumInfo info, String msg, Throwable cause) {
        super(msg, cause);
        this.info = info;
    }
}
