package com.accAyo.serverProjectDemo.controller.portal;

import com.accAyo.serverProjectDemo.common.EnumInfoMessage;
import com.accAyo.serverProjectDemo.framwork.Exception.EnumInfo;
import com.accAyo.serverProjectDemo.framwork.Exception.MainException;
import com.accAyo.serverProjectDemo.pojo.User;
import com.accAyo.serverProjectDemo.service.impl.AuthCodeService;
import com.accAyo.serverProjectDemo.service.impl.UserService;
import com.accAyo.serverProjectDemo.util.CookieUtil;
import com.accAyo.serverProjectDemo.util.ValidationUtil;
import com.accAyo.serverProjectDemo.vo.InfoVO;
import com.accAyo.serverProjectDemo.vo.UserVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午9:54 2018/5/24
 */

@Controller
@RequestMapping("/user")
public class AccountsController {

    @Resource
    private UserService userService;
    @Resource
    private AuthCodeService authCodeService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Object register(String code, String t, String nickName, String email, String password,
            HttpServletRequest request, HttpServletResponse response) {
        InfoVO infoVO = new InfoVO();

        // todo 验证是否已经登录
        UserVO userVO = CookieUtil.getLoginUser(request);
        if (userVO != null)
            return infoVO.createError(EnumInfoMessage.ACCOUNTS_ALREADY_LOGIN.getDesc());

        // 验证码校验
        boolean isVerified = authCodeService.verifyAuthCode(code, t, request);
        if (!isVerified) {
            return infoVO.createError(EnumInfoMessage.ACCOUNTS_VERFIY_AGAIN.getDesc());
        }

        // 邮箱验证
        if (email == null || StringUtils.isBlank(email) )
            return infoVO.createError(EnumInfoMessage.ACCOUNTS_EMAIL_INPUT_NEED.getDesc());
        // 密码验证
        if (password == null || StringUtils.isBlank(password))
            return infoVO.createError(EnumInfoMessage.ACCOUNTS_PASSWORD_ERROR.getDesc());

        User user = null;
        try {
            user = userService.register(email, password, nickName);
        } catch (MainException e) {
            infoVO.createError(e.getMessage());
        }
        return infoVO.createSuccess(EnumInfoMessage.OBJECT_SUCCESS.getDesc(), userService.getUserVO(user));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(String email, String password, HttpServletRequest request, HttpServletResponse response) {
        InfoVO infoVO = new InfoVO();

        if (password == null || email == null || "".equals(email))
            return infoVO.createError(EnumInfoMessage.OBJECT_FAILURE.getDesc());

//        User user = userService.login(email, password, request, response);

        return null;
    }

    @RequestMapping(value = "/userinfo")
    @ResponseBody
    public Object getUserinfo(HttpServletRequest request, HttpServletResponse response) {
        InfoVO infoVO = new InfoVO();

        // 判断登录状态
        UserVO userVO = CookieUtil.getLoginUser(request);
        if (userVO == null)
            return infoVO.createError(EnumInfoMessage.ACCOUNTS_NO_LOGIN.getDesc());
        return infoVO.createSuccess(EnumInfoMessage.OBJECT_SUCCESS.getDesc(), userVO);
    }
}
