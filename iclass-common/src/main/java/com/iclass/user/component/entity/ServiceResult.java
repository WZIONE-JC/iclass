package com.iclass.user.component.entity;

import com.iclass.user.component.msg.CodeMsg;

import java.io.Serializable;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/20/2017 11:38 PM.
 */
public class ServiceResult<T> implements Serializable{

    /**
     * 是否执行成功
     */
    private Boolean isSuccess;

    /**
     * 数据
     */
    private T dataMap;

    /**
     * 错误码和错误信息
     */
    private CodeMsg codeMsg;

    /**
     * 详细信息，可以是详细原因
     */
    private String message;

    public ServiceResult() {

    }

    public ServiceResult(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public ServiceResult(T dataMap, CodeMsg codeMsg) {
        this.dataMap = dataMap;
        this.codeMsg = codeMsg;
    }

    public ServiceResult(T dataMap, CodeMsg codeMsg, String message) {
        this.dataMap = dataMap;
        this.codeMsg = codeMsg;
        this.message = message;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public T getDataMap() {
        return dataMap;
    }

    public void setDataMap(T dataMap) {
        this.dataMap = dataMap;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }

    public void setCodeMsg(CodeMsg codeMsg) {
        this.codeMsg = codeMsg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
