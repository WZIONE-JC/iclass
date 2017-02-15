package com.iclass.cache.service.api;

import com.iclass.cache.dto.UserRequestCache;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/14 14:59.
 */
public interface UserRequestCacheService {

    public void setCache(String sessionid, String usercode, String requesturl);

    public UserRequestCache getCache(String key);
}
