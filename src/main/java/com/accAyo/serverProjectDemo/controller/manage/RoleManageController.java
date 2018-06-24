package com.accAyo.serverProjectDemo.controller.manage;

import com.accAyo.serverProjectDemo.util.CookieUtil;
import com.accAyo.serverProjectDemo.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午9:36 2018/6/24
 */

@Controller
@RequestMapping("/manage")
public class RoleManageController {

    @RequestMapping("/staff/add")
    public String addTest(HttpServletRequest request, HttpServletResponse response, Model model) {
        UserVO userVO = CookieUtil.getLoginUser(request);


        return "/staff/staff_add";
    }
}

