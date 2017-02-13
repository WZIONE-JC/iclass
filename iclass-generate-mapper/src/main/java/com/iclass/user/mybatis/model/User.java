package com.iclass.user.mybatis.model;

import java.util.Date;

public class User {
    private Integer userid;

    private String usercode;

    private String username;

    private String userfullname;

    private String usersex;

    private String userpassword;

    private String userbirth;

    private String useremail;

    private String userphonenum;

    private String userrole;

    private Date userregisterdate;

    public User(Integer userid, String usercode, String username, String userfullname, String usersex, String userpassword, String userbirth, String useremail, String userphonenum, String userrole, Date userregisterdate) {
        this.userid = userid;
        this.usercode = usercode;
        this.username = username;
        this.userfullname = userfullname;
        this.usersex = usersex;
        this.userpassword = userpassword;
        this.userbirth = userbirth;
        this.useremail = useremail;
        this.userphonenum = userphonenum;
        this.userrole = userrole;
        this.userregisterdate = userregisterdate;
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

    public String getUserfullname() {
        return userfullname;
    }

    public void setUserfullname(String userfullname) {
        this.userfullname = userfullname == null ? null : userfullname.trim();
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex == null ? null : usersex.trim();
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null ? null : userpassword.trim();
    }

    public String getUserbirth() {
        return userbirth;
    }

    public void setUserbirth(String userbirth) {
        this.userbirth = userbirth == null ? null : userbirth.trim();
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail == null ? null : useremail.trim();
    }

    public String getUserphonenum() {
        return userphonenum;
    }

    public void setUserphonenum(String userphonenum) {
        this.userphonenum = userphonenum == null ? null : userphonenum.trim();
    }

    public String getUserrole() {
        return userrole;
    }

    public void setUserrole(String userrole) {
        this.userrole = userrole == null ? null : userrole.trim();
    }

    public Date getUserregisterdate() {
        return userregisterdate;
    }

    public void setUserregisterdate(Date userregisterdate) {
        this.userregisterdate = userregisterdate;
    }
}