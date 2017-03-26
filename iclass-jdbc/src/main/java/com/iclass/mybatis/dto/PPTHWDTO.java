package com.iclass.mybatis.dto;

import com.iclass.mybatis.po.Class;
import com.iclass.mybatis.po.Course;

import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/12/2017 1:58 PM.
 * 已上传课件和作业浏览页面
 */
public class PPTHWDTO {

    /**
     * 课堂信息
     */
    private Class aClass;

    /**
     * 课程信息
     */
    private Course course;

    /**
     * 教师姓名
     * teacherFullName
     */
    private String teacherName;

    private List<SessionUser> students;

    public PPTHWDTO() {}

    public PPTHWDTO(Class aClass, Course course, String teacherName, List<SessionUser> students) {
        this.aClass = aClass;
        this.course = course;
        this.teacherName = teacherName;
        this.students = students;
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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public List<SessionUser> getStudents() {
        return students;
    }

    public void setStudents(List<SessionUser> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "PPTHWDTO{" +
                "aClass=" + aClass +
                ", course=" + course +
                ", teacherName='" + teacherName + '\'' +
                ", students=" + students +
                '}';
    }
}
