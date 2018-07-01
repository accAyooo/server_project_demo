package com.accAyo.serverProjectDemo.interceptor;

import com.accAyo.serverProjectDemo.common.Constants;
import com.accAyo.serverProjectDemo.pojo.User;
import com.accAyo.serverProjectDemo.service.impl.ManageService;
import com.accAyo.serverProjectDemo.service.impl.UserService;
import com.accAyo.serverProjectDemo.util.CookieUtil;
import com.accAyo.serverProjectDemo.vo.UserVO;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:21 2018/5/27
 */
public class UserCheckInterceptor extends HandlerInterceptorAdapter {

    private final static String JSONP_CALLBACK = "callback";
    private final static String JSONP_PREFIX = "jQuery";

    @Resource
    private UserService userService;
    @Resource
    private ManageService manageService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        CookieUtil.setUc(request, response);

        // 处理用户信息
        UserVO userVO = CookieUtil.getLoginUser(request, userService);

        // todo 写死manage的登录用户
        if (request.getServletPath().startsWith("/manage")) {
            User manageUser = userService.getUser(1);
            UserVO manageUserVO = userService.getUserVO(manageUser);
            request.setAttribute(Constants.REQUEST_LOGIN_USER, manageUserVO);
            userVO = manageUserVO;
        }


        if (request.getServletPath().startsWith("/manage")) {
            if (userVO == null || !userVO.isStaff() || !manageService.isAccess(userVO.getId(), request.getServletPath())) {
                response.sendRedirect("/error");
                return false;
            }
        }

        if (this.isJsonp(request)) {
            response.getOutputStream().print(request.getParameter(JSONP_CALLBACK) + "(");
        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
        if (this.isJsonp(request)) {
            response.getOutputStream().print(")");
        }
    }

    private boolean isJsonp(HttpServletRequest request) {
        String callback = request.getParameter(JSONP_CALLBACK);
        return callback != null && callback.startsWith(JSONP_PREFIX);
    }

}
