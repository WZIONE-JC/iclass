package com.iclass.ppt_hw.component.controller;

import com.iclass.mybatis.dto.ClasshdDTO;
import com.iclass.mybatis.po.Classhd;
import com.iclass.mybatis.vo.ClasshdVo;
import com.iclass.ppt_hw.component.service.api.ClasshdService;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 4/14/2017 4:31 PM.
 */
@RestController
@RequestMapping("/classhd")
public class ClasshdController {

    @Autowired
    private ClasshdService classhdService;

    /**
     * 根据教师编号获取教师所创建的互动内容
     * @param requestEntity
     * @param userCode
     * @param isLimit web端需要分页, app端不需要分页
     * @return 返回状态为1的互动数据
     */
    @RequestMapping(value = "/getAll", method = {RequestMethod.POST, RequestMethod.GET})
    ServiceResult<List<ClasshdDTO>> getAll(DataTablesRequestEntity requestEntity, String userCode, Boolean isLimit) {
        if (isLimit == null) {
            isLimit = true;
        }
        return classhdService.getAll(requestEntity, userCode, isLimit);
    }

    /**
     * 检查学生作答的答案
     * @param classhdId
     * @param studentCode
     * @param answer
     * @return
     */
    @RequestMapping(value = "/check", method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResult<ResponseMsg> checkAnswer(Integer classhdId, String studentCode, String answer) {

        return classhdService.checkAnswer(classhdId, studentCode, answer);
    }

    /**
     * 添加课堂互动内容
     * @param classhd
     * @return
     */
    @RequestMapping("/save")
    ServiceResult<ResponseMsg> save(Classhd classhd, @RequestParam("classcode") String classCode, @RequestParam("coursecode") String courseCode) {

        return classhdService.save(classhd, classCode, courseCode);
    }

    /**
     * 删除课堂互动内容
     * @param classhdid
     * @return
     */
    @RequestMapping("/del/{id}")
    ServiceResult<ResponseMsg> del(@PathVariable("id") Integer classhdid) {

        return classhdService.del(classhdid);
    }

    /**
     * 更新课堂互动内容
     * @param classhd
     * @return
     */
    @RequestMapping("/update")
    ServiceResult<ResponseMsg> update(Classhd classhd) {

        return classhdService.update(classhd);
    }

    /**
     * 根据课堂互动id来获取课堂互动的内容
     * @param classhdId
     * @return
     */
    @RequestMapping("/get/{id}")
    ServiceResult<ClasshdVo> get(@PathVariable("id") Integer classhdId) {

        return classhdService.get(classhdId);
    }
//
//    /**
//     * 更新课堂互动内容的状态
//     * @param classhd
//     * @param status
//     * @return
//     */
//    ServiceResult<ResponseMsg> updateStatus(Classhd classhd, int status);
}
