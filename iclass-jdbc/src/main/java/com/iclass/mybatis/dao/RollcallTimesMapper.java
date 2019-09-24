package com.iclass.mybatis.dao;

import com.iclass.mybatis.po.RollcallTimes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RollcallTimesMapper {
    int deleteByPrimaryKey(Integer rollcalltimesid);

    int insert(RollcallTimes record);

    int insertSelective(RollcallTimes record);

    RollcallTimes selectByPrimaryKey(Integer rollcalltimesid);

    int updateByPrimaryKeySelective(RollcallTimes record);

    int updateByPrimaryKey(RollcallTimes record);

    /**
     * 统计该教师点名的数据
     * 也可以当做获取次数
     *
     * @param teacherCode
     * @param classCourseId
     * @return
     */
    List<RollcallTimes> selectByTeacherCodeAndClassCourseId(@Param("teachercode") String teacherCode, @Param("classcourseid") Integer classCourseId);

    /**
     * 获取点名次数
     * @param classCourseId
     * @return
     */
    List<RollcallTimes> selectByClassCoureId(@Param("classcourseid") Integer classCourseId);
}