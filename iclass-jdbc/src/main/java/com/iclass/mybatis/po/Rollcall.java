package com.iclass.mybatis.po;

import java.io.Serializable;
import java.util.Date;

public class Rollcall implements Serializable{
    private Integer rollcallid;

    private String rollcallcode;

    private Date rollcalltime;

    private String rollcallstatus;

    private String classcode;

    private String teachercode;

    private String coursecode;

    public Rollcall(Integer rollcallid, String rollcallcode, Date rollcalltime, String rollcallstatus, String classcode, String teachercode, String coursecode) {
        this.rollcallid = rollcallid;
        this.rollcallcode = rollcallcode;
        this.rollcalltime = rollcalltime;
        this.rollcallstatus = rollcallstatus;
        this.classcode = classcode;
        this.teachercode = teachercode;
        this.coursecode = coursecode;
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

    public String getRollcallcode() {
        return rollcallcode;
    }

    public void setRollcallcode(String rollcallcode) {
        this.rollcallcode = rollcallcode == null ? null : rollcallcode.trim();
    }

    public Date getRollcalltime() {
        return rollcalltime;
    }

    public void setRollcalltime(Date rollcalltime) {
        this.rollcalltime = rollcalltime;
    }

    public String getRollcallstatus() {
        return rollcallstatus;
    }

    public void setRollcallstatus(String rollcallstatus) {
        this.rollcallstatus = rollcallstatus == null ? null : rollcallstatus.trim();
    }

    public String getClasscode() {
        return classcode;
    }

    public void setClasscode(String classcode) {
        this.classcode = classcode == null ? null : classcode.trim();
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
        return "Rollcall{" +
                "rollcallid=" + rollcallid +
                ", rollcallcode='" + rollcallcode + '\'' +
                ", rollcalltime=" + rollcalltime +
                ", rollcallstatus='" + rollcallstatus + '\'' +
                ", classcode='" + classcode + '\'' +
                ", teachercode='" + teachercode + '\'' +
                ", coursecode='" + coursecode + '\'' +
                '}';
    }
}