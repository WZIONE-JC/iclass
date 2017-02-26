package com.iclass.user.component.service.api;

import com.iclass.user.component.msg.ResponseMsg;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 14:26.
 *
 * 验证用户名和教职工号是否存在
 */
public interface ValidateExistService {

    public ResponseMsg isExistUsername(String username);

    public ResponseMsg isExistUserCode(String usercode);
}
