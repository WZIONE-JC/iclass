package com.iclass.ppt_hw.component.service.impl;

import com.iclass.mybatis.dao.CourseMapper;
import com.iclass.mybatis.dao.TeacherCourseMapper;
import com.iclass.mybatis.dto.CourseDTO;
import com.iclass.mybatis.po.Course;
import com.iclass.mybatis.po.TeacherCourse;
import com.iclass.ppt_hw.component.service.api.CourseService;
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
 * Created by JasonTang on 3/12/2017 6:01 PM.
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService{

    private final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    /**
     * 先通过 teahcerCode 去teacher_course 表中获取courseCode
     */
    @Autowired
    private TeacherCourseMapper teacherCourseMapper;

    /**
     * 再通过 courseCode 去course 表中获Course 实体
     */
    @Autowired
    private CourseMapper courseMapper;

    /**
     * 获取老师创建的课程
     * @param teacherCode teacherCode
     * @return 课程信息
     */
    @Override
    public ServiceResult<List<CourseDTO>> getCourseByTeacherCode(String teacherCode) {
        ServiceResult<List<CourseDTO>> serviceResult = new ServiceResult<>();
        if (StringUtils.isNotBlank(teacherCode)) {
            List<TeacherCourse> teacherCourseList = teacherCourseMapper.selectByTeacherCode(teacherCode);
            List<CourseDTO> courses = new ArrayList<>();
            if (teacherCourseList != null && teacherCourseList.size() > 0) {
                for (TeacherCourse tc : teacherCourseList) {
                    Course course = courseMapper.selectByCourseCode(tc.getCoursecode());
                    courses.add(new CourseDTO(course));
                }
                serviceResult.setSuccess(true);
                serviceResult.setData(courses);
            } else {
                logger.info("编号为:" + teacherCode +"的教师,没有创建课程");
                serviceResult.setMessage("编号为:" + teacherCode +"的教师,没有创建课程");
            }
        } else {
            logger.warn("teacherCode不能为空");
            serviceResult.setMessage("用户未登录");
        }
        return serviceResult;
    }
}
