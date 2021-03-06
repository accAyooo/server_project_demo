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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

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
        propertyMap.put("roleId", id);
        propertyMap.put("accessId", accessId);
        Collection<HibernateExpression> expressions = formExpressionsByProperty(propertyMap, CompareType.Equal);
        ResultFilter<RoleAccess> RAs = this.getSingleObject(RoleAccess.class, expressions, 1, 1, false, "id");
        if (RAs.getTotalCount() != 0) {
            return false;
        }
        RoleAccess ra = new RoleAccess();
        ra.setAccessId(accessId);
        ra.setRoleId(id);
        this.addObject(ra);
        return true;
    }

    @Override
    public Role getRoleById(int id) {
        return getObject(Role.class, id);
    }

    @Override
    public ResultFilter<Access> listRoleAccessByRoleId(int roleId) {
        HashMap<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("roleId", roleId);
        Collection<HibernateExpression> expressions = formExpressionsByProperty(propertyMap, CompareType.Equal);
        ResultFilter<RoleAccess> raRF = null;
        raRF = this.getObjects(RoleAccess.class, this.formExpressionsByProperty(propertyMap, CompareType.Equal), Integer.MAX_VALUE, 1);
//        raRF = this.getHibernateGenericController().findBy(RoleAccess.class, 1, Integer.MAX_VALUE, "id", false, expressions);

        List<Access> result = new ArrayList<>();
        for (RoleAccess ra : raRF.getItems()) {
            result.add(this.getAccessById(ra.getAccessId()));
        }

        ResultFilter<Access> accessRF = new ResultFilter<Access>(result.size(), 1, 1);
        accessRF.setItems(result);
        return  accessRF;
    }

    @Override
    public boolean deleteAllRoleAccessByRoleId(int roleId) {
        HashMap<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("roleId", roleId);
        Collection<HibernateExpression> expressions = formExpressionsByProperty(propertyMap, CompareType.Equal);
        ResultFilter<RoleAccess> raRF = getObjects(RoleAccess.class, expressions, 1, Integer.MAX_VALUE, true, "id");
        for (RoleAccess ra : raRF.getItems()) {
            this.deleteRoleAccessById(ra.getId());
        }
        return true;
    }

    @Override
    public boolean deleteRoleAccessById(int raId) {
        RoleAccess ra = this.getObject(RoleAccess.class, raId);
        if (ra == null) {
            return false;
        }
        this.deleteObject(ra);
        return true;
    }

    @Override
    public boolean deleteRoleById(int id) {
        Role role = getRoleById(id);
        this.deleteObject(role);
        return true;
    }

    @Override
    public boolean deleteAllRoleAccessByAccessId(int accessId) {
        Access access = this.getAccessById(accessId);
        if (access == null)
            return false;
        ResultFilter<RoleAccess> rf = this.getRoleAccessByAccessId(access.getId());
        for (RoleAccess ra : rf.getItems()) {
            this.deleteObject(ra);
        }

        return true;
    }


    @Override
    public ResultFilter<RoleAccess> getRoleAccessByAccessId(int accessId) {
        HashMap<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("accessId", accessId);
        Collection<HibernateExpression> expressions = formExpressionsByProperty(propertyMap, CompareType.Equal);
        return this.getObjects(RoleAccess.class, expressions, 1, Integer.MAX_VALUE, true, "id");
    }

    @Override
    public boolean deleteAccessById(int accessId) {
        Access access = this.getAccessById(accessId);
        if (access == null) {
            return false;
        }
        this.deleteObject(access);
        return true;
    }

    @Override
    public RoleUser addRoleUser(int id, int roleId) {
        User user = userService.getUser(id);
        Role role = this.getRoleById(roleId);
        if (role == null || user == null)
            return null;

        RoleUser roleUser = new RoleUser();
        roleUser.setRoleId(role.getId());
        roleUser.setUserId(user.getId());
        roleUser.setStatus(Constants.STATUS_NORMAL);
        this.addObject(roleUser);
        return roleUser;
    }

    @Override
    public ResultFilter<RoleUser> listAllRoleUserByUserId(int id) {
        HashMap<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("userId", id);
        return this.getObjectsByProperty(RoleUser.class, propertyMap, CompareType.Equal, Integer.MAX_VALUE, 1, true, "id");
    }

    @Override
    public boolean deleteRoleUserByUserId(int id) {
        ResultFilter<RoleUser> ruRF = this.listAllRoleUserByUserId(id);
        for (RoleUser ru : ruRF.getItems()) {
            this.deleteObject(ru);
        }
        return true;
    }

}
