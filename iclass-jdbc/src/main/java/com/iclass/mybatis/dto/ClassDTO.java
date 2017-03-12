package com.iclass.mybatis.dto;

import com.iclass.mybatis.po.Class;

import java.io.Serializable;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/12/2017 12:56 PM.
 */
public class ClassDTO implements Serializable{

    private Class aClass;

    public ClassDTO(Class aClass) {
        this.aClass = aClass;
    }
    public ClassDTO() {}

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    @Override
    public String toString() {
        return "ClassDTO{" +
                "aClass=" + aClass +
                '}';
    }
}
