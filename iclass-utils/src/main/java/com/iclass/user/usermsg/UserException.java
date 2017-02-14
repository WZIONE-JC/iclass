package com.iclass.user.usermsg;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/11/2017 1:56 PM.
 *
 * 定义用户异常信息
 */
public enum  UserException {

    USERNAMEEXISTED("用户名已存在"),USERCODEEXISTED("工号已存在"),USERNAMECANUSE("用户名可用"),USERCODECANUSE("工号可用"),
    LOGINSUCCESS("登录成功"),LOGINFAILED("登录失败，账号或密码错误"),SIGNUPSUCCESS("注册成功"),SIGNUPFAILED("注册失败,请重试"),
    CODECORRECT("验证码正确"),CODENOTNULL("验证码不能为空"),CODEERROR("验证码错误");

    private String code;
    private String desc;

    UserException(String desc) {
        this.desc = desc;
    }

    UserException(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
