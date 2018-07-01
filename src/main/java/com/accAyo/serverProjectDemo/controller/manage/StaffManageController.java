package com.accAyo.serverProjectDemo.controller.manage;

import com.accAyo.serverProjectDemo.common.EnumInfoMessage;
import com.accAyo.serverProjectDemo.common.EnumStaffType;
import com.accAyo.serverProjectDemo.framework.util.ResultFilter;
import com.accAyo.serverProjectDemo.pojo.Role;
import com.accAyo.serverProjectDemo.pojo.RoleUser;
import com.accAyo.serverProjectDemo.pojo.User;
import com.accAyo.serverProjectDemo.pojo.UserStaff;
import com.accAyo.serverProjectDemo.service.impl.StaffService;
import com.accAyo.serverProjectDemo.service.impl.UserService;
import com.accAyo.serverProjectDemo.util.StringUtil;
import com.accAyo.serverProjectDemo.vo.UserVO;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    @Resource
    private StaffService staffService;

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
            model.addAttribute("types", EnumStaffType.values());
            return "/staff/staff_add";
        }

        boolean result = staffService.addStaff(user.getId(), staffName, resultType);
        if (!result) {
            model.addAttribute("errorMessage", EnumInfoMessage.ACCOUNTS_ADD_USER_FAILED.getDesc());
            model.addAttribute("types", EnumStaffType.values());
            return "/staff/staff_add";
        }
        return "redirect:/manage/staff/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String staffList(@RequestParam(required = false, defaultValue = "0")byte type, Boolean normal,
                            HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultFilter<UserStaff> staffResultFilter = staffService.listUserStaffByType(EnumStaffType.getEnum(type), normal);
        for (UserStaff staff : staffResultFilter.getItems()) {
            UserVO userVO = userService.getUserVO(staff.getId());
            staff.setUser(userVO);
        }

        model.addAttribute("staffRF", staffResultFilter);
        return "/staff/staff_list";
    }

    @RequestMapping(value = "/{id}/role", method = RequestMethod.GET)
    public String staffRolePage(@PathVariable int id,
                                HttpServletRequest request, HttpServletResponse response, Model model) {
        UserStaff userStaff = staffService.getUserStaffById(id);
        if (userStaff == null)
            return "/staff/staff_list";
        ResultFilter<Role> roleRF = staffService.listAllRoles();
        String roleStr = ",";
        ResultFilter<RoleUser> roleUserRF = staffService.listAllRoleUserByUserId(userStaff.getId());
        for (RoleUser ru : roleUserRF.getItems()) {
            roleStr += ru.getRoleId() + ",";
        }
        model.addAttribute("roleStr", roleStr);
        model.addAttribute("roleRF", roleRF);
        model.addAttribute("staff", userStaff);
        return "/staff/staff_role";
    }

    @RequestMapping(value = "/{id}/role", method = RequestMethod.POST)
    public String staffRoleAction(@PathVariable int id,
                                  HttpServletRequest request, HttpServletResponse response, Model model) {
        UserStaff userStaff = staffService.getUserStaffById(id);
        boolean isSuccess = staffService.deleteRoleUserByUserId(userStaff.getId());
        String[] roleStrs = request.getParameterValues("roles");
        if (roleStrs == null)
            return "redirect:/manage/staff/" + id + "/role";
        for (int i = 0; i < roleStrs.length; i ++) {
            int roleId = StringUtil.str2int(roleStrs[i]);
            if (roleId > 0) {
                staffService.addRoleUser(userStaff.getId(), roleId);
            }
        }
        return "redirect:/manage/staff/list";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String staffEdit(@PathVariable int id,
                            HttpServletRequest request, HttpServletResponse response, Model model) {
        UserStaff staff = staffService.getNormalUserStaffById(id);
        staff.setUser(userService.getUserVO(staff.getId()));
        model.addAttribute("types", EnumStaffType.values());
        model.addAttribute("staff", staff);
        return "/staff/staff_edit";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String staffEditPost(@PathVariable int id, String staffName, int staffType,
                            HttpServletRequest request, HttpServletResponse response, Model model) {
        EnumStaffType enumStaffType = EnumStaffType.getEnum(staffType);
        staffService.updateStaff(id, staffName ,enumStaffType);
        return "redirect:/manage/staff/list";
    }

    @RequestMapping(value = "/{id}/del", method = RequestMethod.GET)
    public String staffDel(@PathVariable int id,
                           HttpServletRequest request, HttpServletResponse response, Model model) {
        UserStaff staff = staffService.getNormalUserStaffById(id);
        if (staff != null)
            staffService.closeStaff(staff);
        return "redirect:/manage/staff/list";
    }

    @RequestMapping(value = "/{id}/add", method = RequestMethod.GET)
    public String staffAdd(@PathVariable int id,
                           HttpServletRequest request, HttpServletResponse response, Model model) {
        UserStaff staff = staffService.getUserStaffById(id);
        if (staff != null)
            staffService.openStaff(staff);
        return "redirect:/manage/staff/list";
    }
}
