package com.iclass.mybatis.vo;

import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 5/16/2017 9:54 PM.
 * app上显示的点名数据
 */
public class RollCallVoApp {

    /**
     * 课堂名
     */
    private String classRoomName;

    /**
     * 学生名
     */
    private String studentName;

    /**
     * 签到数据
     */
    private List<RollCallMetaData> rollCallData;

    public RollCallVoApp(String classRoomName, String studentName, List<RollCallMetaData> rollCallData) {
        this.classRoomName = classRoomName;
        this.studentName = studentName;
        this.rollCallData = rollCallData;
    }

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<RollCallMetaData> getRollCallData() {
        return rollCallData;
    }

    public void setRollCallData(List<RollCallMetaData> rollCallData) {
        this.rollCallData = rollCallData;
    }
}
