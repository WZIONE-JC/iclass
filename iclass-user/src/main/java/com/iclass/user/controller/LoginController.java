package com.iclass.user.controller;

import com.iclass.user.UserMsg.UserMsg;
import com.iclass.user.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 20:54.
 */
@RestController
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @RequestMapping("/login")
    public UserMsg login(HttpServletRequest request, String rolename, String username,
                         String password, String code, String callback) {
        return loginService.login(request, rolename, username, password, code, callback);
    }

    @RequestMapping(value = "/getLoginedUserInfo")
    @ResponseBody
    public String getLoginedUserInfo(HttpServletRequest request) {
        System.out.println("LoginController.getLoginedUserInfo : " + request);
        return loginService.getLoginedUserInfo(request);
    }
}
