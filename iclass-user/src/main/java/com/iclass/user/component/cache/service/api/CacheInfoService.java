package com.iclass.user.component.cache.service.api;


import com.iclass.mybatis.model.User;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.vo.SessionUser;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/14 14:59.
 */
public interface CacheInfoService {

    /**
     * 设置用户缓存信息 键是sessionId， value是user
     * @param sessionid sessionId
     * @param user 用户信息
     */
    public ServiceResult<SessionUser> setCache(String sessionid, User user);

    /**
     * 通过sessionId获取用户信息
     * @param sessionid sessionId
     * @return 返回User
     */
    public ServiceResult<SessionUser> getCache(String sessionid);
}
