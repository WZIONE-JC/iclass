package com.iclass.user.service.impl;

import com.iclass.user.mybatis.dao.UserMapper;
import com.iclass.user.service.api.ValidateExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 14:29.
 */
@Service
public class ValidateExistImpl implements ValidateExist {

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
    public Boolean isExistUsername(String username) {
        return userMapper.findByUsername(username) != null;
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
    public Boolean isExistUserCode(String usercode) {
        return userMapper.findByUsercode(usercode) != null;
    }
}
