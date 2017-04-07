package com.iclass.mybatis.dto;

import com.iclass.mybatis.po.Class;
import com.iclass.mybatis.po.Course;

import java.io.Serializable;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/11/2017 11:10 PM.
 */
public class ClassCourseDTO implements Serializable {

    private Class aClass;

    private Course course;

    public ClassCourseDTO(){}

    public ClassCourseDTO(Class aClass, Course course) {
        this.aClass = aClass;
        this.course = course;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "ClassCourseDTO{" +
                "aClass=" + aClass +
                ", course=" + course +
                '}';
    }

}
