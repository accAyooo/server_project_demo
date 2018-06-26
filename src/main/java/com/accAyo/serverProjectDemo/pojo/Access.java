package com.accAyo.serverProjectDemo.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午10:36 2018/6/26
 */

@Entity
@Table(name = "accounts_manage_role_access")
public class Access implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String path;
    private byte status;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
