package com.accAyo.serverProjectDemo.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午8:59 2018/7/1
 */

public interface IManageService {

    /**
     * 是否允许访问当前的URL
     * @param userId
     * @param path
     * @return
     */
    public boolean isAccess(int userId, String path);
}
