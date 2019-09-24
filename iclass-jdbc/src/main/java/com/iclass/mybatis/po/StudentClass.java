package com.iclass.mybatis.po;

public class StudentClass {
    private Integer studentclassid;

    private String classcode;

    private String studentcode;

    public StudentClass(Integer studentclassid, String classcode, String studentcode) {
        this.studentclassid = studentclassid;
        this.classcode = classcode;
        this.studentcode = studentcode;
    }

    public StudentClass() {
        super();
    }

    public Integer getStudentclassid() {
        return studentclassid;
    }

    public void setStudentclassid(Integer studentclassid) {
        this.studentclassid = studentclassid;
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
}