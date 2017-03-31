package com.iclass.user.component.controller;

import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.service.api.UserInfoService;
import com.iclass.mybatis.dto.SessionUser;
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
}
