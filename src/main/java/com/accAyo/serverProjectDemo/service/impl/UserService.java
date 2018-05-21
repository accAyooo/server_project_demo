package com.accAyo.serverProjectDemo.service.impl;

import com.accAyo.serverProjectDemo.common.*;
import com.accAyo.serverProjectDemo.framwork.Exception.MainException;
import com.accAyo.serverProjectDemo.framwork.hibernateDao.HibernateBaseService.BaseService;
import com.accAyo.serverProjectDemo.framwork.hibernateDao.HibernateBaseService.CompareType;
import com.accAyo.serverProjectDemo.framwork.hibernateDao.HibernateBaseService.HibernateExpression;
import com.accAyo.serverProjectDemo.pojo.User;
import com.accAyo.serverProjectDemo.service.IUserService;
import com.accAyo.serverProjectDemo.util.ResultFilter;
import com.accAyo.serverProjectDemo.util.ValidationUtil;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 上午12:07 2018/5/11
 */

@Service
public class UserService extends BaseService implements IUserService {

    @Override
    public User register(String email, String password, int cityId, String name, EnumBookGroupType groupType, EnumSiteType siteType, int spreadId) {
        return null;
    }

    @Override
    public User register(String email, String password, int cityId, String name, EnumBookGroupType groupType, EnumSiteType siteType) throws MainException {
        // 密码的校验
        if (password != null || password.length() < 6) {
            throw new MainException(EnumInfo.ACCOUNTS_PASSWORD_FORMAT_WRONG_FAILED, null);
        }

        // 检查用户名是否合法
        if (!this.validateName(name)) {
            throw new MainException(EnumInfo.ACCOUNTS_NICKNAME_ERROR, null);
        }

        // 检查邮箱是否合法
        if (!ValidationUtil.emailCheck(email)) {
            throw new MainException(EnumInfo.ACCOUNTS_EMAIL_ERROR, null);
        }

        // 检查用户是否已经存在
        if (this.getUserByEmail(email) != null) {
            throw new MainException(EnumInfo.ACCOUNTS_INVITED_REGISTER, null);
        }

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setCityId(cityId);
        user.setPassword(MD5.MD5(password));
        user.setRandom(this.);

    }

    private boolean validateName(String name) throws MainException {
        if (name == null) {
            throw new MainException(EnumInfo.ACCOUNTS_NICKNAME_REQUIRED, null);
        }
        if (name.length() < 2 || name.length() > 14) {
            throw new MainException(EnumInfo.NICKNAME_LENGTH_ERROR, null);
        }
        if (!ValidationUtil.realNameCheck(name)){
            throw new MainException(EnumInfo.NICKNAME_CHAR_ERROR, null);
        }
        // todo 验证名字是否合法 保留字或者违法 或者屏蔽字

        if (this.getAllUserByName(name) != null) {
            throw new MainException(EnumInfo.NICKNAME_EXIST_ERROR, null);
        }

        return true;
    }

    @Override
    public List<User> getAllUserByName(String name) {
        HashMap<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("name", name);
        Collection<HibernateExpression> expressions = formExpressionsByProperty(propertyMap, CompareType.Equal);
        ResultFilter<User> resultFilter = getAllObjects(User.class, expressions, true, "id");
        if (resultFilter.getTotalCount() > 0) {
            return resultFilter.getItems();
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        HashMap<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("email", email);
        Collection<HibernateExpression> expressions = formExpressionsByProperty(propertyMap, CompareType.Equal);
        ResultFilter<User> resultFilter = getSingleObject(User.class, expressions, 1, 1, true, "id");
        if (resultFilter.getTotalCount() > 0) {
            return resultFilter.getItems().get(0);
        }
        return null;
    }


}
