package com.iclass.user.controller;

import com.iclass.user.UserMsg.UserMsg;
import com.iclass.user.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public UserMsg login(String rolename, String username,
                         String password, String code) {
        return loginService.login(rolename, username, password, code);

    }
}
