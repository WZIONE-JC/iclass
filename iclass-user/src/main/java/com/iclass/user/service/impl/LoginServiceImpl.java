package com.iclass.user.service.impl;

import com.iclass.user.UserMsg.USERCODE;
import com.iclass.user.UserMsg.UserMsg;
import com.iclass.user.md5.MD5;
import com.iclass.user.mybatis.dao.UserMapper;
import com.iclass.user.service.api.LoginService;
import com.iclass.verificationcode.service.imp.VerificationCodeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public UserMsg login(String username, String pwd, String code) {
        UserMsg userMsg;
        boolean codeCorrect = validateVerificationCode(code);
        if(codeCorrect) {
            String password = getMD5Password(pwd);
            String result = userMapper.findByUsernameAndPassword(username, password);
            if(result != null) {
                userMsg = new UserMsg(USERCODE.LOGINSUCCESS, "登录成功");
            } else {
                userMsg = new UserMsg(USERCODE.LOGINFAILED, "账号或密码错误");
            }
        } else {
            userMsg = new UserMsg(USERCODE.CODEERROR, "验证码错误");
        }
        return userMsg;
    }

    @Override
    public boolean validateVerificationCode(String code) {
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
