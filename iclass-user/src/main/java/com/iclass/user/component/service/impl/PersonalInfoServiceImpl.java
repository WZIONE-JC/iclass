package com.iclass.user.component.service.impl;

import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.md5.MD5;
import com.iclass.user.component.msg.Msg;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.service.api.PersonalInfoService;
import com.iclass.user.component.vo.SessionUser;
import com.iclass.user.mybatis.dao.UserMapper;
import com.iclass.user.mybatis.model.User;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/13 15:16.
 */
@Service("PersonalInfoService")
public class PersonalInfoServiceImpl implements PersonalInfoService {

    private final Logger logger = LoggerFactory.getLogger(PersonalInfoServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    @Override
    public ServiceResult<SessionUser> getPersonalInfoByUsercode(String usercode) {
        ServiceResult<SessionUser> serviceResult = new ServiceResult<>();
        SessionUser sessionUser = new SessionUser();
        if (StringUtils.isNotBlank(usercode)) {
            User user = userMapper.findUserByUsercode(usercode);
            if(user != null) {
                sessionUser.setUser(user);
                serviceResult.setSuccess(true);
                serviceResult.setData(sessionUser);
                logger.info("通过usercode:"+usercode+",获取到的信息:" + user);
            } else {
                logger.info("没有找到usercode:" + usercode +",对应的用户信息");
                serviceResult.setMessage("没有找到usercode:" + usercode +",对应的用户信息");
            }
        } else {
            serviceResult.setMessage("使用工号获取用户信息时,工号不可以为空");
            logger.error("使用工号获取用户信息时,工号不可以为空");
        }
        return serviceResult;
    }

    /**
     * 通过session来获取sessionID
     * 通过sessionID来获取session中存放的已登录用户的信息,格式如下
     * @param session 获取sessionId
     * @return
     */
    @Override
    public ServiceResult<SessionUser> getPersonalInfoBySession(HttpSession session) {
        ServiceResult<SessionUser> serviceResult = new ServiceResult<>();
        SessionUser sessionUser = (SessionUser) session.getAttribute(session.getId());
        if (sessionUser != null) {
            //为了保证数据都是最新的,所以需要从数据库中去获取（通过usercode）
            String usercode = sessionUser.getUser().getUsercode();
            User user = userMapper.findByUsercode(usercode);
            serviceResult.setSuccess(true);
            serviceResult.setData(new SessionUser(user));
        } else {
            logger.error("从session中通过sessionId获取用户信息出错,session已过期,或用户未登录");
            serviceResult.setMessage("用户未登录");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<ResponseMsg> updatePersonalInfo(User user) {
        logger.info("修改的用户信息:"+user);
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();

        if(user != null) {
            if(StringUtils.isNotBlank(user.getUserid()+"")) {
                userMapper.updateByPrimaryKeySelective(user);
                logger.info("修改用户信息成功");
                serviceResult.setSuccess(true);
                serviceResult.setData(new ResponseMsg(Msg.UPDATE_USER_SUCCESS));
            } else {
                logger.error("修改用户信息失败,userid参数不能为空");
                serviceResult.setMessage("修改用户信息失败,userid参数不能为空");
            }
        } else {
            logger.error("修改用户信息失败,用户信息不能为空");
            serviceResult.setMessage("修改用户信息失败,用户信息不能为空");
            serviceResult.setData(new ResponseMsg(Msg.UPDATE_USER_FAILED));
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<ResponseMsg> updateUserPassword(String userid, String oldPassword, String newPassword) {
       logger.info("userid = [" + userid + "], oldPassword = [" + oldPassword + "], newPassword = [" + newPassword + "]");
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        if(StringUtils.isNotBlank(userid)) {
            if(StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(newPassword)) {
                if(oldPassword.equals(newPassword)) {
                    logger.warn("不能和原始密码相同");
                    serviceResult.setMessage("不能和原始密码相同");
                } else {
                    String oldpwd = MD5.getPwd(oldPassword);
                    String newPwd = MD5.getPwd(newPassword);
                    int result = userMapper.updatePasswordByUserIdAndOldPassword(userid, oldpwd, newPwd);
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
