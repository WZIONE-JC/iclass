package com.iclass.mybatis.dao;

import com.iclass.mybatis.po.Classcourseattendtime;

import java.util.List;

public interface ClasscourseattendtimeMapper {
    int deleteByPrimaryKey(Integer classcourseattendtimeid);

    int insert(Classcourseattendtime record);

    int insertSelective(Classcourseattendtime record);

    Classcourseattendtime selectByPrimaryKey(Integer classcourseattendtimeid);

    int updateByPrimaryKeySelective(Classcourseattendtime record);

    int updateByPrimaryKey(Classcourseattendtime record);

    List<Classcourseattendtime> selectByClassCourseId(Integer classCourseId);
}