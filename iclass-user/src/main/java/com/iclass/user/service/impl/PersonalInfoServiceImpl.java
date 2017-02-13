package com.iclass.user.service.impl;

import com.iclass.user.mybatis.dao.UserMapper;
import com.iclass.user.mybatis.model.User;
import com.iclass.user.service.api.PersonalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public User getPersonalInfo(String usercode) {
        System.out.println("PersonalInfoServiceImpl.getPersonalInfo:"+usercode);
        User user = userMapper.findUserByUsercode(usercode);
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
