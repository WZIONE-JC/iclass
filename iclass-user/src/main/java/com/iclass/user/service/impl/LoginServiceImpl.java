package com.iclass.user.service.impl;

import com.iclass.user.md5.MD5;
import com.iclass.user.service.api.LoginService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 20:46.
 */
@Service
public class LoginServiceImpl implements LoginService{
    @Override
    public String getMD5Password(String password) {
        return MD5.getPwd(password);
    }

    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public boolean validateVerificationCode(HttpServletRequest request, String code) {
        if(code != null && !code.equals("")) {
            HttpSession session = request.getSession();
            String sessionVerificationCode = (String) session.getAttribute("verificationCode");
            System.out.println("session域中的验证码:" + sessionVerificationCode);
            return sessionVerificationCode.equalsIgnoreCase(code.trim());
        } else {
            System.out.println("验证码不能为空");
        }
        return false;
    }
}
