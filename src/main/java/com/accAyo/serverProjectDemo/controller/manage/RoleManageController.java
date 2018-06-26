package com.accAyo.serverProjectDemo.controller.manage;

import com.accAyo.serverProjectDemo.common.EnumStaffType;
import com.accAyo.serverProjectDemo.framework.util.ResultFilter;
import com.accAyo.serverProjectDemo.pojo.UserStaff;
import com.accAyo.serverProjectDemo.service.impl.UserService;
import com.accAyo.serverProjectDemo.util.CookieUtil;
import com.accAyo.serverProjectDemo.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    private UserService userService;

//    @RequestMapping(value = "/staff")
//    public String listStaff(@RequestParam(required = false, defaultValue = "0")byte type, Boolean normal,
//            HttpServletRequest request, HttpServletResponse response, Model model) {
//        ResultFilter<UserStaff> staffResultFilter = userService.listUserStaffByType(EnumStaffType.getEnum(type), normal);
//        model.addAttribute("staffRF", staffResultFilter);
//        return "/staff/staff_list";
//    }
}

