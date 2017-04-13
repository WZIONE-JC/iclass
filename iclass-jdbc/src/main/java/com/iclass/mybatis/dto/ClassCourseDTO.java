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

    private String classRoomName;

    private Class aClass;

    private Course course;

    private String teacherName;

    private List<SessionUser> students;

    private String creatTime;

    private String deadline;

    private Integer status;

    public ClassCourseDTO(){}

    public ClassCourseDTO(Integer classcourseId, Class aClass, Course course, String teacherName, List<SessionUser> students, String creatTime, String deadline, Integer status) {
        this.classcourseId = classcourseId;
        this.aClass = aClass;
        this.course = course;
        this.teacherName = teacherName;
        this.students = students;
        this.creatTime = creatTime;
        this.deadline = deadline;
        this.status = status;
    }

    public ClassCourseDTO(Integer classcourseId, String classRoomName, Class aClass, Course course, String teacherName, List<SessionUser> students, String creatTime, String deadline, Integer status) {
        this.classcourseId = classcourseId;
        this.classRoomName = classRoomName;
        this.aClass = aClass;
        this.course = course;
        this.teacherName = teacherName;
        this.students = students;
        this.creatTime = creatTime;
        this.deadline = deadline;
        this.status = status;
    }

    public Integer getClasscourseId() {
        return classcourseId;
    }

    public void setClasscourseId(Integer classcourseId) {
        this.classcourseId = classcourseId;
    }

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ClassCourseDTO{" +
                "classcourseId=" + classcourseId +
                ", aClass=" + aClass +
                ", course=" + course +
                ", teacherName='" + teacherName + '\'' +
                ", students=" + students +
                ", creatTime='" + creatTime + '\'' +
                ", deadline='" + deadline + '\'' +
                ", status=" + status +
                '}';
    }
}
