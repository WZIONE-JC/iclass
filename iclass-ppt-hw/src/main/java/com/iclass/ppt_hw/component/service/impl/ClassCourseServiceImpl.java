package com.iclass.ppt_hw.component.service.impl;

import com.iclass.mybatis.dao.*;
import com.iclass.mybatis.dto.ClassCourseDTO;
import com.iclass.mybatis.dto.SessionUser;
import com.iclass.mybatis.po.Class;
import com.iclass.mybatis.po.*;
import com.iclass.mybatis.vo.ClassCourseVo;
import com.iclass.ppt_hw.component.service.api.ClassCourseService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 查询课堂的上课时间
     */
    @Autowired
    private ClasscourseattendtimeMapper attendtimeMapper;

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

        //1.根据classCreator去查询该教师所创建的课堂
        List<ClassCourse> classCourseList;

        if (isLimit) {
            classCourseList = classCourseMapper.selectByClassCreator(classCreator, start, length);
        } else {
            classCourseList = classCourseMapper.selectByClassCreatorNoLimit(classCreator);
        }
        Integer total = classCourseMapper.countByClassCreator(classCreator);

        User user = userMapper.findUserByUsercode(classCreator);
        String teacherName = user.getUserfullname();
        String role = user.getUserrole();
        if (classCourseList == null || classCourseList.size() == 0) {
            logger.info("编号为:" + classCreator +"的"+ role +",没有创建课堂");
            serviceResult.setMessage("编号为:" + classCreator +"的"+ role +",没有创建课堂");
            serviceResult.setRecordsTotal(0);
            serviceResult.setRecordsFiltered(0);
            return serviceResult;
        }
        for (ClassCourse classCourse : classCourseList) {
            String classCode = classCourse.getClasscode();
            Class c = classMapper.selectByClassCode(classCode);
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
            //4.1获取课堂上课时间
            List<Classcourseattendtime> classcourseattendtimeList = attendtimeMapper.selectByClassCourseId(classCourseId);
            /**
             * map存放上课时间的数据
             * key: 周几     ccat.getAttendtime()
             * value: 1,2,3,4(第几节课)  ccat.getAttendnumber()
             */
            Map<Integer, String> data = new HashMap<>();
            for(Classcourseattendtime ccat : classcourseattendtimeList) {
                // 如果存在这天的记录,就组合
                if (data.containsKey(ccat.getAttendtime())) {
                    String val = data.get(ccat.getAttendtime());
                    val += "," + ccat.getAttendnumber();
                    data.put(ccat.getAttendtime(), val);
                } else {
                    data.put(ccat.getAttendtime(), ccat.getAttendnumber()+"");
                }
            }
            String attendTime = IclassUtil.getAttendTime(data);
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
            classCourseDTOs.add(new ClassCourseDTO(classcourseId, classRoomName, c, course, teacherName, sessionUsers, attendTime, createTime, deadline, status, null));
        }
        serviceResult.setSuccess(true);
        serviceResult.setData(classCourseDTOs);
        serviceResult.setDraw(draw);
        serviceResult.setRecordsTotal(total);
        serviceResult.setRecordsFiltered(total);
        return serviceResult;
    }

    @Override
    @Transactional
    public ServiceResult<ResponseMsg> save(ClassCourse classCourse, Integer attendnumber, Integer attendtime) {
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
        logger.info("生成的课堂主键为:{}" ,classCourse.getClasscourseid());
        Classcourseattendtime classcourseattendtime = new Classcourseattendtime();
        classcourseattendtime.setAttendnumber(attendnumber);
        classcourseattendtime.setAttendtime(attendtime);
        classcourseattendtime.setClasscourseid(classCourse.getClasscourseid());
        attendtimeMapper.insert(classcourseattendtime);
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
            serviceResult.setMessage("加入课堂失败,该课堂已关闭");
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
     * 获取该教师所创建的所有课堂
     *
     * @param teacherCode
     * @return
     */
    @Override
    public ServiceResult<List<ClassCourseVo>> getClassCourseInfo(String teacherCode) {
        ServiceResult<List<ClassCourseVo>> serviceResult = new ServiceResult<>();
        if (StringUtils.isBlank(teacherCode)) {
            serviceResult.setMessage("未登录");
            return serviceResult;
        }
        List<ClassCourse> classCourseList = classCourseMapper.selectByClassCreatorNoLimit(teacherCode);
        if (classCourseList == null && classCourseList.size() == 0) {
            serviceResult.setMessage("该教师还未创建课堂");
            return serviceResult;
        }
        List<ClassCourseVo> classCourseVoList = new ArrayList<>();
        for (ClassCourse cc : classCourseList) {
            ClassCourseVo classCourseVo = new ClassCourseVo(getClassRoomName(cc.getClasscode(), cc.getCoursecode()), cc.getClasscourseid());
            classCourseVoList.add(classCourseVo);
        }
        serviceResult.setSuccess(true);
        serviceResult.setData(classCourseVoList);
        return serviceResult;
    }

    /**
     * app端
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
        List<ClassCourse> classCourses = null;
        // 图片下标
        int index = 0;
        if (isSelected) {
            //1.通过学生编号获取学生所选的课堂
            classCourseStudents = classCourseStudentMapper.selectByStudentCode(studentCode);
            if (classCourseStudents.size() == 0) {
                serviceResult.setMessage(userfullname + " " + role + ",好像还木有加入课堂诶");
                return serviceResult;
            }
            for (ClassCourseStudent classCourseStudent : classCourseStudents) {
                classCourseDTOList = doData(classCourseDTOList, classCourseStudent.getClasscourseid(), index ++);
            }
        } else {
            //1.通过学生编号获取学生未选的课堂
            classCourses = classCourseMapper.selectUnselectedClassCourse(studentCode);
            if (classCourses.size() == 0) {
                serviceResult.setMessage(userfullname + " " + role + ",已经加入了全部的课堂了诶");
                return serviceResult;
            }
            for (ClassCourse cc : classCourses) {
                classCourseDTOList = doData(classCourseDTOList, cc.getClasscourseid(), index ++);
            }
        }
        serviceResult.setData(classCourseDTOList);
        serviceResult.setSuccess(true);
        return serviceResult;
    }

    /**
     * 处理选课数据
     * @param classCourseDTOList
     * @param classcourseId
     * @return
     */
    private List<ClassCourseDTO> doData(List<ClassCourseDTO> classCourseDTOList, Integer classcourseId, int index) {
        //2.根据classCourseId去获取课堂的信息
        ClassCourse classCourse = classCourseMapper.selectByPrimaryKey(classcourseId);

        //2.1获取课堂上课时间
        List<Classcourseattendtime> classcourseattendtimeList = attendtimeMapper.selectByClassCourseId(classcourseId);
        /**
         * map存放上课时间的数据
         * key: 周几     ccat.getAttendtime()
         * value: 1,2,3,4(第几节课)  ccat.getAttendnumber()
         */
        Map<Integer, String> data = new HashMap<>();
        for(Classcourseattendtime ccat : classcourseattendtimeList) {
            // 如果存在这天的记录,就组合
            if (data.containsKey(ccat.getAttendtime())) {
                String val = data.get(ccat.getAttendtime());
                val += "," + ccat.getAttendnumber();
                data.put(ccat.getAttendtime(), val);
            } else {
                data.put(ccat.getAttendtime(), ccat.getAttendnumber()+"");
            }
        }
        String attendTime = IclassUtil.getAttendTime(data);

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
            String url = getImgUrl(10, index);
            //状态默认为1
            classCourseDTOList.add(new ClassCourseDTO(classcourseId, classRoomName, c, course, teacherName, students, attendTime, createTime, deadLine, 1, url));
        }
        return classCourseDTOList;
    }

    private String getClassRoomName(String classCode, String courseCode) {
        StringBuilder result = new StringBuilder();
        Class c = classMapper.selectByClassCode(classCode);
        Course course = courseMapper.selectByCourseCode(courseCode);
        if (c != null) {
            result.append(c.getClassname());
        }
        if (course != null) {
            result.append(":").append(course.getCoursename());
        }
        return result.toString();
    }

    private String getClassRoomName2(String className, String courseName) {
        StringBuilder result = new StringBuilder();
        result.append(className).append(":").append(courseName);
        return result.toString();
    }

    /**
     * app端
     * 获取一张班级的照片
     * @param cap 照片容量
     * @param index 获取下表
     * @return
     */
    private String getImgUrl(int cap, int index) {
        String[] imgurls = new String[cap];
        for(int i = 0; i < 10; i ++) {
            imgurls[i] = "http://www.aiiclass.cn/files/classimgs/" + (i + 1) + ".jpg";
        }
        if (index < cap) {
            return imgurls[index];
        }
        return null;
    }
}
