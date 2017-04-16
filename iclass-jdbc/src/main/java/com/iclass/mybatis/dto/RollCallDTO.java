package com.iclass.mybatis.dto;

import com.iclass.mybatis.po.Rollcall;
import com.iclass.mybatis.po.User;

/**
 * Created by radishmaster on 15/04/17.
 */
public class RollCallDTO {

    private Rollcall rollcall;

    private String teacherName;

    private String classRoomName;

    private SessionUser student;

    public RollCallDTO(Rollcall rollcall, String teacherName, String classRoomName, SessionUser student) {
        this.rollcall = rollcall;
        this.teacherName = teacherName;
        this.classRoomName = classRoomName;
        this.student = student;
    }

    public RollCallDTO() {}

    public Rollcall getRollcall() {
        return rollcall;
    }

    public void setRollcall(Rollcall rollcall) {
        this.rollcall = rollcall;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
    }

    public SessionUser getStudent() {
        return student;
    }

    public void setStudent(SessionUser student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "RollCallDTO{" +
                "rollcall=" + rollcall +
                ", teacherName='" + teacherName + '\'' +
                ", classRoomName='" + classRoomName + '\'' +
                ", student=" + student +
                '}';
    }
}
