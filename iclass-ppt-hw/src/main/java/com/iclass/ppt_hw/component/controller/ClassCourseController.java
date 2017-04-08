package com.iclass.ppt_hw.component.controller;

import com.iclass.mybatis.dto.ClassCourseDTO;
import com.iclass.mybatis.po.ClassCourse;
import com.iclass.ppt_hw.component.service.api.ClassCourseService;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/11/2017 11:34 PM.
 */
@RestController
@RequestMapping("/classcourse")
public class ClassCourseController {

    @Autowired
    private ClassCourseService classCourseService;

    /**
     * 同时获取老师创建的课堂和课程信息
     * @param classCreator 教师编号
     * @return 课堂和课程数据
     */
    @RequestMapping("/getClassCourse")
    public ServiceResult<List<ClassCourseDTO>> getClassCourse(DataTablesRequestEntity requestEntity, String classCreator) {

        return classCourseService.getClassCourse(requestEntity, classCreator);
    }

    /**
     * 开设课堂
     * @param classCourse
     * @return
     */
    @RequestMapping("/save")
    ServiceResult<ResponseMsg> save(ClassCourse classCourse) {

        return classCourseService.save(classCourse);
    }

    /**
     * 更新课堂
     * @param classCourse
     * @return
     */
    @RequestMapping("/update")
    ServiceResult<ResponseMsg> update(ClassCourse classCourse){

        return classCourseService.update(classCourse);
    }

    /**
     * 获取课堂信息
     * @param classCourseId
     * @return
     */
    @RequestMapping("/get/{id}")
    ServiceResult<ClassCourse> get(@PathVariable("id") Integer classCourseId) {

        return classCourseService.get(classCourseId);
    }
}
