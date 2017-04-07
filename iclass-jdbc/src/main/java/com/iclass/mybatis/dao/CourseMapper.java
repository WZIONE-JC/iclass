package com.iclass.mybatis.dao;

import com.iclass.mybatis.po.Course;
import org.apache.ibatis.annotations.Param;

public interface CourseMapper {
    int deleteByPrimaryKey(Integer courseid);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer courseid);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    Course selectByCourseCode(@Param("courseCode") String courseCode);

    int updateStatusById(@Param("courseid") Integer courseid, int status);
}