package com.iclass.mybatis.po;

import java.io.Serializable;

public class TeacherCourse implements Serializable{
    private Integer teachercourseid;

    private String teachercode;

    private String coursecode;

    public TeacherCourse(Integer teachercourseid, String teachercode, String coursecode) {
        this.teachercourseid = teachercourseid;
        this.teachercode = teachercode;
        this.coursecode = coursecode;
    }

    public TeacherCourse() {
        super();
    }

    public Integer getTeachercourseid() {
        return teachercourseid;
    }

    public void setTeachercourseid(Integer teachercourseid) {
        this.teachercourseid = teachercourseid;
    }

    public String getTeachercode() {
        return teachercode;
    }

    public void setTeachercode(String teachercode) {
        this.teachercode = teachercode == null ? null : teachercode.trim();
    }

    public String getCoursecode() {
        return coursecode;
    }

    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode == null ? null : coursecode.trim();
    }

    @Override
    public String toString() {
        return "TeacherCourse{" +
                "teachercourseid=" + teachercourseid +
                ", teachercode='" + teachercode + '\'' +
                ", coursecode='" + coursecode + '\'' +
                '}';
    }
}