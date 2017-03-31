package com.iclass.mybatis.po;

import java.io.Serializable;

public class Classhd implements Serializable{
    private Integer classhdid;

    private String classhdcontent;

    private String classhdoptions;

    private String classhdanswer;

    private String classcode;

    public Classhd(Integer classhdid, String classhdcontent, String classhdoptions, String classhdanswer, String classcode) {
        this.classhdid = classhdid;
        this.classhdcontent = classhdcontent;
        this.classhdoptions = classhdoptions;
        this.classhdanswer = classhdanswer;
        this.classcode = classcode;
    }

    public Classhd() {
        super();
    }

    public Integer getClasshdid() {
        return classhdid;
    }

    public void setClasshdid(Integer classhdid) {
        this.classhdid = classhdid;
    }

    public String getClasshdcontent() {
        return classhdcontent;
    }

    public void setClasshdcontent(String classhdcontent) {
        this.classhdcontent = classhdcontent == null ? null : classhdcontent.trim();
    }

    public String getClasshdoptions() {
        return classhdoptions;
    }

    public void setClasshdoptions(String classhdoptions) {
        this.classhdoptions = classhdoptions == null ? null : classhdoptions.trim();
    }

    public String getClasshdanswer() {
        return classhdanswer;
    }

    public void setClasshdanswer(String classhdanswer) {
        this.classhdanswer = classhdanswer == null ? null : classhdanswer.trim();
    }

    public String getClasscode() {
        return classcode;
    }

    public void setClasscode(String classcode) {
        this.classcode = classcode == null ? null : classcode.trim();
    }

    @Override
    public String toString() {
        return "Classhd{" +
                "classhdid=" + classhdid +
                ", classhdcontent='" + classhdcontent + '\'' +
                ", classhdoptions='" + classhdoptions + '\'' +
                ", classhdanswer='" + classhdanswer + '\'' +
                ", classcode='" + classcode + '\'' +
                '}';
    }
}