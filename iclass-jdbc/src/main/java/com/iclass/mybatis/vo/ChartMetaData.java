package com.iclass.mybatis.vo;

/**
 * iclass
 * <p>
 * Created by JasonTang on 5/16/2017 2:31 PM.
 * 图表细节的柱形 的基础数据
 */
public class ChartMetaData {

    /**
     * 下面的name（题目名）
     */
    private String name;

    /**
     * 一个课堂的作对率
     */
    private Double value;

    public ChartMetaData(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ChartMetaData{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
