package com.iclass.user.component.service.api;

import com.iclass.mybatis.po.User;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.mybatis.dto.SessionUser;

import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/5/2017 4:24 PM.
 * 用户信息
 */
public interface UserInfoService {

    /**
     * 获取所有用户信息
     *
     * @param requestEntity datables请求参数
     * @return 返回SessionUser的集合
     */
    public ServiceResult<List<SessionUser>> getAll(DataTablesRequestEntity requestEntity);

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
