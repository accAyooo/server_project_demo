package com.accAyo.serverProjectDemo.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午9:59 2018/5/24
 */
public interface IAuthCodeService {

    public void setAuthCode(String code, String t, HttpServletRequest request);

    public boolean isCodeExists(String t, HttpServletRequest request);

    public boolean verifyAuthCode(String inputCode, String t, HttpServletRequest request);
}
