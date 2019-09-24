package com.iclass.mybatis.dto;

import com.iclass.mybatis.po.ClassCourse;
import com.iclass.mybatis.po.Classhd;

/**
 * iclass
 * <p>
 * Created by JasonTang on 4/14/2017 3:20 PM.
 */
public class ClasshdDTO {

    private Classhd classhd;

    private ClassCourse classRoom;

    private String classRoomName;

    private String teacherName;

    private Integer studentNum;

    public ClasshdDTO() {}

    public ClasshdDTO(Classhd classhd, ClassCourse classRoom, String classRoomName, String teacherName, Integer studentNum) {
        this.classhd = classhd;
        this.classRoom = classRoom;
        this.classRoomName = classRoomName;
        this.teacherName = teacherName;
        this.studentNum = studentNum;
    }

    public Classhd getClasshd() {
        return classhd;
    }

    public void setClasshd(Classhd classhd) {
        this.classhd = classhd;
    }

    public ClassCourse getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassCourse classRoom) {
        this.classRoom = classRoom;
    }

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
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
        return "ClasshdDTO{" +
                "classhd=" + classhd +
                ", classRoom=" + classRoom +
                ", classRoomName='" + classRoomName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", studentNum=" + studentNum +
                '}';
    }
}
