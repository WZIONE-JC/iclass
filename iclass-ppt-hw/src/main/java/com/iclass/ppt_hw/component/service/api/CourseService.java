package com.iclass.ppt_hw.component.service.api;

import com.iclass.mybatis.dto.CourseDTO;
import com.iclass.mybatis.po.Course;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;

import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/12/2017 5:56 PM.
 * 课程相关的service
 */
public interface CourseService {

    /**
     * 获取该教师创建的课程
     * @param teacherCode teacherCode
     * @return 课程信息
     */
    ServiceResult<List<CourseDTO>> getCourseByTeacherCode(DataTablesRequestEntity requestEntity, String teacherCode);

    /**
     * 保存课程信息
     * @param course
     * @return
     */
    ServiceResult<ResponseMsg> save(Course course, String coursecreator);

    /**
     * 更新课程信息
     * @param course
     * @return
     */
    ServiceResult<ResponseMsg> update(Course course);

    /**
     * 检查课程编号是否存在
     * @param courseCode
     * @return
     */
    ServiceResult<ResponseMsg> check(String courseCode);

    /**
     * 改变课程状态
     * @param status
     * @return
     */
    ServiceResult<ResponseMsg> updateStatus(Integer id, int status);

    /**
     * 根据课程id获取课程信息
     * @param id
     * @return
     */
    ServiceResult<Course> get(Integer id);

    /**
     * 根据courseCode来获取课程信息
     * @param courseCode
     * @return
     */
    ServiceResult<Course> getByCode(String courseCode);
}
