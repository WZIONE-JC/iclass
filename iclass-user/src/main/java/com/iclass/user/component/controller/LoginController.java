package com.iclass.user.component.controller;

import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.service.impl.LoginServiceImpl;
import com.iclass.user.component.vo.SessionUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private LoginServiceImpl loginService;

    @RequestMapping("/login")
    public ResponseMsg login(HttpServletRequest request, String userrole, String username,
                             String password, String code) {
        logger.info("request = [" + request + "], userrole = [" + userrole + "], username = [" + username + "], password = [" + password + "], code = [" + code + "]");
        return loginService.login(request, userrole, username, password, code);
    }

    /**
     * 获取已登录用户的信息
     * @param request 获取session
     * @return 返回jsonp 数据，或者null
     */
    @RequestMapping(value = "/getLoginedUserInfo")
    public SessionUser getLoginedUserInfo(HttpServletRequest request) {
        SessionUser sessionUser = loginService.getLoginedUserInfo(request);
        System.out.println("LoginController.getLoginedUserInfo");
        System.out.println(sessionUser);
        return sessionUser;
    }
}
