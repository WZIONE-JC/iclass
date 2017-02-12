package com.iclass.user.mybatis.model;

public class Admin {
    private Integer adminid;

    private String admincode;

    public Admin(Integer adminid, String admincode) {
        this.adminid = adminid;
        this.admincode = admincode;
    }

    public Admin() {
        super();
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public String getAdmincode() {
        return admincode;
    }

    public void setAdmincode(String admincode) {
        this.admincode = admincode == null ? null : admincode.trim();
    }
}