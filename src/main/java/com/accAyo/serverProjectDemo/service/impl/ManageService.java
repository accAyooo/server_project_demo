package com.accAyo.serverProjectDemo.service.impl;

import com.accAyo.serverProjectDemo.framework.util.ResultFilter;
import com.accAyo.serverProjectDemo.pojo.*;
import com.accAyo.serverProjectDemo.service.IManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午9:01 2018/7/1
 */

@Service
public class ManageService implements IManageService{

    @Resource
    private UserService userService;
    @Resource
    private StaffService staffService;


    @Override
    public boolean isAccess(int userId, String path) {
        User user = userService.getUser(userId);
        if (user == null)
            return false;
        ResultFilter<RoleUser> ruRf = staffService.listAllRoleUserByUserId(user.getId());
        if (ruRf.getTotalCount() == 0)
            return false;
        for (RoleUser ru : ruRf.getItems()) {
            ResultFilter<Access> aRF = staffService.listRoleAccessByRoleId(ru.getRoleId());
            if (aRF.getItems() != null) {
                for (Access a : aRF.getItems()) {
                    if (a.getPath() != null && path.startsWith(a.getPath())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
