package com.iclass.user.component.cache.service.impl;

import com.iclass.mybatis.dao.UserMapper;
import com.iclass.mybatis.po.User;
import com.iclass.user.component.cache.service.api.CacheInfoService;
import com.iclass.user.component.cache.DataCache;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.mybatis.dto.SessionUser;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/14 15:04.
 */
@Service("cacheInfoService")
public class CacheInfoServiceImpl implements CacheInfoService {

    private final Logger logger = LoggerFactory.getLogger(CacheInfoServiceImpl.class);

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
        if(user != null && StringUtils.isNotBlank(user.getUsercode())) {
            user = userMapper.findUserByUsercode(user.getUsercode());
            sessionUser.setUser(user);
            String reuslt = dataCache.setCache(sessionid, sessionUser);
            if(reuslt.equals("200")) {
                serviceResult.setSuccess(true);
                serviceResult.setData(sessionUser);
                serviceResult.setMessage("UserRequestCacheServiceImpl.setCache 缓存设置成功: " + sessionUser);
            } else if(reuslt.equals("404")){
                serviceResult.setMessage("UserRequestCacheServiceImpl.setCache 缓存设置失败: " + sessionUser);
            }
        } else {
            serviceResult.setMessage("设置缓存失败,用户的usercode不能为空");
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
            serviceResult.setMessage("从缓存中获取不到用户信息,sessionId为: " + sessionid);
        }
        return serviceResult;
    }
}
