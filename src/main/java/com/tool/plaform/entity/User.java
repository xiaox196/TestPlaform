package com.tool.plaform.entity;

import java.sql.Timestamp;

public class User {
    private  int id;
    private String name;
    private String password;
    private Timestamp createtime;
    private Boolean enable;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password ==null?null:password.trim();
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", createtime=" + createtime +
                ", enable=" + enable +
                '}';
    }
}
