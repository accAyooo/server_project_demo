package com.accAyo.serverProjectDemo.controller.manage;

import com.accAyo.serverProjectDemo.common.EnumInfoMessage;
import com.accAyo.serverProjectDemo.framework.util.ResultFilter;
import com.accAyo.serverProjectDemo.pojo.Access;
import com.accAyo.serverProjectDemo.service.impl.StaffService;
import com.accAyo.serverProjectDemo.service.impl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午9:36 2018/6/24
 */

@Controller
@RequestMapping("/manage/role")
public class RoleManageController {

    @Resource
    private StaffService staffService;

    @Resource
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addRolePage(HttpServletRequest request, HttpServletResponse response) {
        return "/role/role_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addRole(String roleName,
                          HttpServletRequest request, HttpServletResponse response, Model model) {

        return "/role/role_add";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String roleListPage(HttpServletRequest request, HttpServletResponse response, Model model) {


        return "/role/role_list";
    }

    @RequestMapping(value = "/access/list", method = RequestMethod.GET)
    public String accessListPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultFilter<Access> rf = staffService.listAccesses();
        return "/role/access_list";
    }

    @RequestMapping(value = "/access/add", method = RequestMethod.GET)
    public String addAccessPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/role/access_add";
    }

    @RequestMapping(value = "/access/add", method = RequestMethod.POST)
    public String addAccess(String name, String path,
                            HttpServletRequest request, HttpServletResponse response, Model model) {
        boolean isSuccess = staffService.addAccess(name, path);
        if (!isSuccess) {
            model.addAttribute("errorMessage", EnumInfoMessage.OBJECT_FAILURE);
            return "/role/access_add";
        }
        return "redirect:/manage/role/access/list";
    }

}

