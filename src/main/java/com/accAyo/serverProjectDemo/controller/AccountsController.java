package com.accAyo.serverProjectDemo.controller;

import com.accAyo.serverProjectDemo.common.ServiceResp;
import com.accAyo.serverProjectDemo.pojo.User;
import com.accAyo.serverProjectDemo.service.impl.UserService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 上午12:36 2018/5/11
 */

@Controller
@RequestMapping("/user")
public class AccountsController {
    @Resource
    UserService userService;

    @RequestMapping("/add_user")
    public String addUser(String name, String username, String password, int age, String email,
                          HttpServletRequest request, HttpServletResponse response, Model m) {
        User user = new User(name, username, password, age, email);
        ServiceResp<User> serviceResp = userService.addUser(user);

        if (serviceResp.isSuccess()) {
            User userVO = serviceResp.getData();
            m.addAttribute("name", userVO.getName());
            return "add_user";
        }

        m.addAttribute("error", serviceResp.getMessage());
        return "error";
    }
}
