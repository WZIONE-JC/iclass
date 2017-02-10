package com.iclass.user.service.api;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 14:26.
 *
 * 验证用户名和教职工号是否存在
 */
public interface ValidateExistService {

    public Boolean isExistUsername(String username);

    public Boolean isExistUserCode(String usercode);
}
