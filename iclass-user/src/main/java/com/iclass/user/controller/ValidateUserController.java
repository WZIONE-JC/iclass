package com.iclass.user.controller;

import com.iclass.user.UserMsg.USERCODE;
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
public class ValidateUserController {

    @Autowired
    private ValidateExistServiceImpl validateIsExistImpl;

    @RequestMapping("/validateUsername/{username}")
    public UserMsg validateUsername(@PathVariable String username) {
        boolean result = validateIsExistImpl.isExistUsername(username);
        UserMsg userMsg;
        //如果返回true,表示存在
        if(result) {
            userMsg = new UserMsg(USERCODE.EXIST,"用户名已存在");
        } else {
            userMsg = new UserMsg(USERCODE.NOEXIST, "用户名可以使用");
        }
        return userMsg;
    }

    @RequestMapping("/validateUsercode/{usercode}")
    public UserMsg validateUsercode(@PathVariable String usercode) {
        boolean result = validateIsExistImpl.isExistUserCode(usercode);
        UserMsg userMsg;
        if(result) {
            userMsg = new UserMsg(USERCODE.EXIST, "工号已存在");
        } else {
            userMsg = new UserMsg(USERCODE.NOEXIST, "工号可用");
        }
       return userMsg;
    }

}
