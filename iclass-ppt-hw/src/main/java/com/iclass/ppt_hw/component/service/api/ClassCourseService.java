package com.iclass.ppt_hw.component.service.api;

import com.iclass.mybatis.dto.ClassCourseDTO;
import com.iclass.mybatis.po.ClassCourse;
import com.iclass.mybatis.vo.ClassCourseVo;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;

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
     * @return 课堂
     */
    ServiceResult<List<ClassCourseDTO>> getClassCourse(DataTablesRequestEntity requestEntity, String classCreator, Boolean isLimit);

    /**
     * 开设课堂
     * @param classCourse
     * @return
     */
    ServiceResult<ResponseMsg> save(ClassCourse classCourse, Integer attendnumber, Integer attendtime);

    /**
     * 更新课堂
     * @param classCourse
     * @return
     */
    ServiceResult<ResponseMsg> update(ClassCourse classCourse);

    /**
     * 获取课堂信息
     * @param classCourseId
     * @return
     */
    ServiceResult<ClassCourse> get(Integer classCourseId);

    /**
     * 根据classCode和CourseCode查看课堂是否存在
     * @param classcourseid
     * @param classcode
     * @param coursecode
     * @return
     */
    ServiceResult<ResponseMsg> check(Integer classcourseid, String classcode, String coursecode);

    /**
     * 根据学生的code获取已经选的课程
     * @param studentCode
     * @return
     */
    ServiceResult<List<ClassCourseDTO>> getSelectedClassRoom(String studentCode);

    /**
     * 根据学生的code获取已经选的课程
     * @param studentCode
     * @return
     */
    ServiceResult<List<ClassCourseDTO>> getUnSelectedClassRoom(String studentCode);

    /**
     * 学生加入课堂
     * @param studentCode
     * @param classCourseId
     * @return
     */
    ServiceResult<ResponseMsg> joinClassRoom(String studentCode, Integer classCourseId);

    /**
     * 获取该教师创建的课堂
     * @param teacherCode
     * @return
     */
    ServiceResult<List<ClassCourseVo>> getClassCourseInfo(String teacherCode);
}
