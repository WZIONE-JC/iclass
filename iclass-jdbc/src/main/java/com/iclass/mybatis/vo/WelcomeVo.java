package com.iclass.mybatis.vo;

/**
 * iclass
 * <p>
 * Created by JasonTang on 5/14/2017 10:31 AM.
 */
public class WelcomeVo {

    /**
     * 课堂数量
     */
    private Integer classCourseNum;

    /**
     * 班级数量
     */
    private Integer classNum;

    /**
     * 课程数量
     */
    private Integer courseNum;

    /**
     * ppt数量
     */
    private Integer pptNum;

    /**
     * 作业数量
     */
    private Integer hwNum;

    public WelcomeVo() {}

    public WelcomeVo(Integer classCourseNum, Integer classNum, Integer courseNum, Integer pptNum, Integer hwNum) {
        this.classCourseNum = classCourseNum;
        this.classNum = classNum;
        this.courseNum = courseNum;
        this.pptNum = pptNum;
        this.hwNum = hwNum;
    }

    public Integer getClassCourseNum() {
        return classCourseNum;
    }

    public void setClassCourseNum(Integer classCourseNum) {
        this.classCourseNum = classCourseNum;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public Integer getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
    }

    public Integer getPptNum() {
        return pptNum;
    }

    public void setPptNum(Integer pptNum) {
        this.pptNum = pptNum;
    }

    public Integer getHwNum() {
        return hwNum;
    }

    public void setHwNum(Integer hwNum) {
        this.hwNum = hwNum;
    }

    @Override
    public String toString() {
        return "WelcomeVo{" +
                "classCourseNum=" + classCourseNum +
                ", classNum=" + classNum +
                ", courseNum=" + courseNum +
                ", pptNum=" + pptNum +
                ", hwNum=" + hwNum +
                '}';
    }
}
