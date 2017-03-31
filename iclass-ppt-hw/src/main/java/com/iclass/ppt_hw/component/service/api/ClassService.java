package com.iclass.ppt_hw.component.service.api;

import com.iclass.mybatis.dto.ClassDTO;
import com.iclass.mybatis.po.Class;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;

import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/11/2017 10:08 PM.
 * <p>
 * 获取课堂信息
 */
public interface ClassService {

    /**
     * 创建课堂
     *
     * @param c 课堂信息
     * @return 处理信息
     */
    ServiceResult<ResponseMsg> createClass(Class c);

    /**
     * 改变课程状态
     *
     * @param status 0：表示课堂失效， 1：表示课程有效
     * @return
     */
    ServiceResult<ResponseMsg> updateClassStatus(int status);

    /**
     * 获取所有课堂信息
     *
     * @return 课堂信息
     */
    ServiceResult<List<ClassDTO>> getAllClasses();

    /**
     * 获取教师所创建的课程
     *
     * @param requestEntity datatable请求过来的数据
     * @param classCreator teacherCode
     * @return 该教师所创建的课堂信息
     */
    ServiceResult<List<ClassDTO>> getClassesByClassCreator(DataTablesRequestEntity requestEntity, String classCreator);

    /**
     * 获取对应ClassCourseCode 的Class 实体
     * @param classCourseCode ClassCourseCode
     * @return class 实体
     */
    ServiceResult<List<ClassDTO>> getClassesByClassCourseCode(String classCourseCode);
}
