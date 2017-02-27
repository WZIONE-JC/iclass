package com.iclass.user.component.controller;

import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.service.api.ValidateExistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    private ValidateExistService validateIsExistImpl;

    @RequestMapping(value = "/validateUsername", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseMsg validateUsername(String username) {
        System.out.println("ValidateUserController.validateUsername: " + username);
       return validateIsExistImpl.isExistUsername(username);
    }

    @RequestMapping(value = "/validateUsercode", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseMsg validateUsercode(String usercode) {
        System.out.println("ValidateUserController.validateUsercode: " + usercode);
        return  validateIsExistImpl.isExistUserCode(usercode);
//        String jsonData = "{\"code\":\"1001\", \"msg\":\"zhangsan\", \"telephone\":\"13612345678\"}";
//        String retStr = mycallback + "(" + jsonData + ")";
    }

}
