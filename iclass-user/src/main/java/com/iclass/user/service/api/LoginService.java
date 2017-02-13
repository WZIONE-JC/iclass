package com.iclass.user.service.api;

import com.iclass.user.UserMsg.UserMsg;

import javax.servlet.http.HttpServletRequest;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 19:57.
 */
public interface LoginService {

    public String getMD5Password(String password);

    public UserMsg login(HttpServletRequest request, String rolename, String username, String pwd, String code, String callback);

    public boolean validateVerificationCode(String code);

    public String getLoginedUserInfo(HttpServletRequest request);
}
