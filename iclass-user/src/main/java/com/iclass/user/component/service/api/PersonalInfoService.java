package com.iclass.user.component.service.api;

import com.iclass.user.component.vo.SessionUser;
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

    /**
     * 根据工号获取用户信息
     * @param usercode 工号
     * @return SessionUser
     */
    public SessionUser getPersonalInfoByUsercode(String usercode);

    /**
     * 根据sessionId 获取用户信息
     * @param request 获取session
     * @return SessionUser
     */
    public SessionUser getPersonalInfoBySession(HttpServletRequest request);

    /**
     * 修改用户信息
     * @param user user参数
     * @return SessionUser
     */
    public SessionUser updatePersonalInfo(User user);

    /**
     * 修改用户密码
     * @param usercode 工号
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return SessionUser
     */
    public SessionUser updateUserPassword(String usercode, String oldPassword, String newPassword);
}
