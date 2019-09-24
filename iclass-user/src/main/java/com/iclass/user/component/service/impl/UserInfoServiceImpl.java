package com.iclass.user.component.service.impl;

import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.md5.MD5;
import com.iclass.user.component.msg.Msg;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.service.api.UserInfoService;
import com.iclass.mybatis.dto.SessionUser;
import com.iclass.mybatis.dao.UserMapper;
import com.iclass.mybatis.po.User;
import com.iclass.user.component.utils.CheckDataTables;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/5/2017 4:29 PM.
 */
@Service("UserInfoService")
public class UserInfoServiceImpl implements UserInfoService{

    private final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取所有用户信息
     * @return
     */
    @Override
    public ServiceResult<List<SessionUser>> getAll(DataTablesRequestEntity requestEntity) {
        logger.info(requestEntity.toString());
        ServiceResult<List<SessionUser>> serviceResult = new ServiceResult<>();
        List<SessionUser> sessionUsers = new ArrayList<>();
        requestEntity = CheckDataTables.check(requestEntity);
        Integer start = requestEntity.getStart();

        Integer length = requestEntity.getLength();

        Integer draw = requestEntity.getDraw();
        List<User> users = userMapper.findAll(start, length);
        for(User user : users) {
            sessionUsers.add(new SessionUser(user));
        }
        Integer total = userMapper.findCount();
        serviceResult.setDraw(draw);
        serviceResult.setData(sessionUsers);
        serviceResult.setRecordsTotal(total);
        serviceResult.setRecordsFiltered(total);
        serviceResult.setSuccess(true);
        return serviceResult;
    }

    /**
     * 更新用户信息
     * @param user user参数
     * @return 返回响应信息
     */
    @Override
    public ServiceResult<ResponseMsg> updatePersonalInfo(User user) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();

        if(user != null) {
            if(StringUtils.isNotBlank(user.getUsercode())) {
                userMapper.updateByUsercodeSelective(user);
                serviceResult.setSuccess(true);
                serviceResult.setData(new ResponseMsg(Msg.UPDATE_USER_SUCCESS));
            } else {
                serviceResult.setMessage("修改用户信息失败,usercode参数不能为空");
            }
        } else {
            serviceResult.setMessage("修改用户信息失败,用户信息不能为空");
            serviceResult.setData(new ResponseMsg(Msg.UPDATE_USER_FAILED));
        }
        return serviceResult;
    }

    /**
     * 修改密码
     * @param usercode    工号
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     */
    @Override
    public ServiceResult<ResponseMsg> updateUserPassword(String usercode, String oldPassword, String newPassword) {
        logger.info("usercode = [" + usercode + "], oldPassword = [" + oldPassword + "], newPassword = [" + newPassword + "]");
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        if(StringUtils.isNotBlank(usercode)) {
            if(StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(newPassword)) {
                if(oldPassword.equals(newPassword)) {
                    serviceResult.setMessage("不能和原始密码相同");
                } else {
                    String oldpwd = MD5.getPwd(oldPassword);
                    String newPwd = MD5.getPwd(newPassword);
                    int result = userMapper.updatePasswordByUserCodeAndOldPassword(usercode, oldpwd, newPwd);
                    if(result == 1) {
                        serviceResult.setSuccess(true);
                        serviceResult.setData(new ResponseMsg(Msg.UPDATE_PASSWORD_SUCCESS));
                    } else {
                        serviceResult.setMessage("原始密码不正确");
                    }
                }
            } else {
                serviceResult.setMessage("修改密码失败,原始密码和新密码不能为空");
            }
        } else {
            serviceResult.setMessage("修改密码失败,userid不能为空");
        }
        return serviceResult;
    }

    /**
     * 方法保留
     * @param usercode 用户工号
     * @param useremail
     * @return
     */
    @Override
    public ServiceResult<ResponseMsg> sendMail(String usercode, String useremail) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        if(StringUtils.isNotBlank(usercode)) {

        } else {
            serviceResult.setMessage("工号不能为空");
        }
        return null;
    }
}
