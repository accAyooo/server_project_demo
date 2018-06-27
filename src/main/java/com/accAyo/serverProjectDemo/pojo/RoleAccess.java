package com.accAyo.serverProjectDemo.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Desc:
 *
 * @author shixiangyu
 * @date 2018/6/27
 */

@Entity
@Table(name = "manage_role_access")
public class RoleAccess implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role_id")
    private int roleId;
    @Column(name = "access_id")
    private int accessId;
    private byte status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getAccessId() {
        return accessId;
    }

    public void setAccessId(int accessId) {
        this.accessId = accessId;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
