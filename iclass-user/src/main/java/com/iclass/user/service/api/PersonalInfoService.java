package com.iclass.user.service.api;

import com.iclass.user.mybatis.model.User;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/13 15:10.
 *
 * 个人信息维护  服务
 */
public interface PersonalInfoService {

    public User getPersonalInfo(String usercode);

    public User updatePersonalInfo(User user);

    public User updateUserPassword(String usercode, String oldPassword, String newPassword);
}
