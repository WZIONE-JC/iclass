package com.iclass.user.component.msg;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/21/2017 10:24 PM.
 *
 * 返回给客户端的信息
 */
public class ResponseMsg {

    private String code;

    private String msg;

    public ResponseMsg() {

    }

    public ResponseMsg(CodeMsg codeMsg) {
        code = codeMsg.getCode();
        msg = codeMsg.getMsg();
    }

    public void setCodeMsg(CodeMsg codeMsg) {
        code = codeMsg.getCode();
        msg = codeMsg.getMsg();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
