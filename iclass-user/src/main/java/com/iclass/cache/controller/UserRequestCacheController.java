package com.iclass.cache.controller;

import com.iclass.cache.service.impl.UserRequestCacheServiceImpl;
import com.iclass.cache.userrequest.UserRequestCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/14 15:07.
 */
@RestController
@RequestMapping("/cache")
public class UserRequestCacheController {

    @Autowired
    private UserRequestCacheServiceImpl userRequestCacheService;

    /**
     * 将下面的参数存入缓存器中
     * @param usercode 用户工号
     * @param username 用户名
     * @param userrole 用户角色
     * @param request 获取session
     * @param requesturl 请求地址
     */
    @RequestMapping("/setCache")
    public void setCache(String usercode,
                            @RequestParam(required = false) String username,
                            @RequestParam(required = false) String userrole,
                            HttpServletRequest request,
                            @RequestParam(required = false) String requesturl) {
        HttpSession session = request.getSession();
        String sessionid = session.getId();
        UserRequestCache userRequestCache = new UserRequestCache(usercode, username, userrole, sessionid, requesturl);
        System.out.println("UserRequestCacheController.setCache: "+userRequestCache);
        System.out.println("usercode = [" + usercode + "], username = [" + username + "], userrole = [" + userrole + "], request = [" + request + "], requesturl = [" + requesturl + "]");
        userRequestCacheService.setCache(userRequestCache);
}

    /**
     * 获取用户缓存数据
     * @param request 获取session
     * @return UserRequestCache , jsonp的方式
     */
    @RequestMapping("/getCache")
    public UserRequestCache getCache(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return userRequestCacheService.getCache(session.getId());
    }
}
