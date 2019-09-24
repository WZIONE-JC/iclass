package com.iclass.mybatis.dao;

import com.iclass.mybatis.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    /**
     * 根据usercode 来选择性的更新数据
     * 为空表示不更新
     *
     * @param record user实体
     * @return 成功返回1 , 否则返回 -1
     */
    int updateByUsercodeSelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据Username去查询用户信息
     * @param username 用户名
     * @return User
     */
    User findByUsername(String username);

    User findByUsercode(String usercode);

    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password, @Param("userrole") String userrole);

    User findByUsercodeAndPassword(@Param("usercode") String usercode, @Param("password") String password, @Param("userrole") String userrole);

    User findUserByUsercode(@Param("usercode") String usercode);

    int updatePasswordByUserCodeAndOldPassword(@Param("usercode") String usercode, @Param("oldpassword") String oldpassword, @Param("newpassword") String newPassword);

    /**
     * 从数据库获取所有用户信息
     *
     * @param start  从多少条开始
     * @param length 长度是多少
     * @return 返回User集合
     */
    List<User> findAll(@Param("start") Integer start, @Param("length") Integer length);

    /**
     * 查询所有的记录
     *
     * @return 所有的记录数
     */
    Integer findCount();
}