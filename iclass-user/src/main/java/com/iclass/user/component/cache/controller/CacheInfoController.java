package com.iclass.user.component.cache.controller;

import com.iclass.mybatis.dto.SessionUser;
import com.iclass.mybatis.po.User;
import com.iclass.user.component.cache.service.api.CacheInfoService;
import com.iclass.user.component.entity.ServiceResult;
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
 * Created by yang.tang on 2017/2/14 15:07.
 * <p>
 * 将User和requesturl 封装到UserRequestCache 中
 */
@RestController
@RequestMapping("/cache")
public class CacheInfoController {

    private final Logger logger = LoggerFactory.getLogger(CacheInfoController.class);

    @Autowired
    private CacheInfoService cacheInfoService;

    /**
     * 将下面的参数存入缓存器中
     *
     * @param user    用户
     * @param request 获取session
     */
    @RequestMapping(value = "/setCache", method = {RequestMethod.POST, RequestMethod.GET})
    public ServiceResult<SessionUser> setCache(HttpServletRequest request,
                                               User user) {
        HttpSession session = request.getSession();
        String sessionid = session.getId();
        logger.info("设置缓存时,从客户端获取的参数:user = [" + user + "]");
        ServiceResult<SessionUser> serviceResult = cacheInfoService.setCache(sessionid, user);
        return serviceResult;
    }

    /**
     * 获取用户缓存数据
     *
     * @param request 获取session
     * @return SessionUser , jsonp的方式
     */
    @RequestMapping(value = "/getCache", method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResult<SessionUser> getCache(HttpServletRequest request) {
        HttpSession session = request.getSession();
        logger.info("UserRequestCacheController.getCache: sessionid=" + session.getId());
        return cacheInfoService.getCache(session.getId());
    }
}
