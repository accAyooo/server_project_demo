package com.accAyo.serverProjectDemo.service;

import com.accAyo.serverProjectDemo.common.EnumBookGroupType;
import com.accAyo.serverProjectDemo.common.EnumSiteType;
import com.accAyo.serverProjectDemo.common.ServiceResp;
import com.accAyo.serverProjectDemo.framwork.Exception.MainException;
import com.accAyo.serverProjectDemo.pojo.User;

import java.util.List;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 上午12:07 2018/5/11
 */
public interface IUserService {

    /**
     * 用户注册
     * @param email
     * @param password
     * @param cityId
     * @param name
     * @param groupType
     * @param siteType
     * @param spreadId
     * @return
     */
    public User register(String email, String password, int cityId, String name, EnumBookGroupType groupType, EnumSiteType siteType, int spreadId);

    /**
     * 用户注册
     * @param email
     * @param password
     * @param cityId
     * @param name
     * @param groupType
     * @param siteType
     * @return
     */
    public User register(String email,String password,int cityId, String name, EnumBookGroupType groupType, EnumSiteType siteType) throws MainException;

    /**
     * 根据名字获取用户
     * @param name
     * @return
     */
    public List<User> getAllUserByName(String name);


    public User getUserByEmail(String email);
}
