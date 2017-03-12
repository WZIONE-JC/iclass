package com.iclass.mybatis.dao;

import com.iclass.mybatis.po.Classhd;

public interface ClasshdMapper {
    int deleteByPrimaryKey(Integer classhdid);

    int insert(Classhd record);

    int insertSelective(Classhd record);

    Classhd selectByPrimaryKey(Integer classhdid);

    int updateByPrimaryKeySelective(Classhd record);

    int updateByPrimaryKey(Classhd record);
}