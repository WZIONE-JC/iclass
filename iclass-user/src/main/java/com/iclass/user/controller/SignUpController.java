package com.iclass.user.controller;

import com.iclass.user.usermsg.UserMsg;
import com.iclass.user.mybatis.model.User;
import com.iclass.user.service.impl.SignUpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/12/2017 4:32 PM.
 */
@RestController
public class SignUpController {

    @Autowired
    private SignUpServiceImpl signUpService;

    @RequestMapping("/signup")
    public UserMsg signup(User user) {
        return signUpService.signup(user);
    }
}
