package com.iclass.user.component.service.api;

import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 14:26.
 *
 * 验证用户名和教职工号是否存在
 */
public interface ValidateExistService {

    /**
     * 检查用户名是否存在
     * @param username 用户名
     * @return 返回消息实体
     */
    public ServiceResult<ResponseMsg> isExistUsername(String username);

    /**
     * 检查工号是否存在
     * @param usercode 工号
     * @return 返回消息实体
     */
    public ServiceResult<ResponseMsg> isExistUserCode(String usercode);
}
