package com.iclass.mybatis.po;

import java.io.Serializable;

public class IclassfileClass implements Serializable{
    private Integer fileclassid;

    private String filecode;

    private Integer classcourseid;

    public IclassfileClass() {
        super();
    }

    public IclassfileClass(Integer fileclassid, String filecode, Integer classcourseid) {
        this.fileclassid = fileclassid;
        this.filecode = filecode;
        this.classcourseid = classcourseid;
    }

    public Integer getFileclassid() {
        return fileclassid;
    }

    public void setFileclassid(Integer fileclassid) {
        this.fileclassid = fileclassid;
    }

    public String getFilecode() {
        return filecode;
    }

    public void setFilecode(String filecode) {
        this.filecode = filecode == null ? null : filecode.trim();
    }


    public Integer getClasscourseid() {
        return classcourseid;
    }

    public void setClasscourseid(Integer classcourseid) {
        this.classcourseid = classcourseid;
    }

    @Override
    public String toString() {
        return "IclassfileClass{" +
                "fileclassid=" + fileclassid +
                ", filecode='" + filecode + '\'' +
                ", classcourseid=" + classcourseid +
                '}';
    }
}