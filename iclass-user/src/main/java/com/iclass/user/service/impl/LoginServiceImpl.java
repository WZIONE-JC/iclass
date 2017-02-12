package com.iclass.user.service.impl;

import com.iclass.user.UserMsg.UserCode;
import com.iclass.user.UserMsg.UserException;
import com.iclass.user.UserMsg.UserMsg;
import com.iclass.user.md5.MD5;
import com.iclass.user.mybatis.dao.UserMapper;
import com.iclass.user.service.api.LoginService;
import com.iclass.verificationcode.service.imp.VerificationCodeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 20:46.
 */
@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VerificationCodeImpl verificationCode;

    @Override
    public String getMD5Password(String password) {
        return MD5.getPwd(password);
    }

    @Override
    public UserMsg login(String username, String password, String code) {

        UserMsg userMsg;

        /**
         * 首先验证验证码，通过validateVerificationCode 方法去验证
         * 这里我考虑是从前端去验证
         */
        boolean codeCorrect = validateVerificationCode(code);

        /**
         * 如果登录方式是工号
         */
        Pattern p = Pattern.compile("[\\d]{10}");
        Matcher m = p.matcher(username);

        if(codeCorrect) {
            //将用户输入的密码进行加密
            String newPassword = getMD5Password(password);

            String result;
            // 以工号的方式登录
            if(m.matches()) {
                System.out.println("使用工号登录");
                result = userMapper.findByUsercodeAndPassword(username, newPassword);
            } else {
                //使用用户名的方式去登录
                System.out.println("使用用户名登录");
                result = userMapper.findByUsernameAndPassword(username, newPassword);
            }
            System.out.println("欢迎 " + username + " " + result + " 登录");
            if(result != null) {
                //查找到，就显示登录成功的信息，返回给前端
                userMsg = new UserMsg(UserCode.LOGINSUCCESS, UserException.LOGINSUCCESS);
            } else {
                //result为空，表示没有查找到用户信息
                userMsg = new UserMsg(UserCode.LOGINFAILED, UserException.LOGINFAILED);
            }
        } else {
            userMsg = new UserMsg(UserCode.CODEERROR, UserException.CODEERROR);
        }
        return userMsg;
    }

    @Override
    public boolean validateVerificationCode(String code) {
        System.out.println("LoginServiceImpl.validateVerificationCode");
        if(code != null && !code.equals("")) {
            String stringCode = verificationCode.getVerificationCode();
            if(stringCode != null) {
                System.out.println("获取到的stringCode:" + stringCode + ":" + code.trim());
                System.out.println("stringCode.equalsIgnoreCase(code.trim()):"+stringCode.equalsIgnoreCase(code.trim()));
                return stringCode.equalsIgnoreCase(code.trim());
            } else {
                System.out.println("获取到的stringCode:"+stringCode);
            }
        } else {
            System.out.println("验证码不能为空");
        }
        return false;
    }
}
