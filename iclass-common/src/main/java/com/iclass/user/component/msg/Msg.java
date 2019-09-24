package com.iclass.user.component.msg;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 19:03.
 */
public enum Msg {

    OK("ok"),
    USERNAME_EXISTED("用户名已存在"),USERCODE_EXISTED("工号已存在"),
    USERNAME_CAN_USE("用户名可用"),USERCODE_CAN_USE("工号可用"),
    USER_NAEM_NOTNULL("用户名不允许为空"),LOGOUT_SUCCESS("用户注销成功"),LOGOUT_FAILED("用户注销失败"),
    LOGIN_SUCCESS("登录成功"),LOGIN_FAILED("登录失败，账号或密码错误"),
    SIGNUP_SUCCESS("注册成功"),SIGNUP_FAILED("注册失败,请重试"),
    CODE_CORRECT("验证码正确"),CODE_NOTNULL("验证码不能为空"),CODE_ERROR("验证码错误"),
    UPDATE_USER_SUCCESS("用户信息更新成功"),UPDATE_USER_FAILED("用户信息更新失败"),
    UPDATE_PASSWORD_SUCCESS("修改密码成功"), UPDATE_PASSWORD_FAILED("修改密码失败"),
    FILE_UPLOAD_SUCCESS("文件上传成功"),FILE_UPLOAD_FAILED("文件上传失败"),
    FILE_DOWNLOAD_SUCCESS("文件下载成功"),FILE_DOWNLOAD_FAILED("文件下载失败"),
    CACHE_SUCCESS("缓存设置成功"), CACHE_FAILED("缓存设置失败"),
    CLASS_CODE_EXISTED("课堂编号已存在"),COURSE_CODE_EXISTED("课程编号已存在");

    private String msg;

    Msg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
