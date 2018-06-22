package com.accAyo.serverProjectDemo.service.impl;

import com.accAyo.serverProjectDemo.common.Constants;
import com.accAyo.serverProjectDemo.common.EnumInfoMessage;
import com.accAyo.serverProjectDemo.framwork.Exception.EnumInfo;
import com.accAyo.serverProjectDemo.framwork.Exception.MainException;
import com.accAyo.serverProjectDemo.framwork.hibernateDao.HibernateBaseService.BaseService;
import com.accAyo.serverProjectDemo.framwork.hibernateDao.HibernateBaseService.CompareType;
import com.accAyo.serverProjectDemo.framwork.hibernateDao.HibernateBaseService.HibernateExpression;
import com.accAyo.serverProjectDemo.framwork.util.ResultFilter;
import com.accAyo.serverProjectDemo.pojo.User;
import com.accAyo.serverProjectDemo.service.IUserService;
import com.accAyo.serverProjectDemo.util.MD5;
import com.accAyo.serverProjectDemo.util.StringUtil;
import com.accAyo.serverProjectDemo.util.ValidationUtil;
import com.accAyo.serverProjectDemo.vo.UserVO;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import sun.applet.Main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Desc:
 *
 * @author shixiangyu
 * @date 2018/5/28
 */

@Service
public class UserService extends BaseService implements IUserService {
    @Override
    public User register(String email, String password, String nickName) throws MainException {
        if (!ValidationUtil.pwdCheck(password)) {
            throw new MainException(EnumInfoMessage.ACCOUNTS_USER_PASSWORD_ERROR, null);
        }
        // 验证昵称
        if (!validateName(nickName))
            throw new MainException(EnumInfoMessage.ACCOUNTS_NICKNAME_ERROR, null);
        // 邮箱验证
        email = email.trim().toLowerCase();
        if (!ValidationUtil.emailCheck(email))
            throw new MainException(EnumInfoMessage.ACCOUNTS_EMAIL_ERROR, null);
        if (getAllUserByEmail(email) != null)
            throw new MainException(EnumInfoMessage.ACCOUNTS_INVITED_REGISTER, null);

        User user = new User();
        user.setEmail(email);
        user.setPassword(MD5.MD5(password));
        user.setNickName(nickName);
        user.setRandom(getNewRandom(user.getRandom()));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        // todo 不理解
        String verify = MD5.MD5(user.getRandom() + email);
        while(getObjectByProperty(User.class, "verify", verify) != null){
            user.setRandom(this.getNewRandom(user.getRandom()));
            verify = MD5.MD5(user.getRandom() + email);
        }
        user.setVerify(verify);
        this.addObject(user);

        // todo 创建一个任务

        return user;
    }

    @Override
    public List<User> getAllUserByName(String name) {
        HashMap<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("nickName", name);
        Collection<HibernateExpression> expressions = formExpressionsByProperty(propertyMap, CompareType.Equal);
        ResultFilter<User> rf = getAllObjects(User.class, expressions, true, "id");
        if (rf.getTotalCount() > 0) {
            return rf.getItems();
        }
        return null;
    }

    @Override
    public List<User> getAllUserByEmail(String email) {
        HashMap<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("email", email);
        Collection<HibernateExpression> expressions = formExpressionsByProperty(propertyMap, CompareType.Equal);
        ResultFilter<User> rf = getAllObjects(User.class, expressions, true, "id");
        if (rf.getTotalCount() > 0) {
            return rf.getItems();
        }
        return null;
    }

    @Override
    public User getUser(int userId) {
        return getObject(User.class, userId);
    }

    @Override
    public UserVO getUserVO(User user) {
        if (user == null)
            return null;
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setName(user.getNickName());
        userVO.setIcon(user.getIcon());
        userVO.setType(user.getType());
        userVO.setMark(user.getMark());
        userVO.setStatus(user.getStatus());
        return userVO;
    }

