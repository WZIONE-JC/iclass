package com.iclass.user.component.service.api;

import com.iclass.mybatis.model.User;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/12/2017 12:17 PM.
 */
public interface SignUpService {

    /**
     * 对密码加密
     * @param password 原密码
     * @return 加密的密码
     */
    public String getMD5Password(String password);

    /**
     * 注册
     * @param user 用户信息
     * @return 返回消息实体
     */
    public ServiceResult<ResponseMsg> signup(User user);
}
