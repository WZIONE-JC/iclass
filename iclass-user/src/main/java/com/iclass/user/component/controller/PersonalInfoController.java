package com.iclass.user.component.controller;

import com.iclass.user.component.vo.SessionUser;
import com.iclass.user.component.service.impl.PersonalInfoServiceImpl;
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
    public SessionUser getUserInfoBySession(HttpServletRequest request) {
        return personalInfoService.getPersonalInfoBySession(request);
    }

    @RequestMapping("/getUserInfoByUsercode")
    public SessionUser getUserInfoByUserCode(String usercode) {
        if(usercode != null) {
            SessionUser sessionUser = personalInfoService.getPersonalInfoByUsercode(usercode);
            return sessionUser;
        }
        return null;
    }
}
