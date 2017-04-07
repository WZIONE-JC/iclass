package com.iclass.mybatis.dao;

import com.iclass.mybatis.po.ClassStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassStudentMapper {
    int deleteByPrimaryKey(Integer classstudentid);

    int insert(ClassStudent record);

    int insertSelective(ClassStudent record);

    ClassStudent selectByPrimaryKey(Integer classstudentid);

    int updateByPrimaryKeySelective(ClassStudent record);

    int updateByPrimaryKey(ClassStudent record);

    List<ClassStudent> selectByClassID(@Param("classID") Integer classid);
}