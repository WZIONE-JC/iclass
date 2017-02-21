package com.iclass.user.component.controller;

import com.iclass.user.component.msg.CodeMsg;
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
    public CodeMsg validateUsername(String username) {
        System.out.println("ValidateUserController.validateUsername: " + username);
        boolean result = validateIsExistImpl.isExistUsername(username);
        CodeMsg codeMsg;
        //如果返回true,表示存在
        if(result) {
            codeMsg = CodeMsg.USERNAME_EXISTED;
        } else {
            codeMsg = CodeMsg.USERNAME_CAN_USE;
        }
        return codeMsg;
    }

    @RequestMapping(value = "/validateUsercode")
    public CodeMsg validateUsercode(String usercode) {
        System.out.println("ValidateUserController.validateUsercode: " + usercode);
        boolean result = validateIsExistImpl.isExistUserCode(usercode);
        CodeMsg codeMsg;
        if(result) {
            codeMsg = CodeMsg.USERCODE_EXISTED;
        } else {
            codeMsg = CodeMsg.USERCODE_CAN_USE;
        }
//        String jsonData = "{\"code\":\"1001\", \"msg\":\"zhangsan\", \"telephone\":\"13612345678\"}";
//        String retStr = mycallback + "(" + jsonData + ")";
       return codeMsg;
    }

}
