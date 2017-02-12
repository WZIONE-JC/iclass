package com.iclass.user.controller;

import com.iclass.user.UserMsg.UserMsg;
import com.iclass.user.service.impl.LoginServiceImpl;
import org.apache.ibatis.annotations.Param;
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
    public UserMsg login(@Param("username") String username,
                         @Param("password")String password, String code) {
        return loginService.login(username, password, code);

    }
}
