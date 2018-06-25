package com.accAyo.serverProjectDemo.controller.manage;

import com.accAyo.serverProjectDemo.common.EnumInfoMessage;
import com.accAyo.serverProjectDemo.common.EnumStaffType;
import com.accAyo.serverProjectDemo.framework.util.ResultFilter;
import com.accAyo.serverProjectDemo.pojo.User;
import com.accAyo.serverProjectDemo.service.impl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午10:59 2018/6/25
 */

@Controller
@RequestMapping("/manage/staff")
public class StaffManageController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addStaff(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("types", EnumStaffType.values());
        return "/staff/staff_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStaff(String email, String staffName, byte staffType,
                           HttpServletRequest request, HttpServletResponse response, Model model) {
        User user = userService.getNormalUserByEmail(email);
        EnumStaffType resultType = EnumStaffType.getEnum(staffType);

        if (user == null || resultType == null) {
            model.addAttribute("errorMessage", EnumInfoMessage.ACCOUNTS_ADD_USER_FAILED.getDesc());
            return "redirect:/staff/add";
        }


        boolean result = userService.addStaff(user.getId(), staffName, resultType);
        return "/staff/staff_add";
    }
}
