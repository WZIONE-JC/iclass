package com.iclass.user.component.service.api;

import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.mybatis.model.Teacher;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/20/2017 11:17 PM.
 */
public interface TeacherService {

    /**
     * 保存教师信息
     * @param teacher 教师实体
     */
    public ServiceResult<Teacher> save(Teacher teacher);

    /**
     * 删除教师信息
     * @param teacherCode 教师的工号
     */
    public ServiceResult<Teacher> delete(String teacherCode);
}
