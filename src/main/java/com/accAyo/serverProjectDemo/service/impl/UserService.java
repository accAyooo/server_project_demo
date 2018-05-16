package com.accAyo.serverProjectDemo.service.impl;

import com.accAyo.serverProjectDemo.common.ServiceResp;
import com.accAyo.serverProjectDemo.pojo.User;
import com.accAyo.serverProjectDemo.service.HibernateBaseService.BaseService;
import com.accAyo.serverProjectDemo.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 上午12:07 2018/5/11
 */

@Service
public class UserService extends BaseService implements IUserService {

    @Override
    public ServiceResp<User> addUser(User user) {
        if (user.getName() == null) {
            return ServiceResp.createByErrorMessage("昵称不能为空");
        }
        if (user.getUserName() == null) {
            return ServiceResp.createByErrorMessage("用户名不能为空");
        }

        if (user.getPassword() == null) {
            return ServiceResp.createByErrorMessage("密码不能为空");
        }

        addObject(user);

        return ServiceResp.createBySuccess(user);
    }
}
