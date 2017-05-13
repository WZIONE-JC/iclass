package com.iclass.mybatis.dao;

import com.iclass.mybatis.po.ClassCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassCourseMapper {
    int deleteByPrimaryKey(Integer iclasscourseid);

    int insert(ClassCourse record);

    int insertSelective(ClassCourse record);

    ClassCourse selectByPrimaryKey(Integer iclasscourseid);

    int updateByPrimaryKeySelective(ClassCourse record);

    int updateByPrimaryKey(ClassCourse record);

    List<ClassCourse> selectByClassCode(@Param("classcode") String classCode);

    List<ClassCourse> selectByCourseCode(@Param("coursecode") String courseCode);

    ClassCourse selectByClassCodeAndCourseCode(@Param("classcode") String classCode, @Param("coursecode") String courseCode);

    List<ClassCourse> selectUnselectedClassCourse(@Param("studentcode") String studentCode);
}