package com.iclass.user.mybatis.model;

public class Teacher {
    private Integer teacherid;

    private String teachercode;

    public Teacher(Integer teacherid, String teachercode) {
        this.teacherid = teacherid;
        this.teachercode = teachercode;
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
}