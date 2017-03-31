package com.iclass.ppt_hw.component.controller;

import com.iclass.mybatis.dto.ClassCourseDTO;
import com.iclass.ppt_hw.component.service.api.ClassCourseService;
import com.iclass.user.component.entity.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/11/2017 11:34 PM.
 */
@RestController
@RequestMapping("/classCourse")
public class ClassCourseController {

    @Autowired
    private ClassCourseService classCourseService;

    /**
     * 同时获取老师创建的课堂和课程信息
     * @param classCreator 教师编号
     * @return 课堂和课程数据
     */
    @RequestMapping("/get")
    public ServiceResult<List<ClassCourseDTO>> getClassCourse(String classCreator) {

        return classCourseService.getClassCourse(classCreator);
    }

}
