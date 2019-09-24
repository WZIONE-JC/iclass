package com.iclass.mybatis.dto;

import com.iclass.mybatis.UserInfoHandler;
import com.iclass.mybatis.po.User;

import java.io.Serializable;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/20/2017 11:45 PM.
 */
public class SessionUser implements Serializable{

    /**
     * 用户信息
     */
    private User user;

    private String className;

    public SessionUser() {

    }

    public SessionUser(User user) {
        this.user = UserInfoHandler.userPasswordHandler(user);
        this.user = UserInfoHandler.userRegisterDateHandler(this.user);
    }

    public SessionUser(User user, String className) {
        this.user = UserInfoHandler.userPasswordHandler(user);
        this.user = UserInfoHandler.userRegisterDateHandler(this.user);
        this.className = className;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.user = UserInfoHandler.userPasswordHandler(user);
        this.user = UserInfoHandler.userRegisterDateHandler(user);
        this.user = user;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "SessionUser{" +
                "user=" + user +
                ", className='" + className + '\'' +
                '}';
    }
}
