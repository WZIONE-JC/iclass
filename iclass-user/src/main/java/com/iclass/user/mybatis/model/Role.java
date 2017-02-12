package com.iclass.user.mybatis.model;

public class Role {
    private Integer roleid;

    private String rolename;

    private String rolebydevice;

    private String rolepermission;

    public Role(Integer roleid, String rolename, String rolebydevice, String rolepermission) {
        this.roleid = roleid;
        this.rolename = rolename;
        this.rolebydevice = rolebydevice;
        this.rolepermission = rolepermission;
    }

    public Role() {
        super();
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getRolebydevice() {
        return rolebydevice;
    }

    public void setRolebydevice(String rolebydevice) {
        this.rolebydevice = rolebydevice == null ? null : rolebydevice.trim();
    }

    public String getRolepermission() {
        return rolepermission;
    }

    public void setRolepermission(String rolepermission) {
        this.rolepermission = rolepermission == null ? null : rolepermission.trim();
    }
}