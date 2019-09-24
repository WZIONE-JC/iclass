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

    /**
     * 根据创建者编号来查询课堂信息,没有长度限制
     * @param classcreator
     * @return
     */
    List<Class> selectByClassCreatorNoLimit(@Param("classCreator") String classcreator);

    /**
     * 表结构改变,废弃此方法
     * 2017年04月08日16:18:21
     *
     * 根据课程编号去查询课堂信息
     * @param classCourseCode
     * @return
     */
//    List<Class> selectByClassCourseCode(@Param("classcoursecode") String classCourseCode);

    /**
     * 统计该教师创建了多少课堂
     * @param classcreator
     * @return
     */
    int countByClassCreator(@Param("classCreator") String classcreator);

    /**
     * 根据classCode查询Class信息
     * @param classCode
     * @return
     */
    Class selectByClassCode(@Param("classcode") String classCode);

    /**
     * 查询未与课程关联的班级
     *
     * @param courseCode
     * @param classcreator
     * @return
     */
    List<Class> selectUnrelatedClassByCourseCode(@Param("coursecode") String courseCode, @Param("classcreator") String classcreator);

    /**
     *
     * @param studentCode
     * @return
     */
    Class selectByStudetCode(@Param("studentcode") String studentCode);

    /**
     * 查询所有的班级
     * @return
     */
    Integer countAll();
}