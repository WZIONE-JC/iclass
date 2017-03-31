package com.iclass.ppt_hw.component.service.impl;

import com.iclass.mybatis.dao.ClassMapper;
import com.iclass.mybatis.dao.CourseMapper;
import com.iclass.mybatis.dto.ClassCourseDTO;
import com.iclass.mybatis.po.Class;
import com.iclass.mybatis.po.Course;
import com.iclass.ppt_hw.component.service.api.ClassCourseService;
import com.iclass.user.component.entity.ServiceResult;
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
 * Created by JasonTang on 3/11/2017 11:17 PM.
 */
@Service("classCourseService")
public class ClassCourseServiceImpl implements ClassCourseService{

    private final Logger logger = LoggerFactory.getLogger(ClassCourseServiceImpl.class);

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 获取该教师所带的课堂和课程
     * @param classCreator 教师编号
     * @return 课堂和课程的实体
     */
    @Override
    public ServiceResult<List<ClassCourseDTO>> getClassCourse(String classCreator) {
        ServiceResult<List<ClassCourseDTO>> serviceResult = new ServiceResult<>();
        List<ClassCourseDTO> classCourseVos = new ArrayList<>();
        if(StringUtils.isNotBlank(classCreator)) {
            List<Class> classes = classMapper.selectByClassCreatorNoLimit(classCreator);
            Course course;
            if(classes != null && classes.size() > 0) {
                for(Class c : classes) {
                    logger.info(c.toString());
                    course = courseMapper.selectByCourseCode(c.getClasscoursecode());
                    logger.info(course.toString());
                    classCourseVos.add(new ClassCourseDTO(c, course));
                }
                serviceResult.setSuccess(true);
                serviceResult.setData(classCourseVos);
                serviceResult.setRecordsTotal(classCourseVos.size());
            } else {
                logger.info("编号为:" + classCreator +"的教师,没有创建课堂");
                serviceResult.setMessage("编号为:" + classCreator +"的教师,没有创建课堂");
                serviceResult.setRecordsTotal(0);
                serviceResult.setRecordsFiltered(0);
            }
        } else {
            logger.warn("classCreator不能为空");
            serviceResult.setMessage("classCreator不能为空");
        }
        return serviceResult;
    }
}
