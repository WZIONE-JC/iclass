package com.iclass.user.service.impl;

import com.iclass.user.mybatis.dao.UserMapper;
import com.iclass.user.mybatis.model.User;
import com.iclass.user.service.api.PersonalInfoService;
import com.iclass.user.userdata.UserUtils;
import com.iclass.user.utils.UserInfoHandler;
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
    public User getPersonalInfoByUsercode(String usercode) {
        User user = userMapper.findUserByUsercode(usercode);
        user = UserInfoHandler.userRegisterDateHandler(user);
        user = UserInfoHandler.userRegisterDateHandler(user);
        System.out.println("PersonalInfoServiceImpl.getPersonalInfoByUsercode: "+user);
        return user;
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
    public User getPersonalInfoBySession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String jsonp = (String)session.getAttribute(session.getId());
        String usercode = UserUtils.getUsercodeByJsonpData(jsonp);
        User user = userMapper.findUserByUsercode(usercode);
        //处理掉userRegisterDate数据中最后的 .0 数据
        user = UserInfoHandler.userRegisterDateHandler(user);
        user = UserInfoHandler.userpasswordHandler(user);
        System.out.println("PersonalInfoServiceImpl.getPersonalInfo: " + user);
        System.out.println("PersonalInfoServiceImpl.getPersonalInfo: end");
        return user;
    }

    @Override
    public User updatePersonalInfo(User user) {
        return null;
    }

    @Override
    public User updateUserPassword(String usercode, String oldPassword, String newPassword) {
        return null;
    }
}
