package com.iclass.user.service.api;

import com.iclass.user.UserMsg.UserMsg;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 19:57.
 */
public interface LoginService {

    public String getMD5Password(String password);

    public UserMsg login(String rolename, String username, String pwd, String code);

    public boolean validateVerificationCode(String code);
}
