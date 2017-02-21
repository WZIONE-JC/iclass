package com.iclass.user.cache.service.impl;

import com.iclass.user.cache.entity.DataCache;
import com.iclass.user.cache.entity.RequestSessionCache;
import com.iclass.user.cache.entity.SessionUser;
import com.iclass.user.cache.service.api.RequestSessionCacheService;
import com.iclass.user.mybatis.dao.UserMapper;
import com.iclass.user.mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/14 15:04.
 */
@Service
public class RequestSessionCacheServiceImpl implements RequestSessionCacheService{

    @Autowired
    private UserMapper userMapper;

    //缓存器
    private DataCache dataCache = DataCache.getInstance();

    /**
     * 设置缓存
     * @param sessionid sessionid
     * @param user 用户信息
     */
    @Override
    public void setCache(String sessionid, User user, String requesturl) {
        RequestSessionCache requestSessionCache = null;
        if(requesturl == null) {
            requesturl = "";
        }
        SessionUser sessionUser = new SessionUser();
        if(user.getUsercode() != null || user.getUsercode() != "") {
            user = userMapper.findUserByUsercode(user.getUsercode());
            sessionUser.setUser(user);
            System.out.println("UserRequestCacheServiceImpl.setCache: " + sessionUser);
            requestSessionCache = new RequestSessionCache(sessionUser, requesturl);
        }
        String reuslt = dataCache.setCache(sessionid, requestSessionCache);
        if(reuslt.equals("200")) {
           System.out.println("UserRequestCacheServiceImpl.setCache 缓存设置成功: " + requestSessionCache);
        } else if(reuslt.equals("404")){
           System.out.println("UserRequestCacheServiceImpl.setCache 缓存设置失败: " + requestSessionCache);
        }
    }

    /**
     * 获取缓存数据
     * @param sessionid dataCache中的key:sessionid
     * @return RequestSessionCache
     */
    @Override
    public RequestSessionCache getCache(String sessionid) {
        return dataCache.getCacheData(sessionid);
    }
}
