package com.iclass.mybatis.dao;

import com.iclass.mybatis.po.Class;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassMapper {
    int deleteByPrimaryKey(Integer classid);

    int insert(Class record);

    int insertSelective(Class record);

    Class selectByPrimaryKey(Integer classid);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);

    List<Class> selectByClassCreator(@Param("classCreator") String classCreator, @Param("start") Integer start, @Param("length") Integer length);

    List<Class> selectByClassCreatorNoLimit(@Param("classCreator") String classCreator);

    List<Class> selectByClassCourseCode(@Param("classcoursecode") String classCousreCode);

    int countByClassCreator(@Param("classCreator") String classCreator);
}