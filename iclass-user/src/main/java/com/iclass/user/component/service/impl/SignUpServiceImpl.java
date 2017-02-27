package com.iclass.user.component.service.impl;

import com.iclass.user.component.md5.MD5;
import com.iclass.user.component.msg.CodeMsg;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.mybatis.dao.UserMapper;
import com.iclass.user.component.service.api.SignUpService;
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
@Service("SignUpService")
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
    public ResponseMsg signup(User user) {
        System.out.println("SignUpServiceImpl.signup: " + user);

        ResponseMsg responseMsg = new ResponseMsg();
        /**
         * 在这里做一下拦截，避免用户注册成功后，重复提交注册信息,
         * 主要验证下用户名和工号
         */
        responseMsg = validateExistService.isExistUserCode(user.getUsercode());
        if(responseMsg.getMsg().equals(CodeMsg.USERCODE_EXISTED)) {
            return responseMsg;
        } else {
            responseMsg = validateExistService.isExistUsername(user.getUsername());
            if (responseMsg.getMsg().equals(CodeMsg.USERNAME_EXISTED)) {
                return responseMsg;
            }else  {
                //如果用户名 和 工号都不存在的话，就执行插入
                user.setUserpassword(getMD5Password(user.getUserpassword()));
                user.setUserregisterdate(new String(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
                int result = userMapper.insert(user);
                if (result == 1) {
                    responseMsg.setCodeMsg(CodeMsg.SIGNUP_SUCCESS);
                } else {
                    responseMsg.setCodeMsg(CodeMsg.LOGIN_FAILED);
                    System.out.println("SignUpServiceImpl.signup failed ,result:" + result);
                }
            }
        }
        return responseMsg;
    }
}
