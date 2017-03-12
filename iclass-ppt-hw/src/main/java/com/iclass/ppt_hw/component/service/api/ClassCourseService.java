package com.iclass.ppt_hw.component.service.api;

import com.iclass.mybatis.dto.ClassCourseDTO;
import com.iclass.user.component.entity.ServiceResult;

import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/11/2017 11:14 PM.
 * 获取课程与课堂的联系
 */
public interface ClassCourseService {

    /**
     * 获取该教师所创建的课堂和所带的课程
     * @param classCreator 教师编号
     * @return 课程与课堂
     */
    ServiceResult<List<ClassCourseDTO>> getClassCourse(String classCreator);
}
