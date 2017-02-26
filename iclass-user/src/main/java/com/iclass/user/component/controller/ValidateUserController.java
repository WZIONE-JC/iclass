package com.iclass.user.component.controller;

import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.service.impl.ValidateExistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 16:53.
 */
@RestController
@RequestMapping("/user")
public class ValidateUserController{

    @Autowired
    private ValidateExistServiceImpl validateIsExistImpl;

    @RequestMapping("/validateUsername")
    public ResponseMsg validateUsername(String username) {
        System.out.println("ValidateUserController.validateUsername: " + username);
       return validateIsExistImpl.isExistUsername(username);
    }

    @RequestMapping(value = "/validateUsercode")
    public ResponseMsg validateUsercode(String usercode) {
        System.out.println("ValidateUserController.validateUsercode: " + usercode);
//        String jsonData = "{\"code\":\"1001\", \"msg\":\"zhangsan\", \"telephone\":\"13612345678\"}";
//        String retStr = mycallback + "(" + jsonData + ")";
       return  validateIsExistImpl.isExistUserCode(usercode);
    }

}
