package com.iclass.mybatis.vo;

import java.io.Serializable;

/**
 * iclass
 * <p>
 * Created by JasonTang on 5/16/2017 9:56 PM.
 * app端显示数据的元数据
 */
public class RollCallMetaData implements Serializable{

    private static final long serialVersionUID = 4292979114290165512L;
    /**
     * 签到状态
     */
    private int status;

    /**
     * 签到时间
     */
    private String time;

    public RollCallMetaData(int status, String time) {
        this.status = status;
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
