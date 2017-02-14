package com.iclass.cache.service.api;

import com.iclass.cache.userrequest.UserRequestCache;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/14 14:59.
 */
public interface UserRequestCacheService {

    public void setCache(UserRequestCache cache);

    public UserRequestCache getCache(String key);
}
