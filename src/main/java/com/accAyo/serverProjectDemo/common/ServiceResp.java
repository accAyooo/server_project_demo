package com.accAyo.serverProjectDemo.common;

import javax.xml.ws.Service;
import java.io.Serializable;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 上午12:09 2018/5/11
 */
public class ServiceResp<T> implements Serializable {

    private int code;

    private String message;

    private T data;

    private ServiceResp(int code) {
        this.code = code;
    }

    private ServiceResp(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private ServiceResp(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return this.code == ServiceCode.SUCCESS.getCode();
    }

    public static <T> ServiceResp<T> createBySuccess() {
        return new ServiceResp<T>(ServiceCode.SUCCESS.getCode());
    }

    public static <T> ServiceResp<T> createBySuccessMessage(String msg) {
        return new ServiceResp<T>(ServiceCode.SUCCESS.getCode(), msg);
    }

    public static <T> ServiceResp<T> createBySuccess(T data) {
        return new ServiceResp<T>(ServiceCode.SUCCESS.getCode(), ServiceCode.SUCCESS.getDesc(), data);
    }

    public static <T> ServiceResp<T> createByError() {
        return new ServiceResp<T>(ServiceCode.ERROR.getCode());
    }

    public static <T> ServiceResp<T> createByErrorMessage(String msg) {
        return new ServiceResp<T>(ServiceCode.ERROR.getCode(), msg);
    }

    public static <T> ServiceResp<T> createByError(T data) {
        return new ServiceResp<T>(ServiceCode.ERROR.getCode(), ServiceCode.ERROR.getDesc(), data);
    }
}
