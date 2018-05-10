package com.accAyo.serverProjectDemo.pojo;

import javax.persistence.*;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:47 2018/5/8
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "user_name")
    private String userName;

    private String password;

    private Integer age;

    private String email;

    public User() {
    }

    public User(String name, String userName, String password, Integer age, String email) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
