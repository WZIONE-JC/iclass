package com.iclass.mybatis.dto;

import com.iclass.mybatis.po.Class;
import com.iclass.mybatis.po.Course;

import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/12/2017 1:58 PM.
 * 已上传课件和作业浏览页面
 */
public class PPTHWDTO {

    /**
     * 课堂信息
     */
    private Class aClass;

    /**
     * 课程信息
     */
    private Course course;

    /**
     * 课件信息
     * FileType
     * 0:作业，1:PPT
     */
    private List<IclassfileDTO> iclassfiles;

    /**
     * 教师姓名
     * teacherFullName
     */
    private String teacherName;

    public PPTHWDTO() {}

    public PPTHWDTO(Class aClass, Course course, List<IclassfileDTO> iclassfiles, String teacherName) {
        this.aClass = aClass;
        this.course = course;
        this.iclassfiles = iclassfiles;
        this.teacherName = teacherName;
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

    public List<IclassfileDTO> getIclassfiles() {
        return iclassfiles;
    }

    public void setIclassfiles(List<IclassfileDTO> iclassfiles) {
        this.iclassfiles = iclassfiles;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "PPTHWDTO{" +
                "aClass=" + aClass +
                ", course=" + course +
                ", iclassfiles=" + iclassfiles +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
