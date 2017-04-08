package com.iclass.ppt_hw.component.service.impl;

import com.iclass.mybatis.dao.*;
import com.iclass.mybatis.dto.ClassCourseDTO;
import com.iclass.mybatis.dto.SessionUser;
import com.iclass.mybatis.po.*;
import com.iclass.mybatis.po.Class;
import com.iclass.ppt_hw.component.service.api.ClassCourseService;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.Msg;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.utils.CheckDataTables;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/11/2017 11:17 PM.
 */
@Service("classCourseService")
public class ClassCourseServiceImpl implements ClassCourseService{

    private final Logger logger = LoggerFactory.getLogger(ClassCourseServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private ClassCourseMapper classCourseMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ClassCourseStudentMapper classCourseStudentMapper;

    /**
     * 获取该教师所带的课堂和课程
     * @param classCreator 教师编号
     * @return 课堂和课程的实体
     */
    @Override
    public ServiceResult<List<ClassCourseDTO>> getClassCourse(DataTablesRequestEntity requestEntity, String classCreator) {
        ServiceResult<List<ClassCourseDTO>> serviceResult = new ServiceResult<>();
        List<ClassCourseDTO> classCourseDTOs = new ArrayList<>();
        if(StringUtils.isBlank(classCreator)) {
            logger.warn("classCreator不能为空");
            serviceResult.setMessage("未登录");
            return serviceResult;
        }
        requestEntity = CheckDataTables.check(requestEntity);
        Integer draw = requestEntity.getDraw();
        Integer start = requestEntity.getStart();
        Integer length = requestEntity.getLength();
        //1.根据classCreator去查询该教师所创建的课程
        List<Class> classes = classMapper.selectByClassCreator(classCreator, start, length);

        User teacher = userMapper.findUserByUsercode(classCreator);
        String teacherName = teacher.getUserfullname();
        if(classes != null && classes.size() > 0) {
            for(Class c : classes) {
                //2.根据classCode去查询class_course表,查询courseCode
                String classCode = c.getClasscode();
                List<ClassCourse> classCourseList = classCourseMapper.selectByClassCode(classCode);
                for (ClassCourse classCourse : classCourseList) {
                    Integer classcourseId = classCourse.getClasscourseid();
                    String createTime = classCourse.getCreatetime();
                    String deadline = classCourse.getDeadline();
                    //3.根据courseCode去查询Course实体
                    String courseCode = classCourse.getCoursecode();
                    Course course = courseMapper.selectByCourseCode(courseCode);
                    //4.查询学生数据
                    List<SessionUser> sessionUsers = new ArrayList<>();
                    Integer classCourseId = classCourse.getClasscourseid();
                    List<ClassCourseStudent> classCourseStudents = classCourseStudentMapper.selectByClassCourseId(classCourseId);
                    // 如果有学生的话
                    if (classCourseStudents != null && classCourseStudents.size() > 0) {
                        for (ClassCourseStudent cs : classCourseStudents) {
                            String studentCode = cs.getStudentcode();
                            //根据学生编号查询学生信息
                            User user = userMapper.findUserByUsercode(studentCode);
                            sessionUsers.add(new SessionUser(user));
                        }
                    }
                    classCourseDTOs.add(new ClassCourseDTO(classcourseId, c, course, teacherName, sessionUsers, createTime, deadline));
                }
            }
            serviceResult.setSuccess(true);
            serviceResult.setData(classCourseDTOs);
            serviceResult.setRecordsTotal(classCourseDTOs.size());
            serviceResult.setRecordsFiltered(classCourseDTOs.size());
        } else {
            logger.info("编号为:" + classCreator +"的教师,没有创建课堂");
            serviceResult.setMessage("编号为:" + classCreator +"的教师,没有创建课堂");
            serviceResult.setRecordsTotal(0);
            serviceResult.setRecordsFiltered(0);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<ResponseMsg> save(ClassCourse classCourse) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        if (classCourse == null) {
            serviceResult.setMessage("开设课堂失败,信息不完整");
            return serviceResult;
        }
        if (StringUtils.isBlank(classCourse.getClasscode())) {
            serviceResult.setMessage("开设课堂失败,课堂编号为空");
            return serviceResult;
        }
        if (StringUtils.isBlank(classCourse.getCoursecode())) {
            serviceResult.setMessage("开设课堂失败,课程编号为空");
            return serviceResult;
        }
        classCourse.setStatus(1);
        classCourseMapper.insert(classCourse);
        serviceResult.setSuccess(true);
        serviceResult.setData(new ResponseMsg(Msg.OK));
        return serviceResult;
    }

    @Override
    public ServiceResult<ResponseMsg> update(ClassCourse classCourse) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        if (classCourse == null) {
            serviceResult.setMessage("开设课堂失败,信息不完整");
            return serviceResult;
        }
        if (classCourse.getClasscourseid() == null) {
            serviceResult.setMessage("id不能为空");
            return serviceResult;
        }
        classCourseMapper.updateByPrimaryKeySelective(classCourse);
        serviceResult.setSuccess(true);
        serviceResult.setData(new ResponseMsg(Msg.OK));
        return serviceResult;
    }

    @Override
    public ServiceResult<ClassCourse> get(Integer classCourseId) {
        ServiceResult<ClassCourse> serviceResult = new ServiceResult<>();
        if (classCourseId == null) {
            serviceResult.setMessage("id不能为空");
            return serviceResult;
        }
        ClassCourse classCourse = classCourseMapper.selectByPrimaryKey(classCourseId);
        serviceResult.setSuccess(true);
        serviceResult.setData(classCourse);
        return serviceResult;
    }
}
