package com.iclass.mybatis.dao;

import com.iclass.mybatis.po.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);

    Integer countLogRecord();

    List<Log> selectAll(@Param("start") Integer start, @Param("length") Integer length);

}