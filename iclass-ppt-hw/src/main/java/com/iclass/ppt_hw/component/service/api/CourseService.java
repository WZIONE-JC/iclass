package com.iclass.ppt_hw.component.service.api;

import com.iclass.mybatis.dto.CourseDTO;
import com.iclass.user.component.entity.ServiceResult;

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
    ServiceResult<List<CourseDTO>> getCourseByTeacherCode(String teacherCode);
}
