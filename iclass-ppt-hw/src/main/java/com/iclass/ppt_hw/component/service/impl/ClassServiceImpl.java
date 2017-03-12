package com.iclass.ppt_hw.component.service.impl;

import com.iclass.mybatis.dao.ClassMapper;
import com.iclass.mybatis.dto.ClassDTO;
import com.iclass.mybatis.po.Class;
import com.iclass.ppt_hw.component.service.api.ClassService;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;
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

    /**
     *
     * @param c 课堂信息
     * @return 返回处理信息
     */
    @Override
    public ServiceResult<ResponseMsg> createClass(Class c) {

        return null;
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
     *
     * @return
     */
    @Override
    public ServiceResult<List<ClassDTO>> getAllClasses() {
        return null;
    }

    /**
     * 获取教师所创建的课堂
     *
     * @param classCreator teacherCode
     * @return 课堂信息
     */
    @Override
    public ServiceResult<List<ClassDTO>> getClassesByClassCreator(DataTablesRequestEntity requestEntity, String classCreator) {
        ServiceResult<List<ClassDTO>> serviceResult = new ServiceResult<>();
        if (StringUtils.isNotBlank(classCreator)) {
            if(requestEntity != null) {
                if(requestEntity.getStart() == null) {
                    requestEntity.setStart(0);
                }
                if(requestEntity.getLength() == null) {
                    requestEntity.setLength(1);
                }
                if(requestEntity.getDraw() == null) {
                    requestEntity.setDraw(1);
                }
            } else {
                requestEntity = new DataTablesRequestEntity(1, 0, 1);
            }
            int start = requestEntity.getStart();
            if(start < 0) {
                start = 0;
            }
            int length = requestEntity.getLength();
            if(length < 1) {
                length = 1;
            }
            List<Class> classes = classMapper.selectByClassCreator(classCreator, start, length);
            List<ClassDTO> classDTOS = new ArrayList<>();

            for(Class c : classes) {
                classDTOS.add(new ClassDTO(c));
            }
            Integer recordsTotal = classMapper.countByClassCreator(classCreator);
            if(classes != null && classes.size() > 0) {
                serviceResult.setData(classDTOS);
                serviceResult.setDraw(requestEntity.getDraw());
                serviceResult.setRecordsTotal(recordsTotal);
                serviceResult.setRecordsFiltered(classes.size());
                serviceResult.setSuccess(true);
            } else {
                logger.info("编号为:" + classCreator + "的教师,没有创建课堂");
                serviceResult.setMessage("编号为:" + classCreator + "的教师,没有创建课堂");
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
    public ServiceResult<List<ClassDTO>> getClassesByClassCourseCode(String classCourseCode) {
        ServiceResult<List<ClassDTO>> serviceResult = new ServiceResult<>();
        if (StringUtils.isNotBlank(classCourseCode)) {
            List<Class> classes = classMapper.selectByClassCourseCode(classCourseCode);
            List<ClassDTO> classDTOS = new ArrayList<>();
            if (classes != null && classes.size() > 0) {
               for (Class c : classes) {
                   classDTOS.add(new ClassDTO(c));
               }
                serviceResult.setSuccess(true);
               serviceResult.setData(classDTOS);
            } else {
                logger.warn("在class表中没有找到classCourseCode：" + classCourseCode + "的记录");
                serviceResult.setMessage("该课程还没有应用到课堂中,请先为某课堂添加该课程");
            }
        } else {
            logger.warn("classCourseCode为空");
            serviceResult.setMessage("classCourseCode为空");
        }
        return serviceResult;
    }
}
