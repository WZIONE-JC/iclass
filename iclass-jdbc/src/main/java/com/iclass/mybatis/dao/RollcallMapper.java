package com.iclass.mybatis.dao;

import com.iclass.mybatis.po.Rollcall;

public interface RollcallMapper {
    int deleteByPrimaryKey(Integer rollcallid);

    int insert(Rollcall record);

    int insertSelective(Rollcall record);

    Rollcall selectByPrimaryKey(Integer rollcallid);

    int updateByPrimaryKeySelective(Rollcall record);

    int updateByPrimaryKey(Rollcall record);
}