package com.iclass.mybatis.dto;

import com.iclass.mybatis.UserInfoHandler;
import com.iclass.mybatis.po.User;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/20/2017 11:45 PM.
 */
public class SessionUser {

    /**
     * 用户信息
     */
    private User user;

    public SessionUser() {

    }

    public SessionUser(User user) {
        this.user = user;
        this.user = UserInfoHandler.userPasswordHandler(user);
        this.user = UserInfoHandler.userRegisterDateHandler(user);
        this.user = user;
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

    @Override
    public String toString() {
        return "SessionUser{" +
                "user=" + user +
                '}';
    }
}
