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
    private Boolean success = false;

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
}
