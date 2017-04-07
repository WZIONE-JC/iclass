package com.iclass.ppt_hw.component.controller;

import com.iclass.mybatis.dto.ClassDTO;
import com.iclass.mybatis.po.Class;
import com.iclass.ppt_hw.component.service.api.ClassService;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/11/2017 10:47 PM.
 */
@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    /**
     * 获取该老师创建的课堂
     * @param requestEntity datatables请求数据
     * @param classCreator teacherCode
     * @return 返回class实体集合
     */
    @RequestMapping("/getClass")
    public ServiceResult<List<ClassDTO>> getClassesByClassCreator(DataTablesRequestEntity requestEntity, String classCreator) {

        return classService.getClassesByClassCreator(requestEntity, classCreator);
    }

    /**
     * 通过classCourseCode 来获取课堂信息
     * @param classCourseCode 课程编号
     * @return class实体 集合
     */
    @RequestMapping("/getClassByClassCourseCode")
    public ServiceResult<List<ClassDTO>> getClassesByClassCourseCode(String classCourseCode) {

        return classService.getClassesByClassCourseCode(classCourseCode);
    }

    /**
     * 检查classCode是否存在
     * @param classCode
     * @return
     */
    @RequestMapping("/check/{code}")
    public ServiceResult<ResponseMsg> checkClassCode(@PathVariable("code") String classCode) {

        return classService.checkClassCode(classCode);
    }

    @RequestMapping("/save")
    public ServiceResult<ResponseMsg> save(Class c) {

        return classService.createClass(c);
    }

    /**
     * 根据id获取class信息
     * @param id
     * @return
     */
    @RequestMapping("/get/{classid}")
    public ServiceResult<Class> getClassById(@PathVariable("classid") String id) {

        return classService.getClassById(id);
    }

    @RequestMapping("/update")
    public ServiceResult<ResponseMsg> updateClass(Class c) {

        return classService.updateClass(c);
    }
}
