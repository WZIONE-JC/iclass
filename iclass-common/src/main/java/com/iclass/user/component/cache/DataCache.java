package com.iclass.user.component.cache;

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
public class DataCache <T>{

    private static final DataCache dataCache = new DataCache();
    //单例
    //key 是sessionID
    //value 是Cache实例 RequestSessionCache
    private final Map<String, T> cache = new HashMap<>();

    public static DataCache getInstance() {
        return dataCache;
    }

    /**
     * 设置缓存数据
     * @param t 用户cache实例
     */
    public String setCache(String sessionid, T t) {
        try {
            if(t != null) {
                cache.put(sessionid, t);
                return "200";
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("DataCache.setCache: 设置缓存出错, 源数据: " + t);
        }
        return "404";
    }

    /**
     * 获取缓存数据
     * @param sessionid sessionId
     * @return 用户缓存数据
     */
    public T getCacheData(String sessionid) {
        return cache.get(sessionid);
    }
}
