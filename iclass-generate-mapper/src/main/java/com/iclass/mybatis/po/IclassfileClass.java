package com.iclass.mybatis.po;

public class IclassfileClass {
    private Integer fileclassid;

    private String filecode;

    private String classcode;

    private String coursecode;

    public IclassfileClass(Integer fileclassid, String filecode, String classcode, String coursecode) {
        this.fileclassid = fileclassid;
        this.filecode = filecode;
        this.classcode = classcode;
        this.coursecode = coursecode;
    }

    public IclassfileClass() {
        super();
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

    public String getClasscode() {
        return classcode;
    }

    public void setClasscode(String classcode) {
        this.classcode = classcode == null ? null : classcode.trim();
    }

    public String getCoursecode() {
        return coursecode;
    }

    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode == null ? null : coursecode.trim();
    }
}