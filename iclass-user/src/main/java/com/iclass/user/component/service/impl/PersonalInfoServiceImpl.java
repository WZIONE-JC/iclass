package com.iclass.user.component.service.impl;

import com.iclass.mybatis.dao.UserMapper;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.service.api.PersonalInfoService;
import com.iclass.mybatis.dto.SessionUser;
import com.iclass.mybatis.po.User;
import com.iclass.user.component.utils.CheckDataTables;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
     * 通过sessionID来获取session中存放的已登录用户的信息
     * @param session 获取sessionId
     * @return
     */
    @Override
    public ServiceResult<List<SessionUser>> getPersonalInfoBySession(DataTablesRequestEntity requestEntity, HttpSession session) {
        ServiceResult<List<SessionUser>> serviceResult = new ServiceResult<>();
        SessionUser sessionUser = (SessionUser) session.getAttribute(session.getId());
        if (sessionUser != null) {
            //为了保证数据都是最新的,所以需要从数据库中去获取（通过usercode）
            String usercode = sessionUser.getUser().getUsercode();
            requestEntity = CheckDataTables.check(requestEntity);
            Integer draw = requestEntity.getDraw();
            User user;
            if(StringUtils.isNotBlank(usercode)) {
                user = userMapper.findByUsercode(usercode);
                if(user != null) {
                    serviceResult.setSuccess(true);
                    serviceResult.setDraw(draw);
                    serviceResult.setRecordsFiltered(1);
                    serviceResult.setRecordsTotal(1);
                    List<SessionUser> sessionUsers = new ArrayList<>();
                    sessionUsers.add(new SessionUser(user));
                    serviceResult.setData(sessionUsers);
                    logger.info("通过usercode:"+usercode+",获取到的信息:" + user);
                } else {
                    logger.info("没有找到usercode:" + usercode +",对应的用户信息");
                    serviceResult.setMessage("没有找到usercode:" + usercode +",对应的用户信息");
                }
            } else {
                logger.error("根据session获取用户信息时,usercode不能为空");
                serviceResult.setMessage("根据session获取用户信息时,usercode不能为空");
            }
        } else {
            logger.error("从session中通过sessionId获取用户信息出错,session已过期,或用户未登录");
            serviceResult.setMessage("用户未登录");
        }
        return serviceResult;
    }

}
