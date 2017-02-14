package com.iclass.user.service.impl;

import com.iclass.user.usermsg.UserCode;
import com.iclass.user.usermsg.UserException;
import com.iclass.user.usermsg.UserMsg;
import com.iclass.user.md5.MD5;
import com.iclass.user.mybatis.dao.UserMapper;
import com.iclass.user.service.api.SignUpService;
import com.iclass.user.mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/12/2017 12:26 PM.
 */
@Service
public class SignUpServiceImpl implements SignUpService{

    @Autowired
    UserMapper userMapper;

    @Autowired ValidateExistServiceImpl validateExistService;

    @Override
    public String getMD5Password(String password) {
        return MD5.getPwd(password);
    }

    @Override
    @Transactional
    public UserMsg signup(User user) {
        System.out.println("SignUpServiceImpl.signup: " + user);
        UserMsg userMsg;
        /**
         * 在这里做一下拦截，避免用户注册成功后，重复提交注册信息,
         * 主要验证下用户名和工号
         */
        boolean existUserCode = validateExistService.isExistUserCode(user.getUsercode());
        if(existUserCode) {
            userMsg = new UserMsg(UserCode.USERCODEEXISTED, UserException.USERCODEEXISTED);
        } else {
            boolean existUsername = validateExistService.isExistUsername(user.getUsername());
            if (existUsername) {
                userMsg = new UserMsg(UserCode.USERNAMEEXISTED, UserException.USERNAMEEXISTED);
            }else  {
                //如果用户名 和 工号都不存在的话，就执行插入
                user.setUserpassword(getMD5Password(user.getUserpassword()));
                user.setUserregisterdate(new String(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
                int result = userMapper.insert(user);
                if (result == 1) {
                    userMsg = new UserMsg(UserCode.SIGNUPSUCCESS, UserException.SIGNUPSUCCESS);
                } else {
                    userMsg = new UserMsg(UserCode.SIGNUPFAILED, UserException.SIGNUPFAILED);
                    System.out.println("SignUpServiceImpl.signup failed ,result:" + result);
                }
            }
        }
        return userMsg;
    }
}
