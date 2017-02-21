package com.iclass.user.component.service.api;

import com.iclass.user.cache.entity.SessionUser;
import com.iclass.user.mybatis.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/13 15:10.
 *
 * 个人信息维护  服务
 */
public interface PersonalInfoService {

    public SessionUser getPersonalInfoByUsercode(String usercode);

    public SessionUser getPersonalInfoBySession(HttpServletRequest request);

    public SessionUser updatePersonalInfo(User user);

    public SessionUser updateUserPassword(String usercode, String oldPassword, String newPassword);
}
