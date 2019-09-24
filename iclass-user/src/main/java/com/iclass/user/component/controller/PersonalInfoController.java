package com.iclass.user.component.controller;

import com.iclass.mybatis.dto.SessionUser;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.service.api.PersonalInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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

    @RequestMapping(value = "/getUserInfoByUsercode", method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResult<SessionUser> getUserInfoByUserCode(String usercode) {

        return personalInfoService.getPersonalInfoByUsercode(usercode);
    }

    @RequestMapping(value = "/getUserInfoBySession", method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResult<List<SessionUser>> getUserInfoBySession(DataTablesRequestEntity requestEntity, HttpServletRequest request) {
        HttpSession session = request.getSession();

        return personalInfoService.getPersonalInfoBySession(requestEntity, session);
    }

}
