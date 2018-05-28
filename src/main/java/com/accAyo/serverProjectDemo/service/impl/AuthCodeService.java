package com.accAyo.serverProjectDemo.service.impl;

import com.accAyo.serverProjectDemo.common.Constants;
import com.accAyo.serverProjectDemo.service.IAuthCodeService;
import com.accAyo.serverProjectDemo.util.CookieUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午10:00 2018/5/24
 */

@Service
public class AuthCodeService implements IAuthCodeService {

    @Resource
    private MemcachedService memcachedService;

    private static int CODE_EXPIRY_TIME = 5 * Constants.ONE_MINUTE_TIMESTAMP; // 验证码过期时间1分钟

    private static String CACHE_PREFIX = "authcode:";

    @Override
    public void setAuthCode(String code, String t, HttpServletRequest request) {
        // TODO: 2018/5/28 检查验证码是否存在
        String keyName = generateCodeKeyName(t, request);
        System.out.println(keyName);
        memcachedService.set(keyName, code, CODE_EXPIRY_TIME);
    }

    @Override
    public boolean isCodeExists(String t, HttpServletRequest request) {
        String keyName = generateCodeKeyName(t, request);
        String cacheCode = (String) memcachedService.get(keyName);
        return cacheCode != null;
    }

    @Override
    public boolean verifyAuthCode(String inputCode, String t, HttpServletRequest request) {
        String keyName = generateCodeKeyName(t, request);
        String cacheCode = (String) memcachedService.get(keyName);
        if (cacheCode != null)
            return inputCode == cacheCode;

        return false;
    }

    private String generateCodeKeyName(String t, HttpServletRequest request) {
        return CACHE_PREFIX + CookieUtil.getCookieValue(request, CookieUtil.COOKIE_UC) + t;
    }
}
