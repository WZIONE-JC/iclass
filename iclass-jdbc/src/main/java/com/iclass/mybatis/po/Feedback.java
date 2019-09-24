package com.iclass.mybatis.po;

import com.iclass.user.component.utils.IclassUtil;

public class Feedback {
    private Integer feedbackid;

    private Integer parentid;

    private String title;

    private String content;

    private Integer classcourseid;

    private Integer fileid;

    private String feedbackcode;

    private String teachercode;

    private String createtime;

    private Byte status;

    public Feedback(Integer feedbackid, Integer parentid, String title, String content, Integer classcourseid, Integer fileid, String feedbackcode, String teachercode, String createtime, Byte status) {
        this.feedbackid = feedbackid;
        this.parentid = parentid;
        this.title = title;
        this.content = content;
        this.classcourseid = classcourseid;
        this.fileid = fileid;
        this.feedbackcode = feedbackcode;
        this.teachercode = teachercode;
        this.createtime = createtime;
        this.status = status;
    }

    public Feedback() {
        super();
    }

    public Integer getFeedbackid() {
        return feedbackid;
    }

    public void setFeedbackid(Integer feedbackid) {
        this.feedbackid = feedbackid;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getClasscourseid() {
        return classcourseid;
    }

    public void setClasscourseid(Integer classcourseid) {
        this.classcourseid = classcourseid;
    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public String getFeedbackcode() {
        return feedbackcode;
    }

    public void setFeedbackcode(String feedbackcode) {
        this.feedbackcode = feedbackcode == null ? null : feedbackcode.trim();
    }

    public String getTeachercode() {
        return teachercode;
    }

    public void setTeachercode(String teachercode) {
        this.teachercode = teachercode;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = IclassUtil.formatTime(createtime);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackid=" + feedbackid +
                ", parentid=" + parentid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", classcourseid=" + classcourseid +
                ", fileid=" + fileid +
                ", feedbackcode='" + feedbackcode + '\'' +
                ", teachercode='" + teachercode + '\'' +
                ", createtime='" + createtime + '\'' +
                ", status=" + status +
                '}';
    }
}