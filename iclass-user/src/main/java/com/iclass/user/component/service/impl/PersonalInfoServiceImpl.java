package com.iclass.user.component.service.impl;

import com.iclass.user.cache.entity.SessionUser;
import com.iclass.user.component.service.api.PersonalInfoService;
import com.iclass.user.mybatis.dao.UserMapper;
import com.iclass.user.mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/13 15:16.
 */
@Service
public class PersonalInfoServiceImpl implements PersonalInfoService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public SessionUser getPersonalInfoByUsercode(String usercode) {
        SessionUser sessionUser = new SessionUser();
        User user = userMapper.findUserByUsercode(usercode);
        sessionUser.setUser(user);
        System.out.println("PersonalInfoServiceImpl.getPersonalInfoByUsercode: "+user);
        return sessionUser;
    }

    /**
     * 通过session来获取sessionID
     * 通过sessionID来获取session中存放的已登录用户的信息,格式如下
     *  {"username":"唐洋","usercode":"1308030331","userrole":"管理员"}
     * 通过上述json数据，可以得到usercode，再通过usercode去数据库中去查询对应的用户信息
     * 通过jsonp(完整的User信息)返回给client
     * @param request 获取session
     * @return
     */
    @Override
    public SessionUser getPersonalInfoBySession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser)session.getAttribute(session.getId());
        System.out.println("PersonalInfoServiceImpl.getPersonalInfo: " + sessionUser);
        return sessionUser;
    }

    @Override
    public SessionUser updatePersonalInfo(User user) {
        return null;
    }

    @Override
    public SessionUser updateUserPassword(String usercode, String oldPassword, String newPassword) {
        return null;
    }
}
