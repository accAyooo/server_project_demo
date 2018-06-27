package com.accAyo.serverProjectDemo.service.impl;

import com.accAyo.serverProjectDemo.common.Constants;
import com.accAyo.serverProjectDemo.common.EnumStaffType;
import com.accAyo.serverProjectDemo.common.EnumUserType;
import com.accAyo.serverProjectDemo.framework.hibernateDao.HibernateBaseService.BaseService;
import com.accAyo.serverProjectDemo.framework.hibernateDao.HibernateBaseService.CompareType;
import com.accAyo.serverProjectDemo.framework.hibernateDao.HibernateBaseService.HibernateExpression;
import com.accAyo.serverProjectDemo.framework.util.ResultFilter;
import com.accAyo.serverProjectDemo.pojo.*;
import com.accAyo.serverProjectDemo.service.IStaffService;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Desc:
 *
 * @author shixiangyu
 * @date 2018/6/26
 */

@Service
public class StaffService extends BaseService implements IStaffService {

    @Resource
    private UserService userService;

    @Override
    public boolean addStaff(int id, String staffName, EnumStaffType resultType) {
//        UserStaff testStaff = this.getObject(UserStaff.class, id);
//        System.out.println(testStaff.getName());

        User user = userService.getUser(id);
        if (user == null)
            return false;
        UserStaff staff = this.getObject(UserStaff.class, id);
        if (staff != null && staff.getStatus() != Constants.STATUS_DELETE)
            return false;

        staff = new UserStaff();
        staff.setType(resultType.getValue());
        staff.setName(staffName);
        staff.setCreateTime(user.getCreateTime());
        staff.setStatus(Constants.STATUS_NORMAL);

        if (staff.getId() == 0) {
            staff.setId(id);
            this.addObject(staff);
        } else {
            this.updateObject(staff);
        }

        user.setType(user.getType() | EnumUserType.STAFF.getValue());
        this.updateObject(user);
        return true;
    }

    @Override
    public boolean updateStaff(int id, String staffName, EnumStaffType resultType) {
        UserStaff staff = this.getUserStaffById(id);
        if (staff == null) {
            return false;
        }
        if (resultType != null) {
            staff.setType(resultType.getValue());
        }
        staff.setName(staffName);
        this.updateObject(staff);
        return true;
    }

    @Override
    public ResultFilter<UserStaff> listUserStaffByType(EnumStaffType enumStaffType, Boolean isNormal) {
        HashMap<String, Object> propertyMap = new HashMap<>();
        if (enumStaffType != null)
            propertyMap.put("type", enumStaffType.getValue());
        if (isNormal != null && !"".equals(isNormal))
            propertyMap.put("status", isNormal ? Constants.STATUS_NORMAL : Constants.STATUS_DELETE);
        Collection<HibernateExpression> expressions = formExpressionsByProperty(propertyMap, CompareType.Equal);
        return this.getAllObjects(UserStaff.class, expressions, true, "id");
    }

    @Override
    public UserStaff getUserStaffById(int id) {
        return getObject(UserStaff.class, id);
    }

    @Override
    public UserStaff getNormalUserStaffById(int id) {
        UserStaff staff = getUserStaffById(id);
        if (staff.getStatus() == Constants.STATUS_DELETE)
            return null;
        return staff;
    }

    @Override
    public void closeStaff(UserStaff staff) {
        staff.setStatus(Constants.STATUS_DELETE);
        this.updateObject(staff);
    }

    @Override
    public void openStaff(UserStaff staff) {
        staff.setStatus(Constants.STATUS_NORMAL);
        this.updateObject(staff);
    }

    @Override
    public boolean addAccess(String name, String path) {
        Access access = getObjectByProperty(Access.class, "path", path);
        if (access != null)
            return false;
        access = new Access();
        access.setName(name);
        access.setPath(path);

        this.addObject(access);
        return true;
    }

    @Override
    public ResultFilter<Access> listAccesses() {
        return this.getAllObjects(Access.class, new ArrayList<HibernateExpression>(0), true, "id");
    }

    @Override
    public Access getAccessById(int id) {
        return this.getObject(Access.class, id);
    }

    @Override
    public boolean updateAccess(int id, String path) {
        Access access = this.getAccessById(id);
        if (access == null) {
            return false;
        }
        access.setPath(path);
        this.updateObject(access);
        return true;
    }

    @Override
    public ResultFilter<Role> listAllRoles() {
        return this.getAllObjects(Role.class, new ArrayList<HibernateExpression>(0), true, "id");
    }

    @Override
    public Role addRole(String name) {
        Role role = this.getObjectByProperty(Role.class, "name", name);
        if (role != null)
            return null;
        role = new Role();
        role.setName(name);
        role.setStatus(Constants.STATUS_NORMAL);
        this.addObject(role);
        return role;
    }

    @Override
    public boolean addRoleAccess(int id, int accessId) {
        HashMap<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("role_id", id);
        propertyMap.put("access_id", accessId);
        Collection<HibernateExpression> expressions = formExpressionsByProperty(propertyMap, CompareType.Equal);
        ResultFilter<RoleAccess> rf = getObjects(RoleAccess.class, expressions, 1, 1);
        if (rf.getTotalCount() != 0) {
            return false;
        }
        RoleAccess ra = new RoleAccess();
        ra.setAccessId(accessId);
        ra.setRoleId(id);
        this.addObject(ra);
        return true;
    }
}
