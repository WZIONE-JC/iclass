package com.iclass.mybatis.po;

import java.io.Serializable;

public class Course implements Serializable{
    private Integer courseid;

    private String coursecode;

    private String coursename;

    private String coursedescription;

    private String coursegrade;

    private String coursecreatetime;

    private Integer coursestatus;

    public Course(Integer courseid, String coursecode, String coursename, String coursedescription, String coursegrade, String coursecreatetime, Integer coursestatus) {
        this.courseid = courseid;
        this.coursecode = coursecode;
        this.coursename = coursename;
        this.coursedescription = coursedescription;
        this.coursegrade = coursegrade;
        this.coursecreatetime = coursecreatetime;
        this.coursestatus = coursestatus;
    }

    public Course() {
        super();
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public String getCoursecode() {
        return coursecode;
    }

    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode == null ? null : coursecode.trim();
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename == null ? null : coursename.trim();
    }

    public String getCoursedescription() {
        return coursedescription;
    }

    public void setCoursedescription(String coursedescription) {
        this.coursedescription = coursedescription == null ? null : coursedescription.trim();
    }

    public String getCoursegrade() {
        return coursegrade;
    }

    public void setCoursegrade(String coursegrade) {
        this.coursegrade = coursegrade == null ? null : coursegrade.trim();
    }

    public String getCoursecreatetime() {
        return coursecreatetime;
    }

    public void setCoursecreatetime(String coursecreatetime) {
        this.coursecreatetime = coursecreatetime;
    }

    public Integer getCoursestatus() {
        return coursestatus;
    }

    public void setCoursestatus(Integer coursestatus) {
        this.coursestatus = coursestatus;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseid=" + courseid +
                ", coursecode='" + coursecode + '\'' +
                ", coursename='" + coursename + '\'' +
                ", coursedescription='" + coursedescription + '\'' +
                ", coursegrade='" + coursegrade + '\'' +
                ", coursecreatetime='" + coursecreatetime + '\'' +
                ", coursestatus=" + coursestatus +
                '}';
    }
}