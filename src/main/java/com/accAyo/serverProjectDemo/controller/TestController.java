package com.accAyo.serverProjectDemo.controller;

import com.accAyo.serverProjectDemo.service.impl.TestService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午10:39 2018/5/8
 */

@Controller
@RequestMapping("/test")
public class TestController {
    @Resource
    TestService testService;

    @RequestMapping("/test")
    public String test(HttpServletRequest request, HttpServletResponse response, Model m) {
        String result = testService.saveUser();
        m.addAttribute("result", result);
        return "/test";
    }
}
