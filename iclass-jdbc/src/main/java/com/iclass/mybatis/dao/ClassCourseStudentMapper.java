package com.iclass.mybatis.dao;

import com.iclass.mybatis.po.ClassCourseStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassCourseStudentMapper {
    int deleteByPrimaryKey(Integer classstudentid);

    int insert(ClassCourseStudent record);

    int insertSelective(ClassCourseStudent record);

    ClassCourseStudent selectByPrimaryKey(Integer classstudentid);

    int updateByPrimaryKeySelective(ClassCourseStudent record);

    int updateByPrimaryKey(ClassCourseStudent record);

    List<ClassCourseStudent> selectByClassCourseId(Integer classcourseid);

    List<ClassCourseStudent> selectByStudentCode(String studentcode);

    List<ClassCourseStudent> selectByNotEqualsStudentCode(String studentcode);

    ClassCourseStudent selectByClassCourseIdAndStudentCode(@Param("classcourseid") Integer classcourseid, @Param("studentcode") String studentcode);
}