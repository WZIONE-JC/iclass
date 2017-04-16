package com.iclass.mybatis.po;

import com.iclass.user.component.utils.IclassUtil;

public class Rollcall {
    private Integer rollcallid;

    private Integer classcourseid;

    private String studentcode;

    private String teachercode;

    private String rollcalltime;

    private Integer rollcallstatus;

    public Rollcall(Integer rollcallid, Integer classcourseid, String studentcode, String teachercode, String rollcalltime, Integer rollcallstatus) {
        this.rollcallid = rollcallid;
        this.classcourseid = classcourseid;
        this.studentcode = studentcode;
        this.teachercode = teachercode;
        this.rollcalltime = IclassUtil.formatTime(rollcalltime);
        this.rollcallstatus = rollcallstatus;
    }

    public Rollcall() {
        super();
    }

    public Integer getRollcallid() {
        return rollcallid;
    }

    public void setRollcallid(Integer rollcallid) {
        this.rollcallid = rollcallid;
    }

    public Integer getClasscourseid() {
        return classcourseid;
    }

    public void setClasscourseid(Integer classcourseid) {
        this.classcourseid = classcourseid;
    }

    public String getStudentcode() {
        return studentcode;
    }

    public void setStudentcode(String studentcode) {
        this.studentcode = studentcode == null ? null : studentcode.trim();
    }

    public String getTeachercode() {
        return teachercode;
    }

    public void setTeachercode(String teachercode) {
        this.teachercode = teachercode == null ? null : teachercode.trim();
    }

    public String getRollcalltime() {
        return rollcalltime;
    }

    public void setRollcalltime(String rollcalltime) {
        this.rollcalltime = IclassUtil.formatTime(rollcalltime);
    }

    public Integer getRollcallstatus() {
        return rollcallstatus;
    }

    public void setRollcallstatus(Integer rollcallstatus) {
        this.rollcallstatus = rollcallstatus;
    }

    @Override
    public String toString() {
        return "Rollcall{" +
                "rollcallid=" + rollcallid +
                ", classcourseid=" + classcourseid +
                ", studentcode='" + studentcode + '\'' +
                ", teachercode='" + teachercode + '\'' +
                ", rollcalltime='" + rollcalltime + '\'' +
                ", rollcallstatus='" + rollcallstatus + '\'' +
                '}';
    }
}