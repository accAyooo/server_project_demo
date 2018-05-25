package com.accAyo.serverProjectDemo.service.impl;

import com.accAyo.serverProjectDemo.service.IAuthCodeService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午10:00 2018/5/24
 */

@Service
public class AuthCodeService implements IAuthCodeService {

    @Override
    public void setAuthCode(String code, String t, HttpServletRequest request) {
//        String key = getAuthCodeKey(t, request);


    }
}
