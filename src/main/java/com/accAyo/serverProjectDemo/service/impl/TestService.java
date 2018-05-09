package com.accAyo.serverProjectDemo.service.impl;

import com.accAyo.serverProjectDemo.dao.impl.TestDaoImpl;
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
    TestDaoImpl testDaoImpl;

    @Override
    public String returnString() {
        String result = testDaoImpl.daoTest();
        return result;
    }
}
