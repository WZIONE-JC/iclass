package com.iclass.user.utils;

import com.iclass.user.mybatis.model.User;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/14 12:39.
 */
public class UserInfoHandler {

    public static User userRegisterDateHandler(User user) {
        String oldUserRegisterDate = user.getUserregisterdate();
        String userRegisterDate = oldUserRegisterDate.split("\\.")[0];
        System.out.println("UserInfoHandler.userRegisterDateHandler:"+userRegisterDate);
        user.setUserregisterdate(userRegisterDate);
        System.out.println("UserInfoHandler.userRegisterDataHandler:"+user);
        return user;
    }
}
