package com.iclass.user.component.controller;

import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.service.api.PersonalInfoService;
import com.iclass.user.component.service.api.UserInfoService;
import com.iclass.user.component.vo.SessionUser;
import com.iclass.user.mybatis.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/13 15:23.
 */
@RestController
@RequestMapping("/user")
public class PersonalInfoController {

    private Logger logger = LoggerFactory.getLogger(PersonalInfoController.class);

    @Autowired
    private PersonalInfoService personalInfoService;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/getUserInfoByUsercode", method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResult<SessionUser> getUserInfoByUserCode(String usercode) {

        return personalInfoService.getPersonalInfoByUsercode(usercode);
    }

    @RequestMapping(value = "/getUserInfoBySession", method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResult<SessionUser> getUserInfoBySession(HttpServletRequest request) {
        HttpSession session = request.getSession();

        return personalInfoService.getPersonalInfoBySession(session);
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST, RequestMethod.GET})
    public ServiceResult<ResponseMsg> updateUser(User user) {

        return userInfoService.updatePersonalInfo(user);
    }

    @RequestMapping(value = "/changePwd", method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResult<ResponseMsg> changePassword(String usercode, String oldPassword, String newPassword) {

        return userInfoService.updateUserPassword(usercode, oldPassword, newPassword);
    }
}
