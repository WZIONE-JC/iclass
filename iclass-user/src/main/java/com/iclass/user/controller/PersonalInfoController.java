package com.iclass.user.controller;

import com.iclass.user.mybatis.model.User;
import com.iclass.user.service.impl.PersonalInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/getUserInfo")
    public User getUserInfo(String usercode) {
        System.out.println("PersonalInfoController.getUserInfo:" + usercode);
        return personalInfoService.getPersonalInfo(usercode);
    }

}
