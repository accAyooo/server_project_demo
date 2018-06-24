package com.accAyo.serverProjectDemo.framework.Exception;

import com.accAyo.serverProjectDemo.common.EnumInfoMessage;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:19 2018/5/20
 */
public class MainException extends Exception {
    private EnumInfoMessage info;

    public MainException(String message) {
        super(message);
    }

    public EnumInfoMessage getInfo() {
        return info;
    }

    public MainException(EnumInfoMessage info, String msg) {
        super(msg);
        this.info = info;
    }

    public MainException(EnumInfoMessage info, String msg, Throwable cause) {
        super(msg, cause);
        this.info = info;
    }
}
