package com.iclass.mybatis.po;

public class RollcallTimes {
    private Integer rollcalltimesid;

    private Integer classcourseid;

    private String teachercode;

    private String rollcalldate;

    public RollcallTimes(Integer rollcalltimesid, Integer classcourseid, String teachercode, String rollcalldate) {
        this.rollcalltimesid = rollcalltimesid;
        this.classcourseid = classcourseid;
        this.teachercode = teachercode;
        this.rollcalldate = rollcalldate;
    }

    public RollcallTimes() {
        super();
    }

    public Integer getRollcalltimesid() {
        return rollcalltimesid;
    }

    public void setRollcalltimesid(Integer rollcalltimesid) {
        this.rollcalltimesid = rollcalltimesid;
    }

    public Integer getClasscourseid() {
        return classcourseid;
    }

    public void setClasscourseid(Integer classcourseid) {
        this.classcourseid = classcourseid;
    }

    public String getTeachercode() {
        return teachercode;
    }

    public void setTeachercode(String teachercode) {
        this.teachercode = teachercode == null ? null : teachercode.trim();
    }

    public String getRollcalldate() {
        return rollcalldate;
    }

    public void setRollcalldate(String rollcalldate) {
        this.rollcalldate = rollcalldate;
    }
}