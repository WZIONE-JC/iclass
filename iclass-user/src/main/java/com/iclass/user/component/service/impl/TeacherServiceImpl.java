package com.iclass.user.component.service.impl;

import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.service.api.TeacherService;
import com.iclass.mybatis.dao.TeacherMapper;
import com.iclass.mybatis.model.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/28 15:02.
 */
@Service("TeacherService")
public class TeacherServiceImpl implements TeacherService{

    private final Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public ServiceResult<Teacher> save(Teacher teacher) {
        ServiceResult<Teacher> serviceResult = new ServiceResult<>();
        if(teacher != null) {
            int result = teacherMapper.insert(teacher);
            if(result == 1) {
                serviceResult.setSuccess(true);
                serviceResult.setData(teacher);
            } else {
                logger.error("sava:教师信息保存失败");
                serviceResult.setMessage("sava:教师信息保存失败");
            }
        } else {
            logger.error("save:保存教师信息出错,教师信息不能为空");
            serviceResult.setMessage("save:保存教师信息出错,教师信息不能为空");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Teacher> delete(String teacherCode) {
        return null;
    }
}
