package com.accAyo.serverProjectDemo.pojo;

import com.accAyo.serverProjectDemo.common.EnumStaffType;
import com.accAyo.serverProjectDemo.common.EnumUserStatus;
import com.accAyo.serverProjectDemo.common.EnumUserType;
import com.accAyo.serverProjectDemo.vo.UserVO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:47 2018/6/25
 */

@Entity
@Table(name = "accounts_user_staff")
public class UserStaff implements Serializable{

    @Id
    private int id;

    @Column(length = 64)
    private String name;

    private byte type;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    private byte status;

//    @Transient
//    private List<Role> roleList;

    @Transient
    private UserVO user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }

    public EnumStaffType getEnumStaffType () {
        return EnumStaffType.getEnum(type);
    }
}
