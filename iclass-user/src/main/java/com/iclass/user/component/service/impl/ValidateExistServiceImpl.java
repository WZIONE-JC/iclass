package com.iclass.user.component.service.impl;

import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.Msg;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.service.api.ValidateExistService;
import com.iclass.mybatis.dao.UserMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 14:29.
 */
@Service("ValidateExistService")
public class ValidateExistServiceImpl implements ValidateExistService {

    private final Logger logger = LoggerFactory.getLogger(ValidateExistServiceImpl.class);
    /**
     * 用户mapper
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 描述：根据username 来查询此username是否在数据库中是否已经存在
     * 结果：
     *      存在：ture
     *      不存在：false
     * @param username 注册时,用户名
     * @return 返回处理消息
     */
    @Override
    public ServiceResult<ResponseMsg> isExistUsername(String username) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        ResponseMsg responseMsg = new ResponseMsg();
        Boolean result = false;
        if(StringUtils.isNotBlank(username)) {
            result = userMapper.findByUsername(username) != null;
        } else {
            serviceResult.setMessage("用户名不能为空");
            return serviceResult;
        }
        if(result) {
            responseMsg.setMsg(Msg.USERNAME_EXISTED);
        } else {
            serviceResult.setSuccess(true);
            responseMsg.setMsg(Msg.USERNAME_CAN_USE);
        }
        serviceResult.setData(responseMsg);
        return serviceResult;
    }

    /**
     * 描述：与上面的方法一致的
     * 结果：
     *      存在：true
     *      不存在：false
     * @param usercode 注册时提供的工号
     * @return 返回处理消息
     */
    @Override
    public ServiceResult<ResponseMsg> isExistUserCode(String usercode) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        ResponseMsg responseMsg = new ResponseMsg();
        Boolean result = false;
        if(StringUtils.isNotBlank(usercode)) {
            result = userMapper.findByUsercode(usercode) != null;
        } else {
            serviceResult.setMessage("工号不能为空");
            return serviceResult;
        }if(result) {
            responseMsg.setMsg(Msg.USERCODE_EXISTED);
        } else {
            responseMsg.setMsg(Msg.USERCODE_CAN_USE);
            serviceResult.setSuccess(true);
        }
        serviceResult.setData(responseMsg);
        return serviceResult;
    }
}
