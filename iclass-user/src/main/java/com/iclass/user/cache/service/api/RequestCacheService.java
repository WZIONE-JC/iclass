package com.iclass.user.cache.service.api;


import com.iclass.user.component.vo.RequestSessionCache;
import com.iclass.user.mybatis.model.User;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/14 14:59.
 */
public interface RequestCacheService {

    public void setCache(String sessionid, User user, String requesturl);

    public RequestSessionCache getCache(String key);
}
