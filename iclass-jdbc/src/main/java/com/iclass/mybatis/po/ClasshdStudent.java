package com.iclass.mybatis.po;

public class ClasshdStudent {
    private Integer classhdstudentid;

    private Integer classhdid;

    private String studentcode;

    private Integer result;

    public ClasshdStudent(Integer classhdstudentid, Integer classhdid, String studentcode, Integer result) {
        this.classhdstudentid = classhdstudentid;
        this.classhdid = classhdid;
        this.studentcode = studentcode;
        this.result = result;
    }

    public ClasshdStudent() {
        super();
    }

    public Integer getClasshdstudentid() {
        return classhdstudentid;
    }

    public void setClasshdstudentid(Integer classhdstudentid) {
        this.classhdstudentid = classhdstudentid;
    }

    public Integer getClasshdid() {
        return classhdid;
    }

    public void setClasshdid(Integer classhdid) {
        this.classhdid = classhdid;
    }

    public String getStudentcode() {
        return studentcode;
    }

    public void setStudentcode(String studentcode) {
        this.studentcode = studentcode == null ? null : studentcode.trim();
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ClasshdStudent{" +
                "classhdstudentid=" + classhdstudentid +
                ", classhdid=" + classhdid +
                ", studentcode='" + studentcode + '\'' +
                ", result=" + result +
                '}';
    }
}