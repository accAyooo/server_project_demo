package com.accAyo.serverProjectDemo.interceptor;

import com.accAyo.serverProjectDemo.util.CookieUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:21 2018/5/27
 */
public class UserCheckInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        CookieUtil.setUc(request, response);
        return super.preHandle(request, response, handler);
    }


}
