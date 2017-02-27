package com.iclass.user.component.service.impl;

import com.iclass.user.component.service.api.TeacherService;
import com.iclass.user.mybatis.dao.TeacherMapper;
import com.iclass.user.mybatis.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/27/2017 11:28 PM.
 */
@Service("TeacherService")
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public void save(Teacher teacher) {

    }

    @Override
    public void delete(String teacherCode) {

    }
}
