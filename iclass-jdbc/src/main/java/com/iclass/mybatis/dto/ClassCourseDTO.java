package com.iclass.mybatis.dto;

import com.iclass.mybatis.po.Class;
import com.iclass.mybatis.po.Course;

import java.io.Serializable;
import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/11/2017 11:10 PM.
 */
public class ClassCourseDTO implements Serializable {

    private Integer classcourseId;

    private Class aClass;

    private Course course;

    private String teacherName;

    private List<SessionUser> sessionUsers;

    private String creatTime;

    private String deadline;

    public ClassCourseDTO(){}

    public ClassCourseDTO(Integer classcourseId, Class aClass, Course course, String teacherName, List<SessionUser> sessionUsers, String creatTime, String deadline) {
        this.classcourseId = classcourseId;
        this.aClass = aClass;
        this.course = course;
        this.teacherName = teacherName;
        this.sessionUsers = sessionUsers;
        this.creatTime = creatTime;
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "ClassCourseDTO{" +
                "classcourseId=" + classcourseId +
                ", aClass=" + aClass +
                ", course=" + course +
                ", teacherName='" + teacherName + '\'' +
                ", sessionUsers=" + sessionUsers +
                ", creatTime='" + creatTime + '\'' +
                ", deadline='" + deadline + '\'' +
                '}';
    }

    public Integer getClasscourseId() {
        return classcourseId;
    }

    public void setClasscourseId(Integer classcourseId) {
        this.classcourseId = classcourseId;
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

    public List<SessionUser> getSessionUsers() {
        return sessionUsers;
    }

    public void setSessionUsers(List<SessionUser> sessionUsers) {
        this.sessionUsers = sessionUsers;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

}
