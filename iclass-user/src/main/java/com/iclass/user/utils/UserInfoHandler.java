package com.iclass.user.utils;

import com.iclass.user.mybatis.model.User;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/14 12:39.
 */
public class UserInfoHandler {

    /**
     * 去掉日期后面的 毫秒数 .0
     * @param user
     * @return
     */
    public static User userRegisterDateHandler(User user) {
        if(user != null) {
            String oldUserRegisterDate = user.getUserregisterdate();
            String userRegisterDate = oldUserRegisterDate.split("\\.")[0];
            System.out.println("UserInfoHandler.userRegisterDateHandler:" + userRegisterDate);
            user.setUserregisterdate(userRegisterDate);
            System.out.println("UserInfoHandler.userRegisterDataHandler:" + user);
        } else {
            System.out.println("UserInfoHandler.userRegisterDateHandler: user不能为空：" + user);
        }
        return user;
    }

    /**
     * 去掉用户信息中的password信息
     * @param user
     * @return
     */
    public static User userpasswordHandler(User user) {
        if(user != null) {
            user.setUserpassword("");
            System.out.println("UserInfoHandler.userpasswordHandler: 已处理password信息 : "+user);
        } else {
            System.out.println("UserInfoHandler.userpasswordHandler: user不能为空: "+user);
        }
        return user;
    }
}
