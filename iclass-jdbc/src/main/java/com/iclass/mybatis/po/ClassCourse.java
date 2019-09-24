package com.iclass.mybatis.po;

import com.iclass.user.component.utils.IclassUtil;

public class ClassCourse {
    private Integer classcourseid;

    private String classcode;

    private String coursecode;

    private String createtime;

    private String deadline;

    private Integer status;

    public ClassCourse(Integer classcourseid, String classcode, String coursecode, String createtime, String deadline, Integer status) {
        this.classcourseid = classcourseid;
        this.classcode = classcode;
        this.coursecode = coursecode;
        this.createtime = IclassUtil.formatTime(createtime);
        this.deadline = IclassUtil.formatTime(deadline);
        this.status = status;
    }

    public ClassCourse() {
        super();
    }

    public Integer getClasscourseid() {
        return classcourseid;
    }

    public void setClasscourseid(Integer classcourseid) {
        this.classcourseid = classcourseid;
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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = IclassUtil.formatTime(createtime);
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = IclassUtil.formatTime(deadline);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ClassCourse{" +
                "iclasscourseid=" + classcourseid +
                ", classcode='" + classcode + '\'' +
                ", coursecode='" + coursecode + '\'' +
                ", createtime='" + createtime + '\'' +
                ", deadline='" + deadline + '\'' +
                ", status=" + status +
                '}';
    }
}