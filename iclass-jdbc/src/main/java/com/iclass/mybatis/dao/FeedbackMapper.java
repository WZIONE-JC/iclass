package com.iclass.mybatis.dao;

import com.iclass.mybatis.po.Feedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeedbackMapper {
    int deleteByPrimaryKey(Integer feedbackid);

    int insert(Feedback record);

    int insertSelective(Feedback record);

    Feedback selectByPrimaryKey(Integer feedbackid);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);

    List<Feedback> selectByTeacherCode(@Param("teachercode") String TeacherCode, @Param("parentId") Integer parentId,
                                    @Param("start") Integer start, @Param("length") Integer length);

    List<Feedback> selectByTeacherCodeNoLimit(@Param("teachercode") String TeacherCode, @Param("parentId") Integer parentId);

    List<Feedback> selectByUserCode(@Param("usercode") String userCode, @Param("parentId") Integer parentId,
                                       @Param("start") Integer start, @Param("length") Integer length);

    List<Feedback> selectByUserCodeNoLimit(@Param("usercode") String userCode, @Param("parentId") Integer parentId);

    /**
     * 统计父贴下面有多少个子贴
     * @param parentId
     * @return
     */
    Integer countByParentId(@Param("parentid") Integer parentId);
}