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


    /**
     * 根据创建者编码查询课堂信息
     * @param classcreator
     * @param start
     * @param length
     * @return
     */
    List<Class> selectByClassCreator(@Param("classCreator") String classcreator, @Param("start") Integer start, @Param("length") Integer length);

    List<Class> selectByClassCreatorNoLimit(@Param("classCreator") String classcreator);

    List<Class> selectByClassCourseCode(@Param("classcoursecode") String classCourseCode);

    int countByClassCreator(@Param("classCreator") String classcreator);

    /**
     * 根据classCode查询Class信息
     * @param classCode
     * @return
     */
    List<Class> selectByClassCode(@Param("classcode") String classCode);

}