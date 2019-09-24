package com.iclass.mybatis.vo;

/**
 * iclass
 * <p>
 * Created by JasonTang on 4/16/2017 4:12 PM.
 */
public class RollcallVo {

    // 显示的名字
    private String name;

    // 值
    private Integer y;

    // 颜色
    private String color;

    // 切割
    private Boolean sliced;

    // 选中
    private Boolean selected;

    public RollcallVo() {}

    public RollcallVo(String name, Integer y, String color, Boolean sliced, Boolean selected) {
        this.name = name;
        this.y = y;
        this.color = color;
        this.sliced = sliced;
        this.selected = selected;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getSliced() {
        return sliced;
    }

    public void setSliced(Boolean sliced) {
        this.sliced = sliced;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
