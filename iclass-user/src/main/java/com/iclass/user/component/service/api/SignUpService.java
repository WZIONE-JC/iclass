package com.iclass.user.component.service.api;

import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.mybatis.model.User;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/12/2017 12:17 PM.
 */
public interface SignUpService {

    public String getMD5Password(String password);

    public ResponseMsg signup(User user);
}
