package com.iclass.mybatis.vo;

import com.iclass.mybatis.po.Classhd;

/**
 * iclass
 * <p>
 * Created by JasonTang on 4/14/2017 6:09 PM.
 */
public class ClasshdVo {

    private Classhd classhd;

    private String classCode;

    private String courseCode;

    public ClasshdVo() {}

    public ClasshdVo(Classhd classhd, String classCode, String courseCode) {
        this.classhd = classhd;
        this.classCode = classCode;
        this.courseCode = courseCode;
    }

    public Classhd getClasshd() {
        return classhd;
    }

    public void setClasshd(Classhd classhd) {
        this.classhd = classhd;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @Override
    public String toString() {
        return "ClasshdVo{" +
                "classhd=" + classhd +
                ", classCode='" + classCode + '\'' +
                ", courseCode='" + courseCode + '\'' +
                '}';
    }
}
