package com.iclass.mybatis.dao;

import com.iclass.mybatis.po.Iclassfile;
import org.apache.ibatis.annotations.Param;

public interface IclassfileMapper {
    int deleteByPrimaryKey(Integer fileid);

    int insert(Iclassfile record);

    int insertSelective(Iclassfile record);

    Iclassfile selectByPrimaryKey(Integer fileid);

    int updateByPrimaryKeySelective(Iclassfile record);

    int updateByPrimaryKey(Iclassfile record);

    Iclassfile selectByFileCode(@Param("filecode") String filecode, @Param("filetype") Integer fileType);

    int updateFileDownloadTimeByFileCode(@Param("filecode") String filecode);
}