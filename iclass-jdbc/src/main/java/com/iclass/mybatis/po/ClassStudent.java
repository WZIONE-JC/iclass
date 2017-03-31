package com.iclass.mybatis.po;

import java.io.Serializable;

public class ClassStudent implements Serializable{
    private Integer classstudentid;

    private String classcode;

    private String studentcode;

    public ClassStudent(Integer classstudentid, String classcode, String studentcode) {
        this.classstudentid = classstudentid;
        this.classcode = classcode;
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

    public String getClasscode() {
        return classcode;
    }

    public void setClasscode(String classcode) {
        this.classcode = classcode == null ? null : classcode.trim();
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
                ", classcode='" + classcode + '\'' +
                ", studentcode='" + studentcode + '\'' +
                '}';
    }
}