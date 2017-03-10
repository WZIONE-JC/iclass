package com.iclass.mybatis.model;

public class Student {
    private Integer studentid;

    private String studentcode;

    public Student(Integer studentid, String studentcode) {
        this.studentid = studentid;
        this.studentcode = studentcode;
    }

    public Student() {
        super();
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public String getStudentcode() {
        return studentcode;
    }

    public void setStudentcode(String studentcode) {
        this.studentcode = studentcode == null ? null : studentcode.trim();
    }
}