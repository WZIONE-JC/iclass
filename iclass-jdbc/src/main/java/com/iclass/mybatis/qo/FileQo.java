package com.iclass.mybatis.qo;

import java.io.Serializable;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/11/2017 2:09 PM.
 * <p>
 * 上传文件时的实体
 */
public class FileQo implements Serializable{

    /**
     * 文件类型：0：作业，1：ppt
     */
    private Integer fileType;

    /**
     * 文件上传者
     */
    private String fileCreator;

    /**
     * 课程编号
     */
    private String courseCode;

    /**
     * 班级编号
     */
    private String classCode;

    /**
     * 文件描述
     */
    private String fileDesc;

    private Integer classcourseId;

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getFileCreator() {
        return fileCreator;
    }

    public void setFileCreator(String fileCreator) {
        this.fileCreator = fileCreator;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getFileDesc() {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    public Integer getClasscourseId() {
        return classcourseId;
    }

    public void setClasscourseId(Integer classcourseId) {
        this.classcourseId = classcourseId;
    }

    @Override
    public String toString() {
        return "FileQo{" +
                "fileType=" + fileType +
                ", fileCreator='" + fileCreator + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", classCode='" + classCode + '\'' +
                ", fileDesc='" + fileDesc + '\'' +
                ", classcourseId=" + classcourseId +
                '}';
    }
}
