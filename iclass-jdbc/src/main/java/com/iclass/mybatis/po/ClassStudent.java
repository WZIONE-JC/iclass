package com.iclass.mybatis.po;

import java.io.Serializable;

public class ClassStudent implements Serializable{
    private Integer classstudentid;

    private Integer classID;

    private String studentcode;

    public ClassStudent(Integer classstudentid, Integer classID, String studentcode) {
        this.classstudentid = classstudentid;
        this.classID = classID;
        this.studentcode = studentcode;
    }

    public ClassStudent() {
        super();
    }

    public Integer getClassstudentid() {
        return classstudentid;
    }

    public void setClassstudentid(Integer classstudentid) {
        this.classstudentid = classstudentid;
    }

    public Integer getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }

    public String getStudentcode() {
        return studentcode;
    }

    public void setStudentcode(String studentcode) {
        this.studentcode = studentcode == null ? null : studentcode.trim();
    }

    @Override
    public String toString() {
        return "ClassStudent{" +
                "classstudentid=" + classstudentid +
                ", classID=" + classID +
                ", studentcode='" + studentcode + '\'' +
                '}';
    }
}