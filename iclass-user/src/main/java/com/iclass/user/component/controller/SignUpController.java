package com.iclass.user.component.controller;

import com.iclass.mybatis.model.User;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.service.api.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/12/2017 4:32 PM.
 */
@RestController
@RequestMapping("/user")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @RequestMapping(value = "/signup", method = {RequestMethod.POST,RequestMethod.GET})
    public ServiceResult<ResponseMsg> signup(User user) {
        return signUpService.signup(user);
    }
}
