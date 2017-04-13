package com.iclass.ppt_hw.component.service.api;

import com.iclass.mybatis.dto.ClassCourseDTO;
import com.iclass.mybatis.dto.IclassfileDTO;
import com.iclass.mybatis.qo.FileQo;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;

import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/12/2017 2:06 PM.
 */
public interface PPTHWService {

    /**
     * 获取已上传课件浏览页面信息
     * @param requestEntity datatables请求数据
     * @param classCreator teacherCode
     * @return 返回PPTHW 实体
     */
    ServiceResult<List<ClassCourseDTO>> getPPTInfo(DataTablesRequestEntity requestEntity, String classCreator);


    /**
     * 获取已上传作业页面信息
     * @param requestEntity datatables请求数据
     * @param classCreator teacherCode
     * @return 返回PPTHW实体
     */
    ServiceResult<List<ClassCourseDTO>> getHWInfo(DataTablesRequestEntity requestEntity, String classCreator);

    /**
     * 获取与课堂和课程相关联的文件信息
     * @param requestEntity datatables请求数据
     * @param fileQo fileQo
     * @return
     */
    ServiceResult<List<IclassfileDTO>> getPPTHWFileInfo(DataTablesRequestEntity requestEntity, FileQo fileQo);

    /**
     * 根据课堂id去获取它的文件信息
     * @param classCourseId
     * @return
     */
    ServiceResult<List<IclassfileDTO>> getPPTFileInfo(Integer classCourseId);

    /**
     * 根据课堂id去获取它的文件信息
     * @param classCourseId
     * @return
     */
    ServiceResult<List<IclassfileDTO>> getHWFileInfo(Integer classCourseId);
}
