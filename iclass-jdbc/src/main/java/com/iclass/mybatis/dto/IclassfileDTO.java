package com.iclass.mybatis.dto;

import com.iclass.mybatis.po.Iclassfile;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/12/2017 10:06 PM.
 */
public class IclassfileDTO {

    private Iclassfile iclassfile;

    private String teacherName;

    public IclassfileDTO(Iclassfile iclassfile, String teacherName) {
        this.iclassfile = iclassfile;
        this.teacherName = teacherName;
    }

    public IclassfileDTO(){}

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Iclassfile getIclassfile() {
        return iclassfile;
    }

    public void setIclassfile(Iclassfile iclassfile) {
        this.iclassfile = iclassfile;
    }

    @Override
    public String toString() {
        return "IclassfileDTO{" +
                "iclassfile=" + iclassfile +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
