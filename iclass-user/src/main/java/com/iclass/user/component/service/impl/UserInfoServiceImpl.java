package com.iclass.user.component.service.impl;

import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.md5.MD5;
import com.iclass.user.component.msg.Msg;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.service.api.UserInfoService;
import com.iclass.user.component.vo.SessionUser;
import com.iclass.user.mybatis.dao.UserMapper;
import com.iclass.user.mybatis.model.User;
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
        Integer start = requestEntity.getStart();
        if(start < 0) {
            start = 0;
        }
        Integer length = requestEntity.getLength();
        if(length < 1) {
            length = 1;
        }
        Integer draw = requestEntity.getDraw();
        List<User> users = userMapper.findAll(start, length);
        for(User user : users) {
            sessionUsers.add(new SessionUser(user));
        }
        Integer total = userMapper.findCount();
        logger.info("获取到全部用户信息,总数:"+total);
        serviceResult.setDraw(draw);
        serviceResult.setData(sessionUsers);
        serviceResult.setRecordsTotal(total);
        serviceResult.setRecordsFiltered(users.size());
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
        logger.info("修改的用户信息:"+user);
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();

        if(user != null) {
            if(StringUtils.isNotBlank(user.getUsercode())) {
                userMapper.updateByUsercodeSelective(user);
                logger.info("修改用户信息成功");
                serviceResult.setSuccess(true);
                serviceResult.setData(new ResponseMsg(Msg.UPDATE_USER_SUCCESS));
            } else {
                logger.error("修改用户信息失败,usercode参数不能为空");
                serviceResult.setMessage("修改用户信息失败,usercode参数不能为空");
            }
        } else {
            logger.error("修改用户信息失败,用户信息不能为空");
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
                    logger.warn("不能和原始密码相同");
                    serviceResult.setMessage("不能和原始密码相同");
                } else {
                    String oldpwd = MD5.getPwd(oldPassword);
                    String newPwd = MD5.getPwd(newPassword);
                    int result = userMapper.updatePasswordByUserCodeAndOldPassword(usercode, oldpwd, newPwd);
                    if(result == 1) {
                        serviceResult.setSuccess(true);
                        logger.info("修改密码成功");
                        serviceResult.setData(new ResponseMsg(Msg.UPDATE_PASSWORD_SUCCESS));
                    } else {
                        logger.warn("原始密码不正确");
                        serviceResult.setMessage("原始密码不正确");
                    }
                }
            } else {
                logger.error("修改密码失败,原始密码和新密码不能为空");
                serviceResult.setMessage("修改密码失败,原始密码和新密码不能为空");
            }
        } else {
            logger.error("修改密码失败,userid不能为空");
            serviceResult.setMessage("修改密码失败,userid不能为空");
        }
        return serviceResult;
    }

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
