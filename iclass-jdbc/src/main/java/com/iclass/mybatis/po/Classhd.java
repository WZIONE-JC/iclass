package com.iclass.mybatis.po;

import com.iclass.user.component.utils.IclassUtil;

public class Classhd {
    private Integer classhdid;

    private Integer classcourseid;

    private String classhdcontent;

    private String classhdoptions;

    private String classhdanswer;

    private String classhdcreator;

    private String classhdcreatetime;

    private Integer rightnumber;

    private Integer classhdstatus;

    public Classhd(Integer classhdid, Integer classcourseid, String classhdcontent, String classhdoptions, String classhdanswer, String classhdcreator, String classhdcreatetime, Integer rightnumber, Integer classhdstatus) {
        this.classhdid = classhdid;
        this.classcourseid = classcourseid;
        this.classhdcontent = classhdcontent;
        this.classhdoptions = classhdoptions;
        this.classhdanswer = classhdanswer;
        this.classhdcreator = classhdcreator;
        this.classhdcreatetime = IclassUtil.formatTime(classhdcreatetime);
        this.rightnumber = rightnumber;
        this.classhdstatus = classhdstatus;
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

    public Integer getClasscourseid() {
        return classcourseid;
    }

    public void setClasscourseid(Integer classcourseid) {
        this.classcourseid = classcourseid;
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

    public String getClasshdcreator() {
        return classhdcreator;
    }

    public void setClasshdcreator(String classhdcreator) {
        this.classhdcreator = classhdcreator == null ? null : classhdcreator.trim();
    }

    public String getClasshdcreatetime() {
        return classhdcreatetime;
    }

    public void setClasshdcreatetime(String classhdcreatetime) {
        this.classhdcreatetime = IclassUtil.formatTime(classhdcreatetime);
    }

    public Integer getRightnumber() {
        return rightnumber;
    }

    public void setRightnumber(Integer rightnumber) {
        this.rightnumber = rightnumber;
    }

    public Integer getClasshdstatus() {
        return classhdstatus;
    }

    public void setClasshdstatus(Integer classhdstatus) {
        this.classhdstatus = classhdstatus;
    }

    @Override
    public String toString() {
        return "Classhd{" +
                "classhdid=" + classhdid +
                ", classhdcontent='" + classhdcontent + '\'' +
                ", classhdoptions='" + classhdoptions + '\'' +
                ", classhdanswer='" + classhdanswer + '\'' +
                ", classhdcreator='" + classhdcreator + '\'' +
                ", classhdcreatetime='" + classhdcreatetime + '\'' +
                ", rightnumber=" + rightnumber +
                ", classhdstatus=" + classhdstatus +
                '}';
    }
}