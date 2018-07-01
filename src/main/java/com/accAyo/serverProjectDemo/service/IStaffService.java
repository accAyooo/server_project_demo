package com.accAyo.serverProjectDemo.service;

import com.accAyo.serverProjectDemo.common.EnumStaffType;
import com.accAyo.serverProjectDemo.framework.util.ResultFilter;
import com.accAyo.serverProjectDemo.pojo.*;

/**
 * Desc:
 *
 * @author shixiangyu
 * @date 2018/6/26
 */
public interface IStaffService {

    /**
     * 添加工作人员
     * @param id
     * @param staffName
     * @param resultType
     * @return
     */
    public boolean addStaff(int id, String staffName, EnumStaffType resultType);

    /**
     * 更新工作人员信息
     * @param id
     * @param staffName
     * @param resultType
     * @return
     */
    public boolean updateStaff(int id, String staffName, EnumStaffType resultType);

    /**
     * 通过员工类型获取员工
     * @param enumStaffType
     * @param isNormal
     * @return
     */
    public ResultFilter<UserStaff> listUserStaffByType(EnumStaffType enumStaffType, Boolean isNormal);

    /**
     * 通过ID获取员工
     * @param id
     * @return
     */
    public UserStaff getUserStaffById(int id);

    /**
     * 获取非关闭员工
     * @param id
     * @return
     */
    public UserStaff getNormalUserStaffById(int id);

    /**
     * 关闭权限
     * @param staff
     */
    void closeStaff(UserStaff staff);

    /**
     * 开启权限
     */
    void openStaff(UserStaff staff);

    /**
     * 添加权限
     * @param name
     * @param path
     * @return
     */
    boolean addAccess(String name, String path);

    /**
     * 获取权限列表（所有）
     * @return
     */
    ResultFilter<Access> listAccesses();

    /**
     * 通过ID获取权限
     * @param id
     * @return
     */
    Access getAccessById(int id);

    /**
     * 更新权限
     * @param id
     * @param path
     * @return
     */
    boolean updateAccess(int id, String path);

    /**
     * 获取所有角色
     * @return
     */
    ResultFilter<Role> listAllRoles();

    /** 添加角色
     * @param name
     */
    Role addRole(String name);

    /**
     * 绑定角色和权限
     * @param id
     * @param accessId
     * @return
     */
    boolean addRoleAccess(int id, int accessId);

    /**
     * 通过id查找角色
     * @param id
     * @return
     */
    Role getRoleById(int id);

    ResultFilter<Access> listRoleAccessByRoleId(int roleId);

    /**
     * 删除角色下的所有权限
     * @param roleId
     * @return
     */
    boolean deleteAllRoleAccessByRoleId(int roleId);

    /**
     * 通过ID删除RoleAccess
     */
    boolean deleteRoleAccessById(int raId);

    /**
     * 通过ID删除角色
     * @param id
     */
    boolean deleteRoleById(int id);

    /**
     * 通过权限ID删除所有RoleAccess
     * @param accessId
     * @return
     */
    boolean deleteAllRoleAccessByAccessId(int accessId);

    /**
     * 通过权限ID
     * @param accessId
     * @return
     */
    ResultFilter<RoleAccess> getRoleAccessByAccessId(int accessId);

    /**
     * 删除权限
     * @param accessId
     * @return
     */
    boolean deleteAccessById(int accessId);

    /**
     * 添加roleUser
     * @param id
     * @param roleId
     */
    RoleUser addRoleUser(int id, int roleId);

    /**
     * 通过用户ID获取用户的所有角色
     * @param id
     * @return
     */
    ResultFilter<RoleUser> listAllRoleUserByUserId(int id);

    /**
     * 删除用户下的所有角色
     * @param id
     * @return
     */
    boolean deleteRoleUserByUserId(int id);


}
