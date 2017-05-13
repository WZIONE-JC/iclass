package com.iclass.mybatis.dao;

import com.iclass.mybatis.po.Classhd;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClasshdMapper {
    int deleteByPrimaryKey(Integer classhdid);

    int insert(Classhd record);

    int insertSelective(Classhd record);

    Classhd selectByPrimaryKey(Integer classhdid);

    int updateByPrimaryKeySelective(Classhd record);

    int updateByPrimaryKey(Classhd record);

    List<Classhd> selectByClasshdCreator(@Param("classhdcreator") String classhdCreator, @Param("start") Integer start, @Param("length") Integer length);

    List<Classhd> selectByClasshdCreatorNolimit(@Param("classhdcreator") String classhdCreator);

    List<Classhd> selectByStudentCode(@Param("studentcode") String studentCode, @Param("start") Integer start, @Param("length") Integer length);

    List<Classhd> selectByStudentCodeNolimit(@Param("studentcode") String studentCode);

    Integer countByClasshdCreator(@Param("classhdcreator") String classhdCreator);

    int updateRightNumber(@Param("classhdid") Integer classhdId);

    int updateStatus(@Param("classhdid") Integer classhdId, @Param("classhdstatus") int classhdStatus);
}