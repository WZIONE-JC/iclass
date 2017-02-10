package com.iclass.user.mybatis.model;

public class Student {
    private Integer studentid;

    private String studentcode;

    private String studentfullname;

    private String studentemail;

    private String studentphonenumber;

    public Student(Integer studentid, String studentcode, String studentfullname, String studentemail, String studentphonenumber) {
        this.studentid = studentid;
        this.studentcode = studentcode;
        this.studentfullname = studentfullname;
        this.studentemail = studentemail;
        this.studentphonenumber = studentphonenumber;
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

    public String getStudentfullname() {
        return studentfullname;
    }

    public void setStudentfullname(String studentfullname) {
        this.studentfullname = studentfullname == null ? null : studentfullname.trim();
    }

    public String getStudentemail() {
        return studentemail;
    }

    public void setStudentemail(String studentemail) {
        this.studentemail = studentemail == null ? null : studentemail.trim();
    }

    public String getStudentphonenumber() {
        return studentphonenumber;
    }

    public void setStudentphonenumber(String studentphonenumber) {
        this.studentphonenumber = studentphonenumber == null ? null : studentphonenumber.trim();
    }
}