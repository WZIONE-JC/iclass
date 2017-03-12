package com.iclass.mybatis.dao;

import com.iclass.mybatis.po.Iclassfile;

public interface IclassfileMapper {
    int deleteByPrimaryKey(Integer fileid);

    int insert(Iclassfile record);

    int insertSelective(Iclassfile record);

    Iclassfile selectByPrimaryKey(Integer fileid);

    int updateByPrimaryKeySelective(Iclassfile record);

    int updateByPrimaryKey(Iclassfile record);
}