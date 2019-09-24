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

    ClassCourse selectByClassCodeAndCourseCode(@Param("classcode") String classCode, 
												@Param("coursecode") String courseCode);

    List<ClassCourse> selectUnselectedClassCourse(@Param("studentcode") String studentCode);


    /**
     * 根据创建者编码查询课堂信息
     * @param classcreator
     * @param start
     * @param length
     * @return
     */
    List<ClassCourse> selectByClassCreator(@Param("classcreator") String classcreator, @Param("start") Integer start,
											@Param("length") Integer length);

    /**
     * 根据创建者编号来查询课堂信息,没有长度限制
     * @param classcreator
     * @return
     */
    List<ClassCourse> selectByClassCreatorNoLimit(@Param("classcreator") String classcreator);

    /**
     * 统计课堂数
     * @param classcreator
     * @return
     */
    Integer countByClassCreator(@Param("classcreator") String classcreator);

    /**
     * 统计所有的课堂
     * @return
     */
    Integer countAll();
}