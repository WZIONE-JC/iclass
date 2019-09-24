package com.iclass.mybatis.vo;

/**
 * iclass
 * <p>
 * Created by JasonTang on 5/15/2017 3:05 PM.
 */
public class ClassCourseVo {

    private String classRoomName;

    private Integer classCourseId;

    public ClassCourseVo() {}

    public ClassCourseVo(String classRoomName, Integer classCourseId) {
        this.classRoomName = classRoomName;
        this.classCourseId = classCourseId;
    }

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
    }

    public Integer getClassCourseId() {
        return classCourseId;
    }

    public void setClassCourseId(Integer classCourseId) {
        this.classCourseId = classCourseId;
    }

    @Override
    public String toString() {
        return "ClassCourseVo{" +
                "classRoomName='" + classRoomName + '\'' +
                ", classCourseId=" + classCourseId +
                '}';
    }
}
