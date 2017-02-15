package com.iclass.cache.service.impl;

import com.iclass.cache.service.api.UserRequestCacheService;
import com.iclass.cache.dto.DataCache;
import com.iclass.cache.dto.UserRequestCache;
import com.iclass.user.mybatis.dao.UserMapper;
import com.iclass.user.mybatis.model.User;
import com.iclass.user.utils.UserInfoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/14 15:04.
 */
@Service
public class UserRequestCacheServiceImpl implements UserRequestCacheService{

    @Autowired
    private UserMapper userMapper;

    //缓存器
    private DataCache dataCache = DataCache.getInstance();

    /**
     * 设置缓存
     * @param sessionid sessionid
     * @param usercode usercode 和 sessionId 是必须的参数
     */
    @Override
    public void setCache(String sessionid, String usercode, String requesturl) {
        UserRequestCache userRequestCache = null;
        if(requesturl == null) {
            requesturl = "";
        }
        System.out.print("UserRequestCacheServiceImpl.setCache:");
        System.out.println("sessionid = [" + sessionid + "], usercode = [" + usercode + "], requesturl = [" + requesturl + "]");
        User user = userMapper.findUserByUsercode(usercode);
        user = UserInfoHandler.userpasswordHandler(user);
        System.out.println("UserRequestCacheServiceImpl.setCache: "+user);
        userRequestCache = new UserRequestCache(user, requesturl);
        String reuslt = dataCache.setCache(sessionid, userRequestCache);
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
