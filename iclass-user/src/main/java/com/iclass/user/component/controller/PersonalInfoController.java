package com.iclass.user.component.controller;

import com.iclass.user.component.service.api.PersonalInfoService;
import com.iclass.user.component.vo.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    private PersonalInfoService personalInfoService;

    @RequestMapping(value = "/getUserInfoBySession", method = {RequestMethod.GET, RequestMethod.POST})
    public SessionUser getUserInfoBySession(HttpServletRequest request) {
        return personalInfoService.getPersonalInfoBySession(request);
    }

    @RequestMapping(value = "/getUserInfoByUsercode", method = {RequestMethod.GET, RequestMethod.POST})
    public SessionUser getUserInfoByUserCode(String usercode) {
        if(usercode != null) {
            SessionUser sessionUser = personalInfoService.getPersonalInfoByUsercode(usercode);
            return sessionUser;
        }
        return null;
    }
}
