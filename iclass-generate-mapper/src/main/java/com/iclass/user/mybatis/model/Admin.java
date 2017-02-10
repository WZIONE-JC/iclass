package com.iclass.user.mybatis.model;

public class Admin {
    private Integer userid;

    private String username;

    private String fullname;

    private String userpwd;

    private String email;

    private String phonenumber;

    public Admin(Integer userid, String username, String fullname, String userpwd, String email, String phonenumber) {
        this.userid = userid;
        this.username = username;
        this.fullname = fullname;
        this.userpwd = userpwd;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public Admin() {
        super();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd == null ? null : userpwd.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber == null ? null : phonenumber.trim();
    }
}