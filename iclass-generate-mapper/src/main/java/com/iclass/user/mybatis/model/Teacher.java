package com.iclass.user.mybatis.model;

public class Teacher {
    private Integer teacherid;

    private String teachercode;

    private String teacherfullname;

    private String teacheremail;

    private String teacherphonenumber;

    public Teacher(Integer teacherid, String teachercode, String teacherfullname, String teacheremail, String teacherphonenumber) {
        this.teacherid = teacherid;
        this.teachercode = teachercode;
        this.teacherfullname = teacherfullname;
        this.teacheremail = teacheremail;
        this.teacherphonenumber = teacherphonenumber;
    }

    public Teacher() {
        super();
    }

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    public String getTeachercode() {
        return teachercode;
    }

    public void setTeachercode(String teachercode) {
        this.teachercode = teachercode == null ? null : teachercode.trim();
    }

    public String getTeacherfullname() {
        return teacherfullname;
    }

    public void setTeacherfullname(String teacherfullname) {
        this.teacherfullname = teacherfullname == null ? null : teacherfullname.trim();
    }

    public String getTeacheremail() {
        return teacheremail;
    }

    public void setTeacheremail(String teacheremail) {
        this.teacheremail = teacheremail == null ? null : teacheremail.trim();
    }

    public String getTeacherphonenumber() {
        return teacherphonenumber;
    }

    public void setTeacherphonenumber(String teacherphonenumber) {
        this.teacherphonenumber = teacherphonenumber == null ? null : teacherphonenumber.trim();
    }
}