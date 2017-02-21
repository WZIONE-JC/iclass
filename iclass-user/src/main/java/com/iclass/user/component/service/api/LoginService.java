package com.iclass.user.component.service.api;

import com.iclass.user.cache.entity.SessionUser;
import com.iclass.user.component.msg.ResponseMsg;

import javax.servlet.http.HttpServletRequest;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 19:57.
 */
public interface LoginService {

    public String getMD5Password(String password);

    public ResponseMsg login(HttpServletRequest request, String username, String pwd, String rolename, String code);

    public boolean validateVerificationCode(HttpServletRequest request, String code);

    public SessionUser getLoginedUserInfo(HttpServletRequest request);
}
