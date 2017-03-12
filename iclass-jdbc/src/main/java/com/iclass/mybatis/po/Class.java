package com.iclass.mybatis.po;

import java.io.Serializable;
import java.sql.Date;

public class Class implements Serializable{
    private Integer classid;

    private String classcode;

    private String classname;

    private String classdescription;

    private String classcreator;

    private Date classcreatetime;

    private Date classdeadline;

    private String classcoursecode;

    private Integer classstatus;

    public Class(Integer classid, String classcode, String classname, String classdescription, String classcreator, Date classcreatetime, Date classdeadline, String classcoursecode, Integer classstatus) {
        this.classid = classid;
        this.classcode = classcode;
        this.classname = classname;
        this.classdescription = classdescription;
        this.classcreator = classcreator;
        this.classcreatetime = classcreatetime;
        this.classdeadline = classdeadline;
        this.classcoursecode = classcoursecode;
        this.classstatus = classstatus;
    }

    public Class() {
        super();
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public String getClasscode() {
        return classcode;
    }

    public void setClasscode(String classcode) {
        this.classcode = classcode == null ? null : classcode.trim();
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }

    public String getClassdescription() {
        return classdescription;
    }

    public void setClassdescription(String classdescription) {
        this.classdescription = classdescription == null ? null : classdescription.trim();
    }

    public String getClasscreator() {
        return classcreator;
    }

    public void setClasscreator(String classcreator) {
        this.classcreator = classcreator == null ? null : classcreator.trim();
    }

    public Date getClasscreatetime() {
        return classcreatetime;
    }

    public void setClasscreatetime(Date classcreatetime) {
        this.classcreatetime = classcreatetime;
    }

    public Date getClassdeadline() {
        return classdeadline;
    }

    public void setClassdeadline(Date classdeadline) {
        this.classdeadline = classdeadline;
    }

    public String getClasscoursecode() {
        return classcoursecode;
    }

    public void setClasscoursecode(String classcoursecode) {
        this.classcoursecode = classcoursecode == null ? null : classcoursecode.trim();
    }

    public Integer getClassstatus() {
        return classstatus;
    }

    public void setClassstatus(Integer classstatus) {
        this.classstatus = classstatus;
    }

    @Override
    public String toString() {
        return "Class{" +
                "classid=" + classid +
                ", classcode='" + classcode + '\'' +
                ", classname='" + classname + '\'' +
                ", classdescription='" + classdescription + '\'' +
                ", classcreator='" + classcreator + '\'' +
                ", classcreatetime=" + classcreatetime +
                ", classdeadline=" + classdeadline +
                ", classcoursecode='" + classcoursecode + '\'' +
                ", classstatus=" + classstatus +
                '}';
    }
}