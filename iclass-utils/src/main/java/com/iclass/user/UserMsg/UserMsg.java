package com.iclass.user.UserMsg;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 18:19.
 */
public class UserMsg {

    private String code;

    private String msg;

    private String data;

    public UserMsg() {}

    public UserMsg(UserCode code, UserException msg) {
        this.code = code.getValue();
        this.msg = msg.getDesc();
    }

    public UserMsg(UserCode code, UserException msg, String data) {
        this.code = code.getValue();
        this.msg = msg.getDesc();
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(UserCode code) {
        this.code = code.getValue();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(UserException msg) {
        this.msg = msg.getDesc();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
