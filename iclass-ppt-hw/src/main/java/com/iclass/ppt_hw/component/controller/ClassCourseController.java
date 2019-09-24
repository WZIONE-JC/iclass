package com.iclass.ppt_hw.component.controller;

import com.iclass.mybatis.dto.ClassCourseDTO;
import com.iclass.mybatis.po.ClassCourse;
import com.iclass.mybatis.vo.ClassCourseVo;
import com.iclass.ppt_hw.component.service.api.ClassCourseService;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ServiceResult<List<ClassCourseDTO>> getClassCourse(DataTablesRequestEntity requestEntity, String classCreator, @RequestParam(required = false) Boolean isLimit) {
        if (isLimit == null) {
            isLimit = Boolean.TRUE;
        }
        return classCourseService.getClassCourse(requestEntity, classCreator, isLimit);
    }

    /**
     * 开设课堂
     * @param classCourse
     * @return
     */
    @RequestMapping("/save")
    ServiceResult<ResponseMsg> save(ClassCourse classCourse, Integer attendnumber, Integer attendtime) {

        return classCourseService.save(classCourse, attendnumber, attendtime);
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

    /**
     * 根据classCode和CourseCode查看课堂是否存在
     * @param classcourseid
     * @param classcode
     * @param coursecode
     * @return
     */
    @RequestMapping("/check")
    ServiceResult<ResponseMsg> check(Integer classcourseid, String classcode, String coursecode) {

        return classCourseService.check(classcourseid, classcode, coursecode);
    }

    /**
     * 根据学生的code获取已经选的课程
     * @param studentCode
     * @return
     */
    @RequestMapping(value = "/getClassRoom",method = RequestMethod.POST)
    ServiceResult<List<ClassCourseDTO>> getSelectedClassRoom(String studentCode) {

        return classCourseService.getSelectedClassRoom(studentCode);
    }

    /**
     * 根据学生的code获取未选的课程
     * @param studentCode
     * @return
     */
    @RequestMapping(value = "/getClassRoom2", method = RequestMethod.POST)
    ServiceResult<List<ClassCourseDTO>> getUnSelectedClassRoom(String studentCode) {

        return classCourseService.getUnSelectedClassRoom(studentCode);
    }

    /**
     * 学生加入课堂
     * @param studentCode
     * @param classCourseId
     * @return
     */
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    ServiceResult<ResponseMsg> joinClassRoom(String studentCode, Integer classCourseId) {

        return classCourseService.joinClassRoom(studentCode, classCourseId);
    }

    /**
     * 获取该教师创建的课堂
     * @param teacherCode
     * @return
     */
    @RequestMapping("/getClassCourseInfo")
    ServiceResult<List<ClassCourseVo>> getClassCourseInfo(String teacherCode) {

        return classCourseService.getClassCourseInfo(teacherCode);
    }
}
