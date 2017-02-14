package com.iclass.cache.service.impl;

import com.iclass.cache.service.api.UserRequestCacheService;
import com.iclass.cache.userrequest.DataCache;
import com.iclass.cache.userrequest.UserRequestCache;
import org.springframework.stereotype.Service;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/14 15:04.
 */
@Service
public class UserRequestCacheServiceImpl implements UserRequestCacheService{

    //缓存器
    private DataCache dataCache = DataCache.getInstance();

    /**
     * 设置缓存
     * @param userRequestCache usercode 和 sessionId 是必须的参数
     */
    @Override
    public void setCache(UserRequestCache userRequestCache) {
        System.out.println("UserRequestCacheServiceImpl.setCache: "+userRequestCache);
        String reuslt = dataCache.setCache(userRequestCache);
        if(reuslt.equals("200")) {
           System.out.println("UserRequestCacheServiceImpl.setCache 缓存设置成功: " + userRequestCache);
        } else if(reuslt.equals("404")){
           System.out.println("UserRequestCacheServiceImpl.setCache 缓存设置失败: " + userRequestCache);
        }
    }

    /**
     * 获取缓存数据
     * @param sessionid dataCache中的key:sessionid
     * @return UserRequestCache
     */
    @Override
    public UserRequestCache getCache(String sessionid) {
        return dataCache.getCacheData(sessionid);
    }
}
