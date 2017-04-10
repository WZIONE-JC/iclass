package com.iclass.user.component.entity;

import com.iclass.user.component.msg.ResponseMsg;

import java.io.Serializable;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/20/2017 11:38 PM.
 */
public class ServiceResult<T> implements Serializable {

    /**
     * 是否执行成功
     */
    private Boolean success = Boolean.FALSE;

    /**
     * 数据
     */
    private T data;

    /**
     * 错误码和错误信息
     */
    private ResponseMsg responseMsg;

    /**
     * 详细信息，可以是详细原因
     */
    private String message;


    // Datatables 支持

    /**
     * Datatables发送的draw是多少那么服务器就返回多少
     */
    private Integer draw;

    /**
     * 数据库里总共记录数
     */
    private Integer recordsTotal;

    /**
     * 过滤后的记录数（如果有接收到前台的过滤条件，则返回的是过滤后的记录数）
     */
    private Integer recordsFiltered;


    public ServiceResult() {

    }

    public ServiceResult(Boolean success, ResponseMsg responseMsg) {
        this.success = success;
        this.responseMsg = responseMsg;
    }

    public ServiceResult(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseMsg getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(ResponseMsg responseMsg) {
        this.responseMsg = responseMsg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    @Override
    public String toString() {
        return "ServiceResult{" +
                "success=" + success +
                ", data=" + data +
                ", responseMsg=" + responseMsg +
                ", message='" + message + '\'' +
                ", draw=" + draw +
                ", recordsTotal=" + recordsTotal +
                ", recordsFiltered=" + recordsFiltered +
                '}';
    }

}
