package com.iclass.cache.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/14 15:34.
 *
 * 使用单例模式来创建DataCache
 *
 */
public class DataCache {

    private static final DataCache dataCache = new DataCache();
    //单例
    //key 是sessionID+页面名称
    //value 是Cache实例
    private static final Map<String, UserRequestCache> cache = new HashMap<String, UserRequestCache>();

    public static DataCache getInstance() {
        return dataCache;
    }

    /**
     * 设置缓存数据
     * @param userRequestCache 用户cache实例
     */
    public String setCache(String sessionid, UserRequestCache userRequestCache) {
        try {
            if(userRequestCache != null) {
                cache.put(sessionid, userRequestCache);
                return "200";
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("DataCache.setCache: 设置缓存出错, 源数据: " + userRequestCache);
        }
        return "404";
    }

    /**
     * 获取缓存数据
     * @param sessionid sessionId
     * @return 用户缓存数据
     */
    public UserRequestCache getCacheData(String sessionid) {
        return cache.get(sessionid);
    }
}
