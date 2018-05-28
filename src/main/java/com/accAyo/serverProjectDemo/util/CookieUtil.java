package com.accAyo.serverProjectDemo.util;

import com.accAyo.serverProjectDemo.common.Constants;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:23 2018/5/27
 */
public class CookieUtil {
    private static final Log logger = LogFactory.getLog(CookieUtil.class);

    public static final String COOKIE_KEY_PREFIX = "cookie_";

    public static final String COOKIE_UC = COOKIE_KEY_PREFIX + "uc";

    private static boolean isCookieSupport(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return false;
        }
        return true;
    }

    public static String getCookieValue(HttpServletRequest request, String key) {
        if (key == null)
            return null;
        if (!key.startsWith(COOKIE_KEY_PREFIX)) {
            key = COOKIE_KEY_PREFIX + key;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie != null && key.equals(cookie.getName()))
                    return cookie.getValue();
            }
        }
        return null;
    }

    public static void setUc(HttpServletRequest request, HttpServletResponse response) {
        if (CookieUtil.isCookieSupport(request) && getCookieValue(request, COOKIE_UC) == null) {
            Cookie uc = new Cookie(COOKIE_UC, 0 + RandomUtil.get32Random());
            uc.setPath("/");
            uc.setMaxAge(Constants.ONE_YEAR_TIMESTAMP);
            response.addCookie(uc);
        }
    }
}
