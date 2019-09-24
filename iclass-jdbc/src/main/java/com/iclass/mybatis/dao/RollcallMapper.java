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

    Integer countByTeacherCode(@Param("teachercode") String teacherCode);

    /**
     * 获取点名数据
     * @param classCourseId
     * @param startDay
     * @param endDay
     * @return
     */
    List<Rollcall> selectByClassCourseIdAndDate(@Param("classcourseid") Integer classCourseId, @Param("startday") String startDay, @Param("endday") String endDay);

    List<Rollcall> selectByClassCourseIdAndStudentCode(@Param("classcourseid") Integer classCourseId, @Param("studentcode") String studentCode);
}