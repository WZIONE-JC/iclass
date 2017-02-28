package com.iclass.user.component.controller;

import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.service.api.PersonalInfoService;
import com.iclass.user.component.vo.SessionUser;
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

    @RequestMapping(value = "/getUserInfoBySession", method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResult<SessionUser> getUserInfoBySession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return personalInfoService.getPersonalInfoBySession(session);
    }

    @RequestMapping(value = "/getUserInfoByUsercode", method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResult<SessionUser> getUserInfoByUserCode(String usercode) {
        ServiceResult<SessionUser> serviceResult = new ServiceResult<>();
        if(usercode != null) {
            serviceResult = personalInfoService.getPersonalInfoByUsercode(usercode);
            logger.info("通过usercode:" + usercode + "获取到的用户信息为:" + serviceResult.getData());
            return serviceResult;
        } else {
            logger.error("通过usercode获取用户信息时,usercode参数不能为空");
            serviceResult.setMessage("通过usercode获取用户信息时,usercode参数不能为空");
            return serviceResult;
        }
    }
}
