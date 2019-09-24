package com.iclass.mybatis.dao;

import com.iclass.mybatis.po.StudentClass;
import org.apache.ibatis.annotations.Param;

public interface StudentClassMapper {
    int deleteByPrimaryKey(Integer studentclassid);

    int insert(StudentClass record);

    int insertSelective(StudentClass record);

    StudentClass selectByPrimaryKey(Integer studentclassid);

    int updateByPrimaryKeySelective(StudentClass record);

    int updateByPrimaryKey(StudentClass record);

    /**
     * 查询班级中有多少人
     * @param classCode
     * @return
     */
    Integer countByClassCode(@Param("classcode") String classCode);

}