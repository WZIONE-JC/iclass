package com.iclass.user.component.controller;

import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.service.api.ValidateExistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ValidateUserController {

    private final Logger logger = LoggerFactory.getLogger(ValidateUserController.class);
    @Autowired
    private ValidateExistService validateIsExistImpl;

    @RequestMapping(value = "/validateUsername", method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResult<ResponseMsg> validateUsername(String username) {
        return validateIsExistImpl.isExistUsername(username);
    }

    @RequestMapping(value = "/validateUsercode", method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResult<ResponseMsg> validateUsercode(String usercode) {
        return validateIsExistImpl.isExistUserCode(usercode);
//        String jsonData = "{\"code\":\"1001\", \"msg\":\"zhangsan\", \"telephone\":\"13612345678\"}";
//        String retStr = mycallback + "(" + jsonData + ")";
    }

}
