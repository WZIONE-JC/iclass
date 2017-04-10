package com.iclass.user.component.controller;

import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.service.api.LoginService;
import com.iclass.mybatis.dto.SessionUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 20:54.
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     * @param request 获取session
     * @param userrole 角色
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @return 返回消息实体
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public ServiceResult<ResponseMsg> login(HttpServletRequest request, String userrole, String username,
                                            String password, String code, @RequestParam(required = false) String remember) {
        return loginService.login(request, userrole, username, password, code, remember);
    }

    /**
     * 获取已登录用户的信息
     * @param request 获取session
     * @return 返回jsonp 数据，或者null
     */
    @RequestMapping(value = "/getLoginedUserInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResult<SessionUser> getLoginedUserInfo(HttpServletRequest request) {
        ServiceResult<SessionUser> serviceResult = loginService.getLoginedUserInfo(request);
        return serviceResult;
    }

    @RequestMapping(value = "/logout",method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResult<ResponseMsg> logout(HttpServletRequest request) {
        return loginService.logout(request);
    }
}
