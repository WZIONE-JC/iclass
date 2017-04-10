package com.iclass.user.component.service.impl;

import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.service.api.StudentService;
import com.iclass.mybatis.dao.StudentMapper;
import com.iclass.mybatis.po.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/28 14:56.
 */
@Service("StudentService")
public class StudentServiceImpl implements StudentService {

    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public ServiceResult<Student> save(Student student) {

        ServiceResult<Student> serviceResult = new ServiceResult<>();
        if (student != null) {
            int result = studentMapper.insert(student);
            if (result == 1) {
                serviceResult.setSuccess(true);
                serviceResult.setData(student);
            } else {
                serviceResult.setMessage("sava:学生信息保存失败");
            }
        } else {
            serviceResult.setMessage("save:保存学生信息出错,信息不能为空");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Student> delete(String studentCdoe) {
        return null;
    }
}
