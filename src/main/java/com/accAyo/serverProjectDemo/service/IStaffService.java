package com.accAyo.serverProjectDemo.service;

import com.accAyo.serverProjectDemo.common.EnumStaffType;
import com.accAyo.serverProjectDemo.framework.util.ResultFilter;
import com.accAyo.serverProjectDemo.pojo.Access;
import com.accAyo.serverProjectDemo.pojo.UserStaff;

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
}
