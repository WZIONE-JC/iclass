package com.iclass.mybatis.vo;

/**
 * iclass
 * <p>
 * Created by JasonTang on 5/16/2017 2:27 PM.
 * <p>
 * 课堂互动图表数据
 */
public class ChartClasshdData {

    /**
     * 最下方的name(课堂名)
     */
    private String name;

    /**
     * 课堂整体作对人数
     */
    private Integer y;

    /**
     * 钻取数据详情的id(课堂名)
     */
    private String drilldown;

    /**
     * 课堂的详细数据
     */
    private ChartClasshdSeriesData drilldownData;

    public ChartClasshdData(String name, Integer y, String drilldown, ChartClasshdSeriesData drilldownData) {
        this.name = name;
        this.y = y;
        this.drilldown = drilldown;
        this.drilldownData = drilldownData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getDrilldown() {
        return drilldown;
    }

    public void setDrilldown(String drilldown) {
        this.drilldown = drilldown;
    }

    public ChartClasshdSeriesData getDrilldownData() {
        return drilldownData;
    }

    public void setDrilldownData(ChartClasshdSeriesData drilldownData) {
        this.drilldownData = drilldownData;
    }
}
