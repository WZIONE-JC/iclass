package com.iclass.mybatis.dto;

import com.iclass.mybatis.po.Class;

import java.io.Serializable;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/12/2017 12:56 PM.
 */
public class ClassDTO implements Serializable{

    private Class aClass;

    private String teacherName;

    private Integer studentNum;

    public ClassDTO() {}

    public ClassDTO(Class aClass) {
        this.aClass = aClass;
    }

    public ClassDTO(Class aClass, String teacherName, Integer studentNum) {
        this.aClass = aClass;
        this.teacherName = teacherName;
        this.studentNum = studentNum;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }
    @Override
    public String toString() {
        return "ClassDTO{" +
                "aClass=" + aClass +
                ", teacherName='" + teacherName + '\'' +
                ", studentNum=" + studentNum +
                '}';
    }
}
