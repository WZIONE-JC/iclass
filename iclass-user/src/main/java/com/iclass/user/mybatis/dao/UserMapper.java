package com.iclass.user.mybatis.dao;

import com.iclass.user.mybatis.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    String findByUsername(String username);

    String findByUsercode(String usercode);
}