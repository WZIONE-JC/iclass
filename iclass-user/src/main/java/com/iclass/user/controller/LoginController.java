package com.iclass.user.controller;

import com.iclass.user.usermsg.UserMsg;
import com.iclass.user.service.impl.LoginServiceImpl;
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
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @RequestMapping("/login")
    public UserMsg login(HttpServletRequest request, String userrole, String username,
                         String password, String code) {
        return loginService.login(request, userrole, username, password, code);
    }

    /**
     * 获取已登录用户的信息
     * @param request 获取session
     * @param callback jsonp的参数
     * @return 返回jsonp 数据，或者null
     */
    @RequestMapping(value = "/getLoginedUserInfo")
    public String getLoginedUserInfo(HttpServletRequest request, String callback) {
        return loginService.getLoginedUserInfo(request, callback);
    }
}
