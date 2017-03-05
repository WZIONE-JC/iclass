package com.iclass.user.mybatis.dao;

import com.iclass.user.mybatis.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByUsercodeSelective(User record);

    int updateByPrimaryKey(User record);

    User findByUsername(String username);

    User findByUsercode(String usercode);

    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password, @Param("userrole") String userrole);

    User findByUsercodeAndPassword(@Param("usercode") String usercode, @Param("password") String password, @Param("userrole") String userrole);

    User findUserByUsercode(@Param("usercode") String usercode);

    int updatePasswordByUserCodeAndOldPassword(@Param("usercode") String usercode, @Param("oldpassword") String oldpassword, @Param("newpassword") String newPassword);

    /**
     * 从数据库获取所有用户信息
     * @param start 从多少条开始
     * @param length 长度是多少
     * @return 返回User集合
     */
    List<User> findAll(@Param("start") Integer start, @Param("length") Integer length);
}