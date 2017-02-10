package com.iclass.user.controller;

import com.iclass.user.UserMsg.UserMsg;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 20:54.
 */
@RestController
public class LoginController {

    @RequestMapping("/login")
    public UserMsg login(String username, String password, String code) {
        UserMsg userMsg = null;
        return userMsg;
    }
}
