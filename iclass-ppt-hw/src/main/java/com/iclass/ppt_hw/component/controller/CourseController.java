package com.iclass.ppt_hw.component.controller;

import com.iclass.mybatis.dto.CourseDTO;
import com.iclass.mybatis.po.Course;
import com.iclass.ppt_hw.component.service.api.CourseService;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ServiceResult<List<CourseDTO>> getCourseByTeacherCode(DataTablesRequestEntity requestEntity, String teacherCode) {

        return courseService.getCourseByTeacherCode(requestEntity, teacherCode);
    }

    /**
     * 保存课程信息
     * @param course
     * @return
     */
    @RequestMapping("/save")
    ServiceResult<ResponseMsg> save(Course course, String coursecreator) {

        return courseService.save(course, coursecreator);
    }

    /**
     * 更新课程信息
     * @param course
     * @return
     */
    @RequestMapping("/update")
    ServiceResult<ResponseMsg> update(Course course) {

        return courseService.update(course);
    }

    /**
     * 检查课程编号是否存在
     * @param courseCode
     * @return
     */
    @RequestMapping("/check/{code}")
    ServiceResult<ResponseMsg> check(@PathVariable("code") String courseCode) {

        return courseService.check(courseCode);
    }

    /**
     * 改变课程状态
     * @param status
     * @return
     */
    @RequestMapping("/updateStatus")
    ServiceResult<ResponseMsg> updateStatus(Integer id, int status) {

        return courseService.updateStatus(id, status);
    }

    /**
     * 根据课程id获取课程信息
     * @param id
     * @return
     */
    @RequestMapping("/get/{id}")
    ServiceResult<Course> get(@PathVariable("id") Integer id) {

        return courseService.get(id);
    }

    /**
     * 根据courseCode来获取课程信息
     * @param courseCode
     * @return
     */
    @RequestMapping("/getByCode/{code}")
    ServiceResult<Course> getByCode(@PathVariable("code") String courseCode) {

        return courseService.getByCode(courseCode);
    }
}
