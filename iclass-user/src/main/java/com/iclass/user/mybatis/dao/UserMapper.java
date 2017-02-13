package com.iclass.user.mybatis.dao;

import com.iclass.user.mybatis.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    String findByUsername(String username);

    String findByUsercode(String usercode);

    String findByUsernameAndPassword(@Param("userrole") String userrole, @Param("username") String username, @Param("password") String password);

    String findByUsercodeAndPassword(@Param("userrole") String userrole, @Param("usercode") String usercode, @Param("password") String password);

    User findUserByUsercode(@Param("usercode") String usercode);
}