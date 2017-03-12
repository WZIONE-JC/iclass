package com.iclass.mybatis.dto;

import com.iclass.mybatis.po.Class;
import com.iclass.mybatis.po.Course;

import java.io.Serializable;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/11/2017 11:10 PM.
 */
public class ClassCourseDTO implements Serializable {

    private Class aClass;

    private Course course;

    public ClassCourseDTO(){}

    public ClassCourseDTO(Class aClass, Course course) {
        this.aClass = aClass;
        this.course = course;
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

    @Override
    public String toString() {
        return "ClassCourseDTO{" +
                "aClass=" + aClass +
                ", course=" + course +
                '}';
    }

    //    private Integer classid;
//
//    private String classcode;
//
//    private String classname;
//
//    private String classdescription;
//
//    private String classcreator;
//
//    private Date classcreatetime;
//
//    private Date classdeadline;
//
//    private String classcoursecode;
//
//    private Integer classstatus;
//
//    private Integer courseid;
//
//    private String coursecode;
//
//    private String coursename;
//
//    private String coursedescription;
//
//    private String coursegrade;
//
//    private Integer coursestatus;
//
//    public ClassCourseDTO() {
//    }
//
//    public ClassCourseDTO(Class c, Course course) {
//        if(c != null) {
//            if (c.getClassid() != null) {
//                this.classid = c.getClassid();
//            }
//            if (c.getClasscode() != null) {
//                this.classcode = c.getClasscode();
//            }
//            if (c.getClassname() != null) {
//                this.classname = c.getClassname();
//            }
//            if (c.getClassdescription() != null) {
//                this.classdescription = c.getClassdescription();
//            }
//            if (c.getClasscreator() != null) {
//                this.classcreator = c.getClasscreator();
//            }
//            if (c.getClasscreatetime() != null) {
//                this.classcreatetime = c.getClasscreatetime();
//            }
//            if (c.getClassdeadline() != null) {
//                this.classdeadline = c.getClassdeadline();
//            }
//            if (c.getClasscoursecode() != null) {
//                this.classcoursecode = c.getClasscoursecode();
//            }
//            if (c.getClassstatus() != null) {
//                this.classstatus = c.getClassstatus();
//            }
//        }
//
//        if(course !=null) {
//            if (course.getCourseid() != null) {
//                this.courseid = course.getCourseid();
//            }
//            if (course.getCoursecode() != null) {
//                this.coursecode = course.getCoursecode();
//            }
//            if (course.getCoursename() != null) {
//                this.coursename = course.getCoursename();
//            }
//            if (course.getCoursedescription() != null) {
//                this.coursedescription = course.getCoursedescription();
//            }
//            if (course.getCoursegrade() != null) {
//                this.coursegrade = course.getCoursegrade();
//            }
//            if (course.getCoursestatus() != null) {
//                this.coursestatus = course.getCoursestatus();
//            }
//        }
//    }
//
//    public Integer getClassid() {
//        return classid;
//    }
//
//    public void setClassid(Integer classid) {
//        this.classid = classid;
//    }
//
//    public String getClasscode() {
//        return classcode;
//    }
//
//    public void setClasscode(String classcode) {
//        this.classcode = classcode;
//    }
//
//    public String getClassname() {
//        return classname;
//    }
//
//    public void setClassname(String classname) {
//        this.classname = classname;
//    }
//
//    public String getClassdescription() {
//        return classdescription;
//    }
//
//    public void setClassdescription(String classdescription) {
//        this.classdescription = classdescription;
//    }
//
//    public String getClasscreator() {
//        return classcreator;
//    }
//
//    public void setClasscreator(String classcreator) {
//        this.classcreator = classcreator;
//    }
//
//    public Date getClasscreatetime() {
//        return classcreatetime;
//    }
//
//    public void setClasscreatetime(Date classcreatetime) {
//        this.classcreatetime = classcreatetime;
//    }
//
//    public Date getClassdeadline() {
//        return classdeadline;
//    }
//
//    public void setClassdeadline(Date classdeadline) {
//        this.classdeadline = classdeadline;
//    }
//
//    public String getClasscoursecode() {
//        return classcoursecode;
//    }
//
//    public void setClasscoursecode(String classcoursecode) {
//        this.classcoursecode = classcoursecode;
//    }
//
//    public Integer getClassstatus() {
//        return classstatus;
//    }
//
//    public void setClassstatus(Integer classstatus) {
//        this.classstatus = classstatus;
//    }
//
//    public Integer getCourseid() {
//        return courseid;
//    }
//
//    public void setCourseid(Integer courseid) {
//        this.courseid = courseid;
//    }
//
//    public String getCoursecode() {
//        return coursecode;
//    }
//
//    public void setCoursecode(String coursecode) {
//        this.coursecode = coursecode;
//    }
//
//    public String getCoursename() {
//        return coursename;
//    }
//
//    public void setCoursename(String coursename) {
//        this.coursename = coursename;
//    }
//
//    public String getCoursedescription() {
//        return coursedescription;
//    }
//
//    public void setCoursedescription(String coursedescription) {
//        this.coursedescription = coursedescription;
//    }
//
//    public String getCoursegrade() {
//        return coursegrade;
//    }
//
//    public void setCoursegrade(String coursegrade) {
//        this.coursegrade = coursegrade;
//    }
//
//    public Integer getCoursestatus() {
//        return coursestatus;
//    }
//
//    public void setCoursestatus(Integer coursestatus) {
//        this.coursestatus = coursestatus;
//    }
//
//    @Override
//    public String toString() {
//        return "ClassCourseDTO{" +
//                "classid=" + classid +
//                ", classcode='" + classcode + '\'' +
//                ", classname='" + classname + '\'' +
//                ", classdescription='" + classdescription + '\'' +
//                ", classcreator='" + classcreator + '\'' +
//                ", classcreatetime=" + classcreatetime +
//                ", classdeadline=" + classdeadline +
//                ", classcoursecode='" + classcoursecode + '\'' +
//                ", classstatus=" + classstatus +
//                ", courseid=" + courseid +
//                ", coursecode='" + coursecode + '\'' +
//                ", coursename='" + coursename + '\'' +
//                ", coursedescription='" + coursedescription + '\'' +
//                ", coursegrade='" + coursegrade + '\'' +
//                ", coursestatus=" + coursestatus +
//                '}';
//    }
}
