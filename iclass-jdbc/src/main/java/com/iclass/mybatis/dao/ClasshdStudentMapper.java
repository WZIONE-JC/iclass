package com.iclass.mybatis.dao;

import com.iclass.mybatis.po.ClasshdStudent;
import org.apache.ibatis.annotations.Param;

public interface ClasshdStudentMapper {
    int deleteByPrimaryKey(Integer classhdstudentid);

    int insert(ClasshdStudent record);

    int insertSelective(ClasshdStudent record);

    ClasshdStudent selectByPrimaryKey(Integer classhdstudentid);

    int updateByPrimaryKeySelective(ClasshdStudent record);

    int updateByPrimaryKey(ClasshdStudent record);

    /**
     * 查询是否已经存在了答题记录
     * @param classhdId
     * @param studentCode
     * @return
     */
    ClasshdStudent selectByClasshdIdAndStudentCode(@Param("classhdid") Integer classhdId, @Param("studentcode") String studentCode);
}