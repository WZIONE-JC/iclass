package com.iclass.user.component.controller;

import com.iclass.mybatis.dto.SessionUser;
import com.iclass.mybatis.po.User;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.service.api.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/5/2017 4:43 PM.
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/all", method = {RequestMethod.POST, RequestMethod.GET})
    public ServiceResult<List<SessionUser>> getAll(DataTablesRequestEntity requestEntity) {
        return userInfoService.getAll(requestEntity);
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
