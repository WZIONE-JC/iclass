package com.iclass.mybatis.dto;

import com.iclass.mybatis.po.Course;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/12/2017 6:14 PM.
 */
public class CourseDTO {

    private Course course;

    public CourseDTO(Course course) {
        this.course = course;
    }

    public CourseDTO() {}

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "course=" + course +
                '}';
    }
}
