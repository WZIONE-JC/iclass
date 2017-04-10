package com.iclass.mybatis.po;

import com.iclass.user.component.utils.IclassUtil;

import java.util.Date;

public class Log {
    private Integer id;

    private String ip;

    private String device;

    private String httpMethod;

    private String operation;

    private String method;

    private String args;

    private String exetime;

    private String logtime;

    private String logdata;

    private String logerror;

    public Log() {}

    public Log(Integer id, String ip, String device, String httpMethod, String operation, String method, String args, String exetime, String logtime, String logdata, String logerror) {
        this.id = id;
        this.ip = ip;
        this.device = device;
        this.httpMethod = httpMethod;
        this.operation = operation;
        this.method = method;
        this.args = args;
        this.exetime = exetime;
        this.logtime = IclassUtil.formatTime(logtime);
        this.logdata = logdata;
        this.logerror = logerror;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getExetime() {
        return exetime;
    }

    public void setExetime(String exetime) {
        this.exetime = exetime;
    }

    public String getLogtime() {
        return logtime;
    }

    public void setLogtime(String logtime) {
        this.logtime = IclassUtil.formatTime(logtime);
    }

    public String getLogdata() {
        return logdata;
    }

    public void setLogdata(String logdata) {
        this.logdata = logdata;
    }

    public String getLogerror() {
        return logerror;
    }

    public void setLogerror(String logerror) {
        this.logerror = logerror;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", device='" + device + '\'' +
                ", httpMethod='" + httpMethod + '\'' +
                ", operation='" + operation + '\'' +
                ", method='" + method + '\'' +
                ", args='" + args + '\'' +
                ", exetime='" + exetime + '\'' +
                ", logtime='" + logtime + '\'' +
                ", logdata='" + logdata + '\'' +
                ", logerror='" + logerror + '\'' +
                '}';
    }
}