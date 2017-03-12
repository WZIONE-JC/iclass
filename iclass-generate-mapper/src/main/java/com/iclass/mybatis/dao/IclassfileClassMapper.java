package com.iclass.mybatis.dao;

import com.iclass.mybatis.po.IclassfileClass;

public interface IclassfileClassMapper {
    int deleteByPrimaryKey(Integer fileclassid);

    int insert(IclassfileClass record);

    int insertSelective(IclassfileClass record);

    IclassfileClass selectByPrimaryKey(Integer fileclassid);

    int updateByPrimaryKeySelective(IclassfileClass record);

    int updateByPrimaryKey(IclassfileClass record);
}