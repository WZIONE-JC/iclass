package com.iclass.mybatis.vo;

/**
 * iclass
 * <p>
 * Created by JasonTang on 5/16/2017 9:08 PM.
 * 点名次数Vo
 */
public class TimesVo {

    private String name;

    private Integer value;

    public TimesVo(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
