package com.iclass.user.cache.service.impl;

import com.iclass.user.cache.service.api.CacheService;
import com.iclass.user.component.cache.DataCache;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.vo.SessionUser;
import com.iclass.user.mybatis.dao.UserMapper;
import com.iclass.user.mybatis.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/14 15:04.
 */
@Service
public class CacheServiceImpl implements CacheService {

    private final Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    //缓存器
    private DataCache<SessionUser> dataCache = DataCache.getInstance();

    /**
     * 设置缓存
     * @param sessionid sessionid
     * @param user 用户信息,这里获取的user信息并不全
     *             但是userCode是不许的
     */
    @Override
    public ServiceResult<SessionUser> setCache(String sessionid, User user) {
        ServiceResult<SessionUser> serviceResult = new ServiceResult<>();
        SessionUser sessionUser = new SessionUser();
        if(user != null && user.getUsercode() != null && !user.getUsercode().equals("")) {
            user = userMapper.findUserByUsercode(user.getUsercode());
            sessionUser.setUser(user);
            logger.info("设置缓存时,从数据库中获取的用户信息:" + sessionUser);
            String reuslt = dataCache.setCache(sessionid, sessionUser);
            if(reuslt.equals("200")) {
                logger.info("UserRequestCacheServiceImpl.setCache 缓存设置成功: " + sessionUser);
                serviceResult.setSuccess(true);
                serviceResult.setData(sessionUser);
                serviceResult.setMessage("UserRequestCacheServiceImpl.setCache 缓存设置成功: " + sessionUser);
            } else if(reuslt.equals("404")){
                logger.error("UserRequestCacheServiceImpl.setCache 缓存设置失败: " + sessionUser);
                serviceResult.setMessage("UserRequestCacheServiceImpl.setCache 缓存设置失败: " + sessionUser);
            }
        } else {
            logger.error("设置缓存失败,用户的usercoer不能为空");
            serviceResult.setMessage("设置缓存失败,用户的usercoer不能为空");
        }
        return serviceResult;
    }

    /**
     * 获取缓存数据
     * @param sessionid dataCache中的key:sessionid
     * @return RequestSessionCache
     */
    @Override
    public ServiceResult<SessionUser> getCache(String sessionid) {
        ServiceResult<SessionUser> serviceResult = new ServiceResult<>();
        SessionUser sessionUser = dataCache.getCacheData(sessionid);
        if (serviceResult != null) {
            serviceResult.setData(sessionUser);
            serviceResult.setSuccess(true);
        } else {
            logger.warn("从缓存中获取不到用户信息,sessionId为: " + sessionid);
            serviceResult.setMessage("从缓存中获取不到用户信息,sessionId为: " + sessionid);
        }
        return serviceResult;
    }
}
