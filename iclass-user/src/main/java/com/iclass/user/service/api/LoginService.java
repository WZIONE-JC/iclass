package com.iclass.user.service.api;

import javax.servlet.http.HttpServletRequest;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 19:57.
 */
public interface LoginService {

    public String getMD5Password(String password);

    public boolean login(String username, String password);

    public boolean validateVerificationCode(HttpServletRequest request, String code);
}
