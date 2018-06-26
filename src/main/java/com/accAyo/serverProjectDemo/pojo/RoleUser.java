package com.accAyo.serverProjectDemo.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Desc:
 *
 * @author shixiangyu
 * @date 2018/6/26
 */

@Entity
@Table(name = "accounts_manage_role_user")
public class RoleUser implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "role_id")
    private int roleId;
    private byte status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}

