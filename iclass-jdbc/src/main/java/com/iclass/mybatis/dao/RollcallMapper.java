package com.iclass.mybatis.dao;

import com.iclass.mybatis.po.Rollcall;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RollcallMapper {
    int deleteByPrimaryKey(Integer rollcallid);

    int insert(Rollcall record);

    int insertSelective(Rollcall record);

    Rollcall selectByPrimaryKey(Integer rollcallid);

    int updateByPrimaryKeySelective(Rollcall record);

    int updateByPrimaryKey(Rollcall record);

    List<Rollcall> selectByTeacherCode(@Param("teachercode") String teacherCode, @Param("start") Integer start, @Param("length") Integer length);

    List<Rollcall> selectByTeacherCodeNoLimit(@Param("teachercode") String teacherCode);

    List<Rollcall> selectByClassCourseId(@Param("classcourseId") Integer classCourseId, Integer attendNumber, Integer attendTime);

    List<Rollcall> selectByClassCourseIdAndStatus(@Param("classcourseid") Integer classCourseId, @Param("rollcallstatus") int status);

    Integer countByClassCourseIdAndStatus(@Param("classcourseid") Integer classCourseId, @Param("rollcallstatus") int status);
}