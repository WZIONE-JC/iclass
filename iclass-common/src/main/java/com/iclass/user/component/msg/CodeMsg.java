package com.iclass.user.component.msg;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 19:03.
 */
public enum CodeMsg {

    USERNAME_EXISTED("1001","用户名已存在"),USERCODE_EXISTED("1002","工号已存在"),
    USERNAME_CAN_USE("1003","用户名可用"),USERCODE_CAN_USE("1004","工号可用"),
    USER_NAEM_NOTNULL("1001", "用户名不允许为空"),
    LOGIN_SUCCESS("2001","登录成功"),LOGIN_FAILED("2002","登录失败，账号或密码错误"),
    SIGNUP_SUCCESS("2003","注册成功"),SIGNUP_FAILED("2004","注册失败,请重试"),
    CODE_CORRECT("3001","验证码正确"),CODE_NOTNULL("3002","验证码不能为空"),CODE_ERROR("3003","验证码错误");

    private String code;
    private String msg;

    CodeMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
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

    @Override
    public String toString() {
        return "CodeMsg{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
