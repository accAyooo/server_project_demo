package com.accAyo.serverProjectDemo.util;

import com.accAyo.serverProjectDemo.common.Constants;
import com.accAyo.serverProjectDemo.common.EnumUserType;
import com.accAyo.serverProjectDemo.pojo.User;
import com.accAyo.serverProjectDemo.service.IUserService;
import com.accAyo.serverProjectDemo.service.impl.UserService;
import com.accAyo.serverProjectDemo.vo.UserVO;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:23 2018/5/27
 */
public class CookieUtil {

    @Resource
    private UserService userService;
    private static final Log logger = LogFactory.getLog(CookieUtil.class);

    public static final String COOKIE_KEY_PREFIX = "cookie_";

    public static final String COOKIE_UC = COOKIE_KEY_PREFIX + "uc";
    public static final String COOKIE_SID = COOKIE_KEY_PREFIX + "sid";
    public static final String COOKIE_UID = COOKIE_KEY_PREFIX + "uid";

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

    public static UserVO getLoginUser(HttpServletRequest request) {
        return (UserVO) request.getAttribute(Constants.REQUEST_LOGIN_USER);
    }
    public static UserVO getLoginUser(HttpServletRequest request, IUserService userService) {
        Cookie[] cookies = request.getCookies();
        int userId = 0;
        String sd = null;
        boolean useCookie = false;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie != null){
                    if (COOKIE_SID.equals(cookie.getName())) {
                        sd = cookie.getValue();
                    }
                    if (COOKIE_UID.equals(cookie.getName())) {
                        userId = Integer.parseInt(cookie.getValue());
                        if (userId > 0)
                            useCookie = true;
                    }
                    if ("cookieStatus".equals(cookie.getName())) {
                        try {
                            if (cookie.getValue() != null)
                                useCookie = true;
                            if (useCookie == false)
                                logger.info("CookieUtil==" + request.getHeader("User-Agent"));
                        }catch (NumberFormatException e) {
                        }
                    }
                }
            }
        }
        if (userId == 0)
            return null;
        User user = userService.getUser(userId);
        if (user == null)
            return null;

        if (user != null && user.isType(EnumUserType.STAFF) && getToken(userId, user.getRandom(), request).equals(sd)){

        } else if (!getToken(userId, user.getRandom(), request).equals(sd)) {
            return null;
        }

        UserVO userVO = userService.getUserVO(user);
        request.setAttribute(Constants.REQUEST_LOGIN_USER, userVO);
        return userVO;
    }

    private static String getToken(int userId, String random, HttpServletRequest request){
        random = random + "0000000000000000000000000000000000000000000000000000000000000000";
        return MD5.MD5(random.substring(1 * Constants.RANDOM_ONE_LENGTH, 2 * Constants.RANDOM_ONE_LENGTH)).substring(8, 28) + userId;
    }

    public static void setUc(HttpServletRequest request, HttpServletResponse response) {
        if (CookieUtil.isCookieSupport(request) && getCookieValue(request, COOKIE_UC) == null) {
            Cookie uc = new Cookie(COOKIE_UC, 0 + RandomUtil.get32Random());
            uc.setPath("/");
            uc.setMaxAge(Constants.ONE_YEAR_TIMESTAMP);
            response.addCookie(uc);
        }
    }

    public static void saveUser(User user, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookieUid = new Cookie(COOKIE_UID, user.getId() + "");
        cookieUid.setMaxAge(Constants.ONE_YEAR_TIMESTAMP * 10);
        response.addCookie(cookieUid);
    }

    public static void clearUser(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (COOKIE_UID.equals(c.getName()) || COOKIE_SID.equals(c.getName())) {
                    c.setMaxAge(0);
                    c.setPath("/");
                    response.addCookie(c);
                }
            }
        }
        request.setAttribute(COOKIE_SID, null);
    }
}
