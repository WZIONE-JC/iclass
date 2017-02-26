package com.iclass.user.cache.controller;

import com.iclass.user.component.vo.RequestSessionCache;
import com.iclass.user.cache.service.impl.RequestSessionCacheServiceImpl;
import com.iclass.user.mybatis.model.User;
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
 *
 * 将User和requesturl 封装到UserRequestCache 中
 */
@RestController
@RequestMapping("/cache")
public class RequestSessionCacheController {

    @Autowired
    private RequestSessionCacheServiceImpl requestSessionCacheService;

    /**
     * 将下面的参数存入缓存器中
     * @param user 用户
     * @param request 获取session
     * @param requesturl 请求地址
     */
    @RequestMapping("/setCache")
    public void setCache(HttpServletRequest request,
                         User user,
                         @RequestParam(required = false) String requesturl) {
        HttpSession session = request.getSession();
        String sessionid = session.getId();
        System.out.print("UserRequestCacheController.setCache");
        System.out.println("request = [" + request + "], user = [" + user + "], requesturl = [" + requesturl + "]");
        requestSessionCacheService.setCache(sessionid, user, requesturl);
}

    /**
     * 获取用户缓存数据
     * @param request 获取session
     * @return RequestSessionCache , jsonp的方式
     */
    @RequestMapping("/getCache")
    public RequestSessionCache getCache(HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println("UserRequestCacheController.getCache: sessionid="+session.getId());
        return requestSessionCacheService.getCache(session.getId());
    }
}
