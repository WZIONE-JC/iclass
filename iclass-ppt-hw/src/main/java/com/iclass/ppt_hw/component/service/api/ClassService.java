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
     * 创建班级
     *
     * @param c 班级信息
     * @return 处理信息
     */
    ServiceResult<ResponseMsg> save(Class c);

    /**
     * 改变班级状态
     *
     * @param status 0：表示班级失效， 1：表示班级有效
     * @return
     */
    ServiceResult<ResponseMsg> updateClassStatus(int status);

    /**
     * 获取教师所创建的班级
     *
     * @param requestEntity datatable请求过来的数据
     * @param classCreator teacherCode
     * @return 该教师所创建的班级信息
     */
    ServiceResult<List<ClassDTO>> getClassesByClassCreator(DataTablesRequestEntity requestEntity, String classCreator, Boolean isLimit);

    /**
     * 获取对应courseCode 的Class 实体
     * @param courseCode ClassCourseCode
     * @return class 实体
     */
    ServiceResult<List<ClassDTO>> getClassesByCourseCode(String courseCode);

    /**
     * 获取还没有选该课的班级
     * @param courseCode ClassCourseCode
     * @param classCreator
     * @return class 实体
     */
    ServiceResult<List<Class>> getUnRelatedClassesByCourseCode(String courseCode, String classCreator);

    /**
     * 检查classCode是否存在
     * @param classCode
     * @return
     */
    ServiceResult<ResponseMsg> checkClassCode(String classCode);

    /**
     * 根据id获取class信息
     * @param id
     * @return
     */
    ServiceResult<Class> getClassById(String id);

    /**
     * 根据id获取class信息
     * @param classCode
     * @return
     */
    ServiceResult<Class> getClassByCode(String classCode);
    /**
     * 更新class信息
     * @param c
     * @return
     */
    ServiceResult<ResponseMsg> updateClass(Class c);
}
