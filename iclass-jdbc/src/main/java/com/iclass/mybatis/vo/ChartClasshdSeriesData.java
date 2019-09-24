package com.iclass.mybatis.vo;

import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 5/16/2017 2:34 PM.
 */
public class ChartClasshdSeriesData {

    /**
     * 在详情里面要在柱子上显示的name(课堂名称)
     */
    private String name;

    /**
     * 此处的id要和ChartClasshdData.drilldown一致
     */
    private String id;

    /**
     * 详情里面的数据
     */
    private List<ChartMetaData> data;

    public ChartClasshdSeriesData(String name, String id, List<ChartMetaData> data) {
        this.name = name;
        this.id = id;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ChartMetaData> getData() {
        return data;
    }

    public void setData(List<ChartMetaData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ChartClasshdSeriesData{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", data=" + data +
                '}';
    }
}
