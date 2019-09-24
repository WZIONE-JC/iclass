package com.iclass.mybatis.dto;

import com.iclass.mybatis.po.Course;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/12/2017 6:14 PM.
 */
public class CourseDTO {

    private Course course;

    private String teacherName;

    public CourseDTO() {}

    public CourseDTO(Course course, String teacherName) {
        this.course = course;
        this.teacherName = teacherName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "course=" + course +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
