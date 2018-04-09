package com.tool.plaform.entity;

import org.springframework.data.annotation.Id;

import java.util.List;

public class HttpBean {
    public int id;
    public List<Integer> ids;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
