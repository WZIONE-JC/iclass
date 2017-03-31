package com.iclass.ppt_hw.component.controller;

import com.iclass.mybatis.dto.CourseDTO;
import com.iclass.ppt_hw.component.service.api.CourseService;
import com.iclass.user.component.entity.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/12/2017 6:10 PM.
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/get")
    public ServiceResult<List<CourseDTO>> getCourseByTeacherCode(String teacherCode) {

        return courseService.getCourseByTeacherCode(teacherCode);
    }
}
