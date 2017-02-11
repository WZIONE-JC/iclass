package com.iclass.user.controller;

import com.iclass.user.UserMsg.UserCode;
import com.iclass.user.UserMsg.UserException;
import com.iclass.user.UserMsg.UserMsg;
import com.iclass.user.service.impl.ValidateExistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/validateUsername/{username}")
    public UserMsg validateUsername(@PathVariable String username) {
        System.out.println(username);
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

    @RequestMapping(value = "/validateUsercode/{usercode}")
    public UserMsg validateUsercode(@PathVariable String usercode) {
        System.out.println(usercode);
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
