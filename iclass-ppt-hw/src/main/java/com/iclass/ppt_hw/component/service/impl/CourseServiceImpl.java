package com.iclass.ppt_hw.component.service.impl;

import com.iclass.mybatis.dao.CourseMapper;
import com.iclass.mybatis.dao.TeacherCourseMapper;
import com.iclass.mybatis.dao.TeacherMapper;
import com.iclass.mybatis.dao.UserMapper;
import com.iclass.mybatis.dto.CourseDTO;
import com.iclass.mybatis.po.Course;
import com.iclass.mybatis.po.TeacherCourse;
import com.iclass.mybatis.po.User;
import com.iclass.ppt_hw.component.service.api.CourseService;
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
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
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
     * 获取教师信息
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 获取老师创建的课程
     * @param teacherCode teacherCode
     * @return 课程信息
     */
    @Override
    public ServiceResult<List<CourseDTO>> getCourseByTeacherCode(DataTablesRequestEntity requestEntity,  String teacherCode) {
        ServiceResult<List<CourseDTO>> serviceResult = new ServiceResult<>();
        if (StringUtils.isBlank(teacherCode)) {
            logger.warn("teacherCode不能为空");
            serviceResult.setMessage("用户未登录");
            return serviceResult;
        }
        requestEntity = CheckDataTables.check(requestEntity);
        Integer draw = requestEntity.getDraw();
        Integer start = requestEntity.getStart();
        Integer length = requestEntity.getLength();
        Integer count = teacherCourseMapper.countByTeacherCode(teacherCode);
        List<TeacherCourse> teacherCourseList = teacherCourseMapper.selectByTeacherCode(teacherCode, start, length);
        List<CourseDTO> courses = new ArrayList<>();
        User user = userMapper.findUserByUsercode(teacherCode);
        String teacherName = user.getUserfullname();
        if (teacherCourseList != null && teacherCourseList.size() > 0) {
            for (TeacherCourse tc : teacherCourseList) {
                Course course = courseMapper.selectByCourseCode(tc.getCoursecode());
                courses.add(new CourseDTO(course,teacherName));
            }
            serviceResult.setSuccess(true);
            serviceResult.setDraw(draw);
            serviceResult.setRecordsTotal(count);
            serviceResult.setRecordsFiltered(count);
            serviceResult.setData(courses);
        } else {
            logger.info("编号为:" + teacherCode +"的教师,没有创建课程");
            serviceResult.setMessage("编号为:" + teacherCode +"的教师,没有创建课程");
        }
        return serviceResult;
    }

    @Override
    @Transactional
    public ServiceResult<ResponseMsg> save(Course course, String coursecreator) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();

        course.setCoursecreatetime(IclassUtil.getDateTimeNow());
        course.setCoursestatus(1);
        courseMapper.insertSelective(course);

        TeacherCourse teacherCourse = new TeacherCourse();
        teacherCourse.setCoursecode(course.getCoursecode());
        teacherCourse.setTeachercode(coursecreator);
        teacherCourseMapper.insertSelective(teacherCourse);

        serviceResult.setSuccess(true);
        serviceResult.setData(new ResponseMsg(Msg.OK));
        return serviceResult;
    }

    @Override
    public ServiceResult<ResponseMsg> update(Course course) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        courseMapper.updateByPrimaryKeySelective(course);
        serviceResult.setSuccess(true);
        serviceResult.setData(new ResponseMsg(Msg.OK));
        return serviceResult;
    }

    @Override
    public ServiceResult<ResponseMsg> check(String courseCode) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        if (StringUtils.isBlank(courseCode)) {
            serviceResult.setMessage("课程编号不能为空");
            return serviceResult;
        }
        Course course = courseMapper.selectByCourseCode(courseCode);
        if (course != null) {
            serviceResult.setData(new ResponseMsg(Msg.COURSE_CODE_EXISTED));
            return serviceResult;
        }
        serviceResult.setSuccess(true);
        serviceResult.setData(new ResponseMsg(Msg.OK));
        return serviceResult;
    }

    @Override
    public ServiceResult<ResponseMsg> updateStatus(Integer id, int status) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        if (id == null) {
            serviceResult.setMessage("课程id不能为空");
            return serviceResult;
        }
        courseMapper.updateStatusById(id, status);
        serviceResult.setSuccess(true);
        serviceResult.setData(new ResponseMsg(Msg.OK));
        return serviceResult;
    }

    @Override
    public ServiceResult<Course> get(Integer id) {
        ServiceResult<Course> serviceResult = new ServiceResult<>();
        if (id == null) {
            serviceResult.setMessage("课程id不能为空");
            return serviceResult;
        }
        Course course = courseMapper.selectByPrimaryKey(id);
        serviceResult.setSuccess(true);
        serviceResult.setData(course);
        return serviceResult;
    }

    @Override
    public ServiceResult<Course> getByCode(String courseCode) {
        ServiceResult<Course> serviceResult = new ServiceResult<>();
        if (StringUtils.isBlank(courseCode)) {
            serviceResult.setMessage("课程编号不能为空");
            return serviceResult;
        }
        Course course = courseMapper.selectByCourseCode(courseCode);
        serviceResult.setSuccess(true);
        serviceResult.setData(course);
        return serviceResult;
    }
}