    @Override
    public UserVO getUserVO(int userId) {
        User user = getUser(userId);
        if (user == null)
            return null;
        return getUserVO(user);
    }

    @Override
    public User login(String email, String password, HttpServletRequest request, HttpServletResponse response) throws MainException {
        User user = getUserByEmail(email);
        if (user == null)
            throw new MainException(EnumInfoMessage.ACCOUNTS_EMAIL_NOT_EXISIT, null);
        if (!user.getPassword().equals(MD5.MD5(password)))
            throw new MainException(EnumInfoMessage.ACCOUNTS_USER_PASSWORD_ERROR, null);
        if (user.getStatus() < 0)
            throw new MainException(EnumInfoMessage.ACTIVE_USER_STATUS, null);

        randomUser(user, request);
        return user;
    }

    @Override
    public void logout(int userId, HttpServletRequest request) {
        User user = getUser(userId);
        randomUser(user, request);
    }

    private void randomUser(User user, HttpServletRequest request) {
        user.setRandom(getNewRandom(user.getRandom(), request));
        updateObject(user);
    }

    private User getUserByEmail(String email) {
        User user = null;
        HashMap<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("email", email);
        Collection<HibernateExpression> expressions = formExpressionsByProperty(propertyMap, CompareType.Equal);
        ResultFilter<User> userRF = getSingleObject(User.class, expressions, 1, 1, true, "id");

        if (userRF.getTotalCount() > 0) {
            System.out.println(1);
            user = userRF.getItems().get(0);
        }
        return user;
    }

    private boolean validateName(String name) throws MainException {
        if (name == null)
            throw new MainException(EnumInfoMessage.ACCOUNTS_NICKNAME_ERROR, null);
        if (name.length() < 2 || name.length() > 14)
            throw new MainException(EnumInfoMessage.NICKNAME_LENGTH_ERROR, null);
        if (!ValidationUtil.realNameCheck(name))
            throw new MainException(EnumInfoMessage.NICKNAME_CHAR_ERROR, null);
        List<User> result = getAllUserByName(name);
        if (result != null) {
            throw new MainException(EnumInfoMessage.NICKNAME_EXIST_ERROR, null);
        }
        return true;
    }

    private String getNewRandom(String oldRandom, HttpServletRequest request) {
        oldRandom = oldRandom + "00000000000000000000000000000000000000000000000000000000000000000000000000000000";
        String newRandom = "";
        String oldOneRandom = oldRandom.substring(1 * Constants.RANDOM_ONE_LENGTH, 2 * Constants.RANDOM_ONE_LENGTH);
        newRandom = oldRandom.substring(0, 1 * Constants.RANDOM_ONE_LENGTH) + StringUtil.getOneRandom(oldOneRandom) + oldRandom.substring(2 * Constants.RANDOM_ONE_LENGTH);

        return newRandom.substring(0, Constants.RANDOM_ONE_LENGTH * 5);
    }

    private String getNewRandom(String oldRandom) {
        oldRandom = oldRandom + "00000000000000000000000000000000000000000000000000000000000000000000000000000000";
        return StringUtil.getOneRandom(oldRandom.substring(0, Constants.RANDOM_ONE_LENGTH))
                + StringUtil.getOneRandom(oldRandom.substring(Constants.RANDOM_ONE_LENGTH, 2 * Constants.RANDOM_ONE_LENGTH))
                + StringUtil.getOneRandom(oldRandom.substring(2 * Constants.RANDOM_ONE_LENGTH, 3 * Constants.RANDOM_ONE_LENGTH))
                + StringUtil.getOneRandom(oldRandom.substring(3 * Constants.RANDOM_ONE_LENGTH, 4 * Constants.RANDOM_ONE_LENGTH))
                + StringUtil.getOneRandom(oldRandom.substring(4 * Constants.RANDOM_ONE_LENGTH));
    }

}
