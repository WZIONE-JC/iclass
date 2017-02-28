package com.iclass.user.component.service.impl;

import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.vo.SessionUser;
import com.iclass.user.component.service.api.PersonalInfoService;
import com.iclass.user.mybatis.dao.UserMapper;
import com.iclass.user.mybatis.model.User;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/13 15:16.
 */
@Service("PersonalInfoService")
public class PersonalInfoServiceImpl implements PersonalInfoService {

    private final Logger logger = LoggerFactory.getLogger(PersonalInfoServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    @Override
    public ServiceResult<SessionUser> getPersonalInfoByUsercode(String usercode) {
        ServiceResult<SessionUser> serviceResult = new ServiceResult<>();
        SessionUser sessionUser = new SessionUser();
        if (usercode != null && !usercode.equals("")) {
            User user = userMapper.findUserByUsercode(usercode);
            if(user != null) {
                sessionUser.setUser(user);
                serviceResult.setSuccess(true);
                serviceResult.setData(sessionUser);
                logger.info("通过usercode:"+usercode+",获取到的信息:" + user);
            } else {
                logger.info("没有找到usercode:" + usercode +",对应的用户信息");
                serviceResult.setMessage("没有找到usercode:" + usercode +",对应的用户信息");
            }
        } else {
            serviceResult.setMessage("使用工号获取用户信息时,工号不可以为空");
            logger.error("使用工号获取用户信息时,工号不可以为空");
        }
        return serviceResult;
    }

    /**
     * 通过session来获取sessionID
     * 通过sessionID来获取session中存放的已登录用户的信息,格式如下
     * @param session 获取sessionId
     * @return
     */
    @Override
    public ServiceResult<SessionUser> getPersonalInfoBySession(HttpSession session) {
        ServiceResult<SessionUser> serviceResult = new ServiceResult<>();
        SessionUser sessionUser = (SessionUser) session.getAttribute(session.getId());
        if (sessionUser != null) {
            logger.info("PersonalInfoServiceImpl.getPersonalInfo: " + sessionUser);
            serviceResult.setSuccess(true);
            serviceResult.setData(sessionUser);
        } else {
            logger.error("从session中通过sessionId获取用户信息出错,session已过期,或用户未登录");
            serviceResult.setMessage("从session中通过sessionId获取用户信息出错,session已过期,或用户未登录");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<SessionUser> updatePersonalInfo(User user) {
        return null;
    }

    @Override
    public ServiceResult<SessionUser> updateUserPassword(String usercode, String oldPassword, String newPassword) {
        return null;
    }
}
