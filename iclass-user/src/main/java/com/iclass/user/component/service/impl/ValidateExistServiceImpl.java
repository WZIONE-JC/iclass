package com.iclass.user.component.service.impl;

import com.iclass.user.component.exception.UserException;
import com.iclass.user.component.msg.CodeMsg;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.service.api.ValidateExistService;
import com.iclass.user.mybatis.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 14:29.
 */
@Service("ValidateExistService")
public class ValidateExistServiceImpl implements ValidateExistService {

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
     * @param username
     * @return
     */
    @Override
    public ResponseMsg isExistUsername(String username) {
        ResponseMsg responseMsg = new ResponseMsg();
        Boolean result = false;
        if(username != null) {
            result = userMapper.findByUsername(username) != null;
        } else {
            throw new UserException("4001", "用户名不能为空");
        }
        if(result) {
            responseMsg.setCodeMsg(CodeMsg.USERNAME_EXISTED);
        } else {
            responseMsg.setCodeMsg(CodeMsg.USERNAME_CAN_USE);
        }
        return responseMsg;
    }

    /**
     * 描述：与上面的方法一致的
     * 结果：
     *      存在：true
     *      不存在：false
     * @param usercode
     * @return
     */
    @Override
    public ResponseMsg isExistUserCode(String usercode) {
        ResponseMsg responseMsg = new ResponseMsg();
        Boolean result = false;
        if(usercode != null) {
            result = userMapper.findByUsercode(usercode) != null;
        } else {
            throw new UserException("4002", "工号不能为空");
        }if(result) {
            responseMsg.setCodeMsg(CodeMsg.USERCODE_EXISTED);
        } else {
            responseMsg.setCodeMsg(CodeMsg.USERCODE_CAN_USE);
        }
        return responseMsg;
    }
}
