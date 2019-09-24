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

    /**
     * 获取学生已选的课堂
     * @param studentcode
     * @return
     */
    List<ClassCourseStudent> selectByStudentCode(String studentcode);

    /**
     * 获取学生未选的课堂,应该在ClassCourseMapper中
     * @param studentcode
     * @return
     */
//    List<ClassCourseStudent> selectByStudentCode2(String studentcode);

    List<ClassCourseStudent> selectByNotEqualsStudentCode(String studentcode);

    ClassCourseStudent selectByClassCourseIdAndStudentCode(@Param("classcourseid") Integer classcourseid, @Param("studentcode") String studentcode);

    /**
     * 统计课堂的学生数
     * @param classcourseid
     * @return
     */
    Integer countByClassCourseId(@Param("classcourseid") Integer classcourseid);
}