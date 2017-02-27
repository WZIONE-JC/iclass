package com.iclass.user.cache.service.impl;

import com.iclass.user.cache.service.api.RequestCacheService;
import com.iclass.user.component.cache.DataCache;
import com.iclass.user.component.vo.RequestSessionCache;
import com.iclass.user.component.vo.SessionUser;
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
public class RequestCacheServiceImpl implements RequestCacheService{

    @Autowired
    private UserMapper userMapper;

    //缓存器
    private DataCache<RequestSessionCache> dataCache = DataCache.getInstance();

    /**
     * 设置缓存
     * @param sessionid sessionid
     * @param user 用户信息
     */
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
    public RequestSessionCache getCache(String sessionid) {
        return dataCache.getCacheData(sessionid);
    }
}
