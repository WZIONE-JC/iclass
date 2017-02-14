package com.iclass.user.controller;

import com.iclass.user.mybatis.model.User;
import com.iclass.user.service.impl.PersonalInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/13 15:23.
 */
@RestController
@RequestMapping("/user")
public class PersonalInfoController {

    @Autowired
    private PersonalInfoServiceImpl personalInfoService;

    @RequestMapping("/getUserInfoBySession")
    public User getUserInfoBySession(HttpServletRequest request) {
        return personalInfoService.getPersonalInfoBySession(request);
    }

    @RequestMapping("/getUserInfoByUsercode")
    public User getUserInfoByUserCode(String usercode) {
        if(usercode != null) {
            User user = personalInfoService.getPersonalInfoByUsercode(usercode);
            return user;
        }
        return null;
    }
}
