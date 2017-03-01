package com.iclass.user.component.service.api;

import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.vo.SessionUser;
import com.iclass.user.mybatis.model.User;

import javax.servlet.http.HttpSession;

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
    public ServiceResult<SessionUser> getPersonalInfoByUsercode(String usercode);

    /**
     * 根据sessionId 获取用户信息
     * @param session 获取sessionId
     * @return SessionUser
     */
    public ServiceResult<SessionUser> getPersonalInfoBySession(HttpSession session);

    /**
     * 修改用户信息
     * @param user user参数
     * @return SessionUser
     */
    public ServiceResult<ResponseMsg> updatePersonalInfo(User user);

    /**
     * 修改用户密码
     *
     * @param usercode    工号
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return SessionUser
     */
    public ServiceResult<ResponseMsg> updateUserPassword(String usercode, String oldPassword, String newPassword);

    /**
     * 发送邮件
     * @param usercode 用户工号
     * @return 返回消息实体
     */
    public ServiceResult<ResponseMsg> sendMail(String usercode, String useremail);
}
