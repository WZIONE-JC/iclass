package com.iclass.mybatis.dto;

import com.iclass.mybatis.po.Iclassfile;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/12/2017 10:06 PM.
 */
public class IclassfileDTO {

    private Iclassfile iclassfile;

    public IclassfileDTO(Iclassfile iclassfile) {
        this.iclassfile = iclassfile;
    }

    public IclassfileDTO(){}

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
                '}';
    }
}
