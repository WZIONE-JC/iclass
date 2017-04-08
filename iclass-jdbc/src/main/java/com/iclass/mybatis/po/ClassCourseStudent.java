package com.iclass.mybatis.po;

public class ClassCourseStudent {
    private Integer classstudentid;

    private Integer classcourseid;

    private String studentcode;

    public ClassCourseStudent(Integer classstudentid, Integer classcourseid, String studentcode) {
        this.classstudentid = classstudentid;
        this.classcourseid = classcourseid;
        this.studentcode = studentcode;
    }

    public ClassCourseStudent() {
        super();
    }

    public Integer getClassstudentid() {
        return classstudentid;
    }

    public void setClassstudentid(Integer classstudentid) {
        this.classstudentid = classstudentid;
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

    @Override
    public String toString() {
        return "ClassCourseStudent{" +
                "classstudentid=" + classstudentid +
                ", classcourseid=" + classcourseid +
                ", studentcode='" + studentcode + '\'' +
                '}';
    }
}