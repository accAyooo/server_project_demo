package com.accAyo.serverProjectDemo.controller.manage;

import com.accAyo.serverProjectDemo.common.EnumInfoMessage;
import com.accAyo.serverProjectDemo.framework.util.ResultFilter;
import com.accAyo.serverProjectDemo.pojo.Access;
import com.accAyo.serverProjectDemo.pojo.Role;
import com.accAyo.serverProjectDemo.service.impl.StaffService;
import com.accAyo.serverProjectDemo.service.impl.UserService;
import com.accAyo.serverProjectDemo.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String addRolePage(HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultFilter<Access> accessRF = staffService.listAccesses();
        model.addAttribute("accessRF", accessRF);
        return "/role/role_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addRoleAction(String name,
                          HttpServletRequest request, HttpServletResponse response, Model model) {
        String[] accessesId = request.getParameterValues("access");
        Role role = staffService.addRole(name);
        if (role == null)
            return "redirect:/manage/role/add";

        for (String accessStr : accessesId) {
            int accessId = StringUtil.str2int(accessStr);
            if (accessId > 0) {
                staffService.addRoleAccess(role.getId(), accessId);
            }
        }
        return "redirect:/manage/role/list";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editRolePage(@PathVariable int id,
                               HttpServletRequest request, HttpServletResponse response, Model model) {
        Role role = staffService.getRoleById(id);
        ResultFilter<Access> raRF = staffService.listRoleAccessByRoleId(role.getId());
        ResultFilter<Access> accessRF = staffService.listAccesses();

        String accessStr = ",";
        for (Access a : raRF.getItems()) {
            accessStr += (a.getId() + ",");
        }

        model.addAttribute("raRF", raRF);
        model.addAttribute("accessRF", accessRF);
        model.addAttribute("role", role);
        model.addAttribute("accessStr", accessStr);
        return "/role/role_edit";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String editRoleAction(@PathVariable int id,
                                 HttpServletRequest request, HttpServletResponse response, Model model) {

        Role role = staffService.getRoleById(id);
        // 先删除
        boolean delSuccess = staffService.deleteAllRoleAccessByRoleId(role.getId());
        if (!delSuccess)
            return "redirect:/manage/role/" + id + "/edit";
        String[] accessStr = request.getParameterValues("access");
        if (accessStr == null) {
            return "redirect:/manage/role/" + id + "/edit";
        }
        for (int i = 0; i < accessStr.length; i ++) {
            int accessId = StringUtil.str2int(accessStr[i]);
            if (accessId > 0) {
                staffService.addRoleAccess(role.getId(), accessId);
            }
        }
        return "redirect:/manage/role/list";
    }

    @RequestMapping(value = "/{id}/del", method = RequestMethod.GET)
    public String deleteRoleAction(@PathVariable int id,
                                   HttpServletRequest request, HttpServletResponse response) {
        Role role = staffService.getRoleById(id);
        if (role != null) {
            boolean isSuccess = staffService.deleteAllRoleAccessByRoleId(role.getId());
            if (isSuccess) {
                staffService.deleteRoleById(role.getId());
            }
        }
        return "redirect:/manage/role/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String roleListPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultFilter<Role> roleRF = staffService.listAllRoles();
        model.addAttribute("roleRF", roleRF);
        return "/role/role_list";
    }

    @RequestMapping(value = "/access/list", method = RequestMethod.GET)
    public String accessListPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultFilter<Access> rf = staffService.listAccesses();
        model.addAttribute("rf", rf);
        return "/role/access_list";
    }

    @RequestMapping(value = "/access/{id}/edit", method = RequestMethod.GET)
    public String accessEditPage(@PathVariable int id,
                                 HttpServletRequest request, HttpServletResponse response, Model model) {
        Access access = staffService.getAccessById(id);
        if (access == null) {
            model.addAttribute("errorMessage", EnumInfoMessage.OBJECT_NOT_EXIST.getDesc());
            return "/role/access_edit";
        }
        model.addAttribute("access", access);
        return "/role/access_edit";
    }

    @RequestMapping(value = "/access/{id}/edit", method = RequestMethod.POST)
    public String accessEdit(@PathVariable int id, String path,
                             HttpServletRequest request, HttpServletResponse response, Model model) {
        boolean isSuccess = staffService.updateAccess(id, path);
        if (!isSuccess) {
            model.addAttribute("errorMessage", EnumInfoMessage.OBJECT_FAILURE.getDesc());
            return "/role/access_edit";
        }
        return "redirect:/manage/role/access/list";
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

    @RequestMapping(value = "/access/{accessId}/del", method = RequestMethod.GET)
    public String delAccessAction(@PathVariable int accessId,
                                  HttpServletRequest request, HttpServletResponse response, Model model) {
        Access access = staffService.getAccessById(accessId);
        if (access != null) {
            boolean isSuccess = staffService.deleteAllRoleAccessByAccessId(access.getId());
        }
        return "redirect:/manage/role/access/list";
    }
}

