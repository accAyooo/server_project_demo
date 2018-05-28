package com.accAyo.serverProjectDemo.service;

import com.accAyo.serverProjectDemo.framwork.Exception.MainException;
import com.accAyo.serverProjectDemo.pojo.User;
import com.accAyo.serverProjectDemo.vo.UserVO;

import java.util.List;

/**
 * Desc:
 *
 * @author shixiangyu
 * @date 2018/5/28
 */
public interface IUserService {

    /**
     * 用户注册
     * @param email
     * @param password
     * @param nickName
     * @return
     */
    public User register(String email, String password, String nickName) throws MainException;

    /**
     * 昵称获取用户
     * @param name
     * @return
     */
    public List<User> getAllUserByName(String name);

    public List<User> getAllUserByEmail(String email);

    /**
     * 通过id获取用户
     * @param userId
     * @return
     */
    public User getUser(int userId);

    /**
     * 返回vo对象
     * @param user
     * @return
     */
    public UserVO getUserVO(User user);

    /**
     * 通过ID获取userVO对象
     * @param userId
     * @return
     */
    public UserVO getUserVO(int userId);
}
