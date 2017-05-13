package com.iclass.mybatis.vo;

import com.iclass.mybatis.dto.SessionUser;

import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 4/16/2017 4:12 PM.
 */
public class RollcallVo {

    private String classRoomName;

    private String teacherName;

    private Integer arrivedNum;

    private Integer absentNum;

    private List<SessionUser> absentStudents;

    public RollcallVo() {}

    public RollcallVo(String classRoomName, String teacherName, Integer arrivedNum, Integer absentNum, List<SessionUser> absentStudents) {
        this.classRoomName = classRoomName;
        this.teacherName = teacherName;
        this.arrivedNum = arrivedNum;
        this.absentNum = absentNum;
        this.absentStudents = absentStudents;
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

    public Integer getArrivedNum() {
        return arrivedNum;
    }

    public void setArrivedNum(Integer arrivedNum) {
        this.arrivedNum = arrivedNum;
    }

    public Integer getAbsentNum() {
        return absentNum;
    }

    public void setAbsentNum(Integer absentNum) {
        this.absentNum = absentNum;
    }

    public List<SessionUser> getAbsentStudents() {
        return absentStudents;
    }

    public void setAbsentStudents(List<SessionUser> absentStudents) {
        this.absentStudents = absentStudents;
    }

    @Override
    public String toString() {
        return "RollcallVo{" +
                "classRoomName='" + classRoomName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", arrivedNum=" + arrivedNum +
                ", absentNum=" + absentNum +
                ", absentStudents=" + absentStudents +
                '}';
    }
}
