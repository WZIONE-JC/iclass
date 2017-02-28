package com.iclass.user.component.service.api;

import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.vo.SessionUser;

import javax.servlet.http.HttpServletRequest;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 19:57.
 */
public interface LoginService {

    /**
     * 对密码进行MD5 加密
     * @param password 原始密码
     * @return 加密后的密码
     */
    public String getMD5Password(String password);

    /**
     * 登录请求
     * @param request 获取session
     * @param username 用户名
     * @param pwd 密码
     * @param rolename 角色
     * @param code 验证码
     * @return 返回实体消息
     */
    public ServiceResult<ResponseMsg> login(HttpServletRequest request, String username, String pwd, String rolename, String code);

    /**
     * 验证验证码是否正确
     * @param request 获取session
     * @param code 验证码
     * @return 返回验证码是否正确
     */
    public boolean validateVerificationCode(HttpServletRequest request, String code);

    /**
     * 获取登录信息，从session中获取用户登录信息
     * @param request 获取session
     * @return 返回sessionUser
     */
    public ServiceResult<SessionUser> getLoginedUserInfo(HttpServletRequest request);
}
