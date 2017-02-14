package com.iclass.user.controller;

import com.iclass.user.usermsg.UserCode;
import com.iclass.user.usermsg.UserException;
import com.iclass.user.usermsg.UserMsg;
import com.iclass.user.service.impl.ValidateExistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 16:53.
 */
@RestController
@RequestMapping("/ValidateUser")
public class ValidateUserController{

    @Autowired
    private ValidateExistServiceImpl validateIsExistImpl;

    @RequestMapping("/validateUsername")
    public UserMsg validateUsername(String username) {
        System.out.println("ValidateUserController.validateUsername: " + username);
        boolean result = validateIsExistImpl.isExistUsername(username);
        UserMsg userMsg;
        //如果返回true,表示存在
        if(result) {
            userMsg = new UserMsg(UserCode.USERNAMEEXISTED, UserException.USERNAMEEXISTED);
        } else {
            userMsg = new UserMsg(UserCode.USERNAMECANUSE, UserException.USERNAMECANUSE);
        }
        return userMsg;
    }

    @RequestMapping(value = "/validateUsercode")
    public UserMsg validateUsercode(String usercode) {
        System.out.println("ValidateUserController.validateUsercode: " + usercode);
        boolean result = validateIsExistImpl.isExistUserCode(usercode);
        UserMsg userMsg;
        if(result) {
            userMsg = new UserMsg(UserCode.USERCODEEXISTED, UserException.USERCODEEXISTED);
        } else {
            userMsg = new UserMsg(UserCode.USERCODECANUSE, UserException.USERCODECANUSE);
        }
//        String jsonData = "{\"code\":\"1001\", \"msg\":\"zhangsan\", \"telephone\":\"13612345678\"}";
//        String retStr = mycallback + "(" + jsonData + ")";
       return userMsg;
    }

}
