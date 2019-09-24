package com.iclass.mybatis.dao;

import com.iclass.mybatis.po.TeacherCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherCourseMapper {
    int deleteByPrimaryKey(Integer teachercourseid);

    int insert(TeacherCourse record);

    int insertSelective(TeacherCourse record);

    TeacherCourse selectByPrimaryKey(Integer teachercourseid);

    int updateByPrimaryKeySelective(TeacherCourse record);

    int updateByPrimaryKey(TeacherCourse record);

    List<TeacherCourse> selectByTeacherCode(@Param("teachercode") String teacherCode, @Param("start") Integer start, @Param("length") Integer length);

    int countByTeacherCode(@Param("teachercode") String teacherCode);

}