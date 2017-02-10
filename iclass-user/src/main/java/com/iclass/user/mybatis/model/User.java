package com.iclass.user.mybatis.model;

public class User {
    private Integer userid;

    private String usercode;

    private String username;

    private String usersex;

    private String userpwd;

    private String userbirth;

    private String userattributes;

    public User(Integer userid, String usercode, String username, String usersex, String userpwd, String userbirth, String userattributes) {
        this.userid = userid;
        this.usercode = usercode;
        this.username = username;
        this.usersex = usersex;
        this.userpwd = userpwd;
        this.userbirth = userbirth;
        this.userattributes = userattributes;
    }

    public User() {
        super();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex == null ? null : usersex.trim();
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd == null ? null : userpwd.trim();
    }

    public String getUserbirth() {
        return userbirth;
    }

    public void setUserbirth(String userbirth) {
        this.userbirth = userbirth == null ? null : userbirth.trim();
    }

    public String getUserattributes() {
        return userattributes;
    }

    public void setUserattributes(String userattributes) {
        this.userattributes = userattributes == null ? null : userattributes.trim();
    }
}