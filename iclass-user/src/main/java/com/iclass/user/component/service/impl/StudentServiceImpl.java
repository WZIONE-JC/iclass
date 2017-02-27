package com.iclass.user.component.service.impl;

import com.iclass.user.component.service.api.StudentService;
import com.iclass.user.mybatis.dao.StudentMapper;
import com.iclass.user.mybatis.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/20/2017 11:19 PM.
 */
@Service("StudentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void save(Student student) {

    }
    @Override
    public void delete(String studentCdoe) {

    }
}
