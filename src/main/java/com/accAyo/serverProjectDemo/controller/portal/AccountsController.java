package com.accAyo.serverProjectDemo.controller.portal;

import com.accAyo.serverProjectDemo.common.EnumInfoMessage;
import com.accAyo.serverProjectDemo.framwork.Exception.EnumInfo;
import com.accAyo.serverProjectDemo.util.ValidationUtil;
import com.accAyo.serverProjectDemo.vo.InfoVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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

    @RequestMapping("/register")
    @ResponseBody
    public Object register(int code, String t, String nickName, String email, String password,
            HttpServletRequest request, HttpServletResponse response) {
        InfoVO infoVO = new InfoVO();

        // todo 验证是否已经登录

        // 邮箱验证
        if (email == null || StringUtils.isBlank(email) )
            return infoVO.createError(EnumInfo.ACCOUNTS_EMAIL_INPUT_NEED.getDesc());
        // 密码验证
        if (password == null || StringUtils.isBlank(password))
            return infoVO.createError(EnumInfoMessage.ACCOUNTS_PASSWORD_ERROR.getDesc());


        return null;
    }
}
