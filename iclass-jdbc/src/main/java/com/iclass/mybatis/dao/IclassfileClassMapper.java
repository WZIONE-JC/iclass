package com.iclass.mybatis.dao;

import com.iclass.mybatis.po.IclassfileClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IclassfileClassMapper {
    int deleteByPrimaryKey(Integer fileclassid);

    int insert(IclassfileClass record);

    int insertSelective(IclassfileClass record);

    IclassfileClass selectByPrimaryKey(Integer fileclassid);

    int updateByPrimaryKeySelective(IclassfileClass record);

    int updateByPrimaryKey(IclassfileClass record);

    List<IclassfileClass> selectByClassCourseId(@Param("classcourseid") Integer classCourseId,
                                                @Param("start") Integer strat, @Param("length") Integer length);

    List<IclassfileClass> selectByClassCourseIdNoLimit(@Param("classcourseid") Integer classCourseId);
}