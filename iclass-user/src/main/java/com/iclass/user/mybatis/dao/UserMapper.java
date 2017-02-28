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

    User findByUsername(String username);

    User findByUsercode(String usercode);

    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password, @Param("userrole") String userrole);

    User findByUsercodeAndPassword(@Param("usercode") String usercode, @Param("password") String password, @Param("userrole") String userrole);

    User findUserByUsercode(@Param("usercode") String usercode);

    int updatePasswordByUserIdAndOldPassword(@Param("userid") String userid, @Param("oldpassword") String oldpassword, @Param("newpassword") String newPassword);
}