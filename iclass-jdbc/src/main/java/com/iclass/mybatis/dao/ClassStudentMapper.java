package com.iclass.mybatis.dao;

import com.iclass.mybatis.po.ClassStudent;

public interface ClassStudentMapper {
    int deleteByPrimaryKey(Integer classstudentid);

    int insert(ClassStudent record);

    int insertSelective(ClassStudent record);

    ClassStudent selectByPrimaryKey(Integer classstudentid);

    int updateByPrimaryKeySelective(ClassStudent record);

    int updateByPrimaryKey(ClassStudent record);
}