package com.iclass.mybatis.dao;

import com.iclass.mybatis.po.ClassCourseStudent;

public interface ClassCourseStudentMapper {
    int deleteByPrimaryKey(Integer classstudentid);

    int insert(ClassCourseStudent record);

    int insertSelective(ClassCourseStudent record);

    ClassCourseStudent selectByPrimaryKey(Integer classstudentid);

    int updateByPrimaryKeySelective(ClassCourseStudent record);

    int updateByPrimaryKey(ClassCourseStudent record);
}