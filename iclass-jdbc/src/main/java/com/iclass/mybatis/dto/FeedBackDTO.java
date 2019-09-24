package com.iclass.mybatis.dto;

import com.iclass.mybatis.po.Feedback;
import com.iclass.mybatis.po.Iclassfile;

/**
 * iclass
 * <p>
 * Created by JasonTang on 4/26/2017 10:11 PM.
 */
public class FeedBackDTO {

    /**
     * 课堂名字
     */
    private String classRoomName;

    /**
     * 反馈的信息
     */
    private Feedback feedback;

    /**
     * 学生/教师的名字
     */
    private String feedbacker;

    /**
     * 学生所在班级
     */
    private String className;

    /**
     * 返回问题的子问题数量
     */
    private Integer feedbackNum;

    /**
     * 1是学生，0是教师
     */
    private Integer isStudent;

    /**
     * 课件
     */
    private Iclassfile iclassfile;

    public FeedBackDTO() {}
    

    public String getClassRoomName() {
        return classRoomName;
    }

    public FeedBackDTO(String classRoomName, Feedback feedback, String feedbacker, String className, Integer feedbackNum, Integer isStudent, Iclassfile iclassfile) {
        this.classRoomName = classRoomName;
        this.feedback = feedback;
        this.feedbacker = feedbacker;
        this.className = className;
        this.feedbackNum = feedbackNum;
        this.isStudent = isStudent;
        this.iclassfile = iclassfile;
    }

    @Override
    public String toString() {
        return "FeedBackDTO{" +
                "classRoomName='" + classRoomName + '\'' +
                ", feedback=" + feedback +
                ", feedbacker='" + feedbacker + '\'' +
                ", className='" + className + '\'' +
                ", feedbackNum=" + feedbackNum +
                ", isStudent=" + isStudent +
                ", iclassfile=" + iclassfile +
                '}';
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public String getFeedbacker() {
        return feedbacker;
    }

    public void setFeedbacker(String feedbacker) {
        this.feedbacker = feedbacker;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getFeedbackNum() {
        return feedbackNum;
    }

    public void setFeedbackNum(Integer feedbackNum) {
        this.feedbackNum = feedbackNum;
    }

    public Integer getIsStudent() {
        return isStudent;
    }

    public void setIsStudent(Integer isStudent) {
        this.isStudent = isStudent;
    }

    public Iclassfile getIclassfile() {
        return iclassfile;
    }

    public void setIclassfile(Iclassfile iclassfile) {
        this.iclassfile = iclassfile;
    }

}
