package com.iclass.user.component.msg;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/21/2017 10:24 PM.
 *
 * 返回给客户端的信息
 */
public class ResponseMsg {

    private String msg;

    public ResponseMsg() {

    }

    public ResponseMsg(String msg) {
        this.msg = msg;
    }

    public ResponseMsg(Msg msg) {
        this.msg = msg.getMsg();
    }

    public void setMsg(Msg msg) {
        this.msg = msg.getMsg();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
