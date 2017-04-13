package com.iclass.ppt_hw.component.service.impl;

import com.iclass.mybatis.dao.ClassCourseMapper;
import com.iclass.mybatis.dao.ClassMapper;
import com.iclass.mybatis.dao.UserMapper;
import com.iclass.mybatis.dto.ClassDTO;
import com.iclass.mybatis.po.Class;
import com.iclass.mybatis.po.ClassCourse;
import com.iclass.mybatis.po.User;
import com.iclass.ppt_hw.component.service.api.ClassService;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.Msg;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.utils.CheckDataTables;
import com.iclass.user.component.utils.IclassUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/11/2017 10:32 PM.
 */
@Service("classService")
public class ClassServiceImpl implements ClassService {

    private final Logger logger = LoggerFactory.getLogger(ClassServiceImpl.class);

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ClassCourseMapper classCourseMapper;

    /**
     *
     * @param c 课堂信息
     * @return 返回处理信息
     */
    @Override
    public ServiceResult<ResponseMsg> save(Class c) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        if (c == null) {
            serviceResult.setMessage("创建班级失败,班级信息不完整");
            return serviceResult;
        }
        c.setClasscreatetime(IclassUtil.getDateTimeNow());
        c.setClassstatus(1);
        classMapper.insert(c);
        serviceResult.setSuccess(true);
        serviceResult.setData(new ResponseMsg(Msg.OK));
        return serviceResult;
    }

    /**
     *
     * @param status 0：表示课堂失效， 1：表示课程有效
     * @return
     */
    @Override
    public ServiceResult<ResponseMsg> updateClassStatus(int status) {
        return null;
    }

    /**
     * 获取教师所创建的课堂
     *
     * @param classCreator teacherCode
     * @return 课堂信息
     */
    @Override
    public ServiceResult<List<ClassDTO>> getClassesByClassCreator(DataTablesRequestEntity requestEntity, String classCreator, Boolean isLimit) {
        ServiceResult<List<ClassDTO>> serviceResult = new ServiceResult<>();
        if (StringUtils.isNotBlank(classCreator)) {
            requestEntity = CheckDataTables.check(requestEntity);

            Integer start = requestEntity.getStart();

            Integer length = requestEntity.getLength();

            Integer draw = requestEntity.getDraw();

            List<Class> classes = new ArrayList<>();
            if (isLimit) {
                classes = classMapper.selectByClassCreator(classCreator, start, length);
            } else {
                classes = classMapper.selectByClassCreatorNoLimit(classCreator);
            }
            User user = userMapper.findUserByUsercode(classCreator);

            String teacherName = user.getUserfullname();

            List<ClassDTO> classDTOS = new ArrayList<>();

            for(Class c : classes) {
                classDTOS.add(new ClassDTO(c, teacherName));
            }

            Integer recordsTotal = classMapper.countByClassCreator(classCreator);

            if(classes != null && classes.size() > 0) {
                serviceResult.setData(classDTOS);
                serviceResult.setDraw(draw);
                serviceResult.setRecordsTotal(recordsTotal);
                serviceResult.setRecordsFiltered(classes.size());
                serviceResult.setSuccess(true);
            } else {
                logger.info("编号为:" + classCreator + "的教师,没有创建班级");
                serviceResult.setMessage("编号为:" + classCreator + "的教师,没有创建班级");
                serviceResult.setRecordsTotal(0);
                serviceResult.setRecordsFiltered(0);
            }
        } else {
            logger.warn("classCreator不能为空");
            serviceResult.setMessage("classCreator不能为空");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ClassDTO>> getClassesByCourseCode(String courseCode) {
        ServiceResult<List<ClassDTO>> serviceResult = new ServiceResult<>();
        if (StringUtils.isNotBlank(courseCode)) {
            List<ClassDTO> classDTOS = new ArrayList<>();
            List<ClassCourse> classCourses = classCourseMapper.selectByCourseCode(courseCode);
            List<Class> classes = new ArrayList<>();
            for (ClassCourse classCourse : classCourses) {
                Class c = classMapper.selectByClassCode(classCourse.getClasscode());
                classes.add(c);
            }
            if (classes != null && classes.size() > 0) {
               for (Class c : classes) {
                   classDTOS.add(new ClassDTO(c));
               }
                serviceResult.setSuccess(true);
               serviceResult.setData(classDTOS);
            } else {
                logger.warn("在class_course表中没有找到courseCode：" + courseCode + "的记录");
                serviceResult.setMessage("该课程还没有应用到课堂中,请先为某课堂添加该课程");
            }
        } else {
            logger.warn("courseCode为空");
            serviceResult.setMessage("courseCode为空");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Class>> getUnRelatedClassesByCourseCode(String courseCode, String classCreator) {
        ServiceResult<List<Class>> serviceResult = new ServiceResult<>();
        if (StringUtils.isBlank(classCreator)) {
            logger.warn("classCreator为空");
            serviceResult.setMessage("未登录");
            return serviceResult;
        }
        if (StringUtils.isBlank(courseCode)) {
            logger.warn("courseCode为空");
            serviceResult.setMessage("courseCode为空");
            return serviceResult;
        }
        List<Class> classes = classMapper.selectUnrelatedClassByCourseCode(courseCode, classCreator);
        if (classes != null && classes.size() > 0) {
            serviceResult.setData(classes);
            serviceResult.setSuccess(true);
        } else {
            serviceResult.setMessage("您创建的所有的班级都已创建该课程!");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<ResponseMsg> checkClassCode(String classCode) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        if (StringUtils.isBlank(classCode)) {
            serviceResult.setMessage("课程编号不能为空");
            return serviceResult;
        }
        Class classes = classMapper.selectByClassCode(classCode);
        if (classes != null) {
            serviceResult.setData(new ResponseMsg(Msg.CLASS_CODE_EXISTED));
            return serviceResult;
        }
        serviceResult.setData(new ResponseMsg(Msg.OK));
        serviceResult.setSuccess(true);
        return serviceResult;
    }

    @Override
    public ServiceResult<Class> getClassById(String id) {
        ServiceResult<Class> serviceResult = new ServiceResult<>();
        if (StringUtils.isBlank(id)) {
            serviceResult.setMessage("id不能为空");
            return serviceResult;
        }
        Class c = classMapper.selectByPrimaryKey(Integer.valueOf(id));
        serviceResult.setSuccess(true);
        serviceResult.setData(c);
        return serviceResult;
    }

    @Override
    public ServiceResult<Class> getClassByCode(String classCode) {
        ServiceResult<Class> serviceResult = new ServiceResult<>();
        if (StringUtils.isBlank(classCode)) {
            serviceResult.setMessage("班级编号不能为空");
            return serviceResult;
        }
        Class c = classMapper.selectByClassCode(classCode);
        serviceResult.setSuccess(true);
        serviceResult.setData(c);
        return serviceResult;
    }

    @Override
    public ServiceResult<ResponseMsg> updateClass(Class c) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        if (c == null) {
            serviceResult.setMessage("更新班级信息失败,班级信息不完整");
            return serviceResult;
        }
        classMapper.updateByPrimaryKeySelective(c);
        serviceResult.setSuccess(true);
        serviceResult.setData(new ResponseMsg(Msg.OK));
        return serviceResult;
    }
}
