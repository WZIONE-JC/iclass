package com.iclass.user.service.api;

import com.iclass.user.UserMsg.UserMsg;
import com.iclass.user.mybatis.model.User;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/12/2017 12:17 PM.
 */
public interface SignUpService {

    public String getMD5Password(String password);

    public UserMsg signup(User user);
}
