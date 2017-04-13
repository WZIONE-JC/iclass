package com.iclass.ppt_hw.component.service.impl;

import com.iclass.mybatis.dao.*;
import com.iclass.mybatis.dto.ClassCourseDTO;
import com.iclass.mybatis.dto.SessionUser;
import com.iclass.mybatis.po.Class;
import com.iclass.mybatis.po.*;
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
    public ServiceResult<List<ClassCourseDTO>> getClassCourse(DataTablesRequestEntity requestEntity, String classCreator, Boolean isLimit) {
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

        //1.根据classCreator去查询该教师所创建的班级
        List<Class> classes;

        if (isLimit) {
            classes = classMapper.selectByClassCreator(classCreator, start, length);
        } else {
            classes = classMapper.selectByClassCreatorNoLimit(classCreator);
        }
        User user = userMapper.findUserByUsercode(classCreator);
        String teacherName = user.getUserfullname();
        String role = user.getUserrole();
        if(classes != null && classes.size() > 0) {
            for(Class c : classes) {
                //2.根据classCode去查询class_course表,查询courseCode
                String classCode = c.getClasscode();
                List<ClassCourse> classCourseList = classCourseMapper.selectByClassCode(classCode);
                for (ClassCourse classCourse : classCourseList) {
                    Integer classcourseId = classCourse.getClasscourseid();
                    String createTime = classCourse.getCreatetime();
                    String deadline = classCourse.getDeadline();
                    Integer status = classCourse.getStatus();
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
                            User student = userMapper.findUserByUsercode(studentCode);
                            sessionUsers.add(new SessionUser(student));
                        }
                    }
                    String classRoomName = getClassRoomName2(c.getClassname(), course.getCoursename());
                    classCourseDTOs.add(new ClassCourseDTO(classcourseId, classRoomName, c, course, teacherName, sessionUsers, createTime, deadline, status));
                }
            }
            serviceResult.setSuccess(true);
            serviceResult.setData(classCourseDTOs);
            serviceResult.setDraw(draw);
            serviceResult.setRecordsTotal(classCourseDTOs.size());
            serviceResult.setRecordsFiltered(classCourseDTOs.size());
        } else {
            logger.info("编号为:" + classCreator +"的"+ role +",没有创建课堂");
            serviceResult.setMessage("编号为:" + classCreator +"的"+ role +",没有创建课堂");
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

    @Override
    public ServiceResult<ResponseMsg> check(Integer classcourseId, String classCode, String courseCode) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        if (classcourseId == null) {
            serviceResult.setMessage("课堂id不能为空");
            return serviceResult;
        }
        if (StringUtils.isBlank(classCode)) {
            serviceResult.setMessage("班级编号不能为空");
            return serviceResult;
        }
        if (StringUtils.isBlank(courseCode)) {
            serviceResult.setMessage("课程编号不能为空");
            return serviceResult;
        }
        ClassCourse classCourse = classCourseMapper.selectByClassCodeAndCourseCode(classCode, courseCode);
        //传入的id和查询出来的id不一致,才是修改了 classCode 和 courseCode
        if (classCourse != null) {
            Integer id = classCourse.getClasscourseid();
            if (!id.equals(classcourseId)) {
                serviceResult.setMessage("该课堂"+getClassRoomName(classCode, courseCode)+"已经存在");
                return serviceResult;
            }
        }
        serviceResult.setData(new ResponseMsg(Msg.OK));
        serviceResult.setSuccess(true);
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ClassCourseDTO>> getSelectedClassRoom(String studentCode) {
        return  getClassRoom(studentCode, true);
    }

    @Override
    public ServiceResult<List<ClassCourseDTO>> getUnSelectedClassRoom(String studentCode) {
        return  getClassRoom(studentCode, false);
    }

    @Override
    public ServiceResult<ResponseMsg> joinClassRoom(String studentCode, Integer classCourseId) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        if (StringUtils.isBlank(studentCode)) {
            serviceResult.setMessage("学生编号不能为空");
            return serviceResult;
        }
        if (classCourseId == null) {
            serviceResult.setMessage("课堂编号不能为空");
            return serviceResult;
        }
        User student = userMapper.findByUsercode(studentCode);
        if (student == null) {
            serviceResult.setMessage("不存在该学生的信息哦");
            return serviceResult;
        }
        ClassCourse classCourse = classCourseMapper.selectByPrimaryKey(classCourseId);
        if (classCourse == null) {
            serviceResult.setMessage("不存在该课堂的信息");
            return serviceResult;
        }
        if (classCourse.getStatus() == 0) {
            serviceResult.setMessage("加入课堂失败,该课堂已下架");
            return serviceResult;
        }
        ClassCourseStudent ccs = classCourseStudentMapper.selectByClassCourseIdAndStudentCode(classCourseId, studentCode);
        if (ccs != null) {
            serviceResult.setMessage("该学生已经加入了该课堂");
            return serviceResult;
        }
        ClassCourseStudent classCourseStudent = new ClassCourseStudent(null, classCourseId, studentCode);
        classCourseStudentMapper.insert(classCourseStudent);
        serviceResult.setSuccess(true);
        serviceResult.setData(new ResponseMsg(Msg.OK));
        return serviceResult;
    }

    /**
     * 学生获取课堂的方法
     * @param studentCode
     * @param isSelected true,表示已选的课堂   false,表示未选的课堂
     * @return
     */
    private ServiceResult<List<ClassCourseDTO>> getClassRoom(String studentCode, Boolean isSelected) {
        ServiceResult<List<ClassCourseDTO>> serviceResult = new ServiceResult<>();
        if (StringUtils.isBlank(studentCode)) {
            serviceResult.setMessage("用户未登录");
            return serviceResult;
        }
        List<ClassCourseDTO> classCourseDTOList = new ArrayList<>();
        User student = userMapper.findByUsercode(studentCode);
        if (student == null) {
            serviceResult.setMessage("您好像还不属于我们系统的用户呢");
            return serviceResult;
        }
        String userfullname = student.getUserfullname();
        String role = student.getUserrole();
        List<ClassCourseStudent> classCourseStudents = null;
        if (isSelected) {
            //1.通过学生编号获取学生所选的课堂
            classCourseStudents = classCourseStudentMapper.selectByStudentCode(studentCode);
            if (classCourseStudents.size() == 0) {
                serviceResult.setMessage(userfullname + " " + role + ",好像还木有加入课堂诶");
                return serviceResult;
            }
        } else {
            //1.通过学生编号获取学生所选的课堂
            classCourseStudents = classCourseStudentMapper.selectByNotEqualsStudentCode(studentCode);
            if (classCourseStudents.size() == 0) {
                serviceResult.setMessage(userfullname + " " + role + ",已经加入了全部的课堂了诶");
                return serviceResult;
            }
        }
        for (ClassCourseStudent classCourseStudent : classCourseStudents) {
            Integer classcourseId = classCourseStudent.getClasscourseid();
            //2.根据classCourseId去获取课堂的信息
            ClassCourse classCourse = classCourseMapper.selectByPrimaryKey(classcourseId);
            //3.只加入启用的课程
            if(classCourse != null && classCourse.getStatus() == 1) {
                String createTime = classCourse.getCreatetime();
                String deadLine = classCourse.getDeadline();
                //3.根据classCode获取班级信息
                String classCode = classCourse.getClasscode();
                Class c = classMapper.selectByClassCode(classCode);
                String teacherCode = c.getClasscreator();
                User teacher = userMapper.findByUsercode(teacherCode);
                String teacherName = teacher.getUserfullname();
                //5.根据courseCode获取课程信息
                String courseCode = classCourse.getCoursecode();
                Course course = courseMapper.selectByCourseCode(courseCode);
                String classRoomName = getClassRoomName2(c.getClassname(), course.getCoursename());
                //6.根据classcourseId去查询这个课堂有多少学生选了
                List<ClassCourseStudent> classCourseStudentList = classCourseStudentMapper.selectByClassCourseId(classcourseId);
                List<SessionUser> students = new ArrayList<>();
                for (ClassCourseStudent ccs : classCourseStudentList) {
                    String studentCode2 = ccs.getStudentcode();
                    User student2 = userMapper.findByUsercode(studentCode2);
                    students.add(new SessionUser(student2));
                }
                //状态默认为1
                classCourseDTOList.add(new ClassCourseDTO(classcourseId, classRoomName, c, course, teacherName, students, createTime, deadLine, 1));
            }
        }
        serviceResult.setData(classCourseDTOList);
        serviceResult.setSuccess(true);
        return serviceResult;
    }

    private String getClassRoomName(String classCode, String courseCode) {
        StringBuilder result = new StringBuilder();
        Class c = classMapper.selectByClassCode(classCode);
        Course course = courseMapper.selectByCourseCode(courseCode);
        if (c != null) {
            result.append("[").append(c.getClassname());
        }
        if (course != null) {
            result.append(":").append(course.getCoursename()).append("]");
        }
        return result.toString();
    }

    private String getClassRoomName2(String className, String courseName) {
        StringBuilder result = new StringBuilder("[");
        result.append(className).append(":").append(courseName).append("]");
        return result.toString();
    }
}
