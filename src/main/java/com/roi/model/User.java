package com.roi.model;

public class User {
    private Integer status;
    private String name;
    private Integer id;

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getStatus() {
        return status;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
}