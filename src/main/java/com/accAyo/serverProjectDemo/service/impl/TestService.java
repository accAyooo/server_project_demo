package com.accAyo.serverProjectDemo.service.impl;

import com.accAyo.serverProjectDemo.dao.impl.BaseDaoImpl;
import com.accAyo.serverProjectDemo.pojo.User;
import com.accAyo.serverProjectDemo.service.ITestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午10:45 2018/5/8
 */
@Service
public class TestService implements ITestService {

    @Resource
    BaseDaoImpl baseDaoImpl;

    @Override
    public String saveUser() {
        User user = new User("石翔宇", "accAyo", "xiangyang12", 18, "shixiangyu01@icloud.com");
        baseDaoImpl.save(user);
        return "success";
    }
}
