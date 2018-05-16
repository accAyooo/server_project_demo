package com.accAyo.serverProjectDemo.service;

import com.accAyo.serverProjectDemo.common.ServiceResp;
import com.accAyo.serverProjectDemo.pojo.User;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 上午12:07 2018/5/11
 */
public interface IUserService {

    /**
     * 添加用户
     * @param user
     * @return
     */
    public ServiceResp<User> addUser(User user);

}
