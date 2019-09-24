package com.iclass.ppt_hw.component.service.impl;

import com.iclass.mybatis.dao.*;
import com.iclass.mybatis.dto.ClasshdDTO;
import com.iclass.mybatis.po.Class;
import com.iclass.mybatis.po.*;
import com.iclass.mybatis.vo.ChartClasshdData;
import com.iclass.mybatis.vo.ChartClasshdSeriesData;
import com.iclass.mybatis.vo.ChartMetaData;
import com.iclass.mybatis.vo.ClasshdVo;
import com.iclass.ppt_hw.component.service.api.ClasshdService;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.Msg;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.utils.CheckDataTables;
import com.iclass.user.component.utils.IclassUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 4/14/2017 4:06 PM.
 */
@Service("classhdService")
public class ClasshdServiceImpl implements ClasshdService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ClasshdMapper classhdMapper;

    @Autowired
    private ClassCourseMapper classCourseMapper;

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ClassCourseStudentMapper classCourseStudentMapper;

    @Autowired
    private ClasshdStudentMapper classhdStudentMapper;

    @Override
    public ServiceResult<List<ClasshdDTO>> getAll(DataTablesRequestEntity requestEntity, String userCode, Boolean isLimit, Integer classCourseIdParam) {
        ServiceResult<List<ClasshdDTO>> serviceResult = new ServiceResult<>();
        if (StringUtils.isBlank(userCode)) {
            serviceResult.setMessage("用户未登录");
            return serviceResult;
        }

        User user = userMapper.findByUsercode(userCode);
        if (user == null) {
            serviceResult.setMessage("您好像还不是我们系统的用户,快去注册吧");
            return serviceResult;
        }
        requestEntity = CheckDataTables.check(requestEntity);
        Integer draw = requestEntity.getDraw();
        Integer start = requestEntity.getStart();
        Integer length = requestEntity.getLength();
        String teacherName = "";
        //1.获取课堂互动数据
        List<Classhd> classhds = null;
        if (user.getUserrole().equals("教师")) {
            teacherName = user.getUserfullname();
            if (isLimit) {
                classhds = classhdMapper.selectByClasshdCreator(userCode, start, length);
            } else {
                classhds = classhdMapper.selectByClasshdCreatorNolimit(userCode);
            }
        } else if (user.getUserrole().equals("学生")) {
            if (isLimit) {
                classhds = classhdMapper.selectByStudentCode(userCode, start, length);
            } else {
                classhds = classhdMapper.selectByStudentCodeNolimit(userCode);
            }
        }
        if (classhds == null) {
            serviceResult.setMessage("没有找到课堂互动内容");
            serviceResult.setRecordsTotal(0);
            serviceResult.setRecordsFiltered(0);
            return serviceResult;
        }
        List<ClasshdDTO> classhdDTOList = new ArrayList<>();
        //2.获取课堂数据
        for (Classhd classhd : classhds) {
            if (classCourseIdParam != null && classCourseIdParam != 0 && !classCourseIdParam.equals(classhd.getClasscourseid())) {
                continue;
            }
            // 统计课堂的学生数量
            Integer studentNum = classCourseStudentMapper.countByClassCourseId(classhd.getClasscourseid());
            classhdDTOList.add(doData(classhd, classhd.getClasscourseid(), teacherName, studentNum));
        }
        Integer count = classhdMapper.countByClasshdCreator(userCode);
        serviceResult.setDraw(draw);
        serviceResult.setData(classhdDTOList);
        //如果没有过滤
        if (classCourseIdParam != null && classCourseIdParam == 0) {
            serviceResult.setRecordsFiltered(count);
        } else {
            serviceResult.setRecordsFiltered(classhdDTOList.size());
        }
        serviceResult.setRecordsTotal(count);
        serviceResult.setSuccess(true);
        return serviceResult;
    }

    @Override
    public ServiceResult<ResponseMsg> checkAnswer(Integer classhdId, String studentCode, String answer) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        if (classhdId == null) {
            serviceResult.setMessage("课堂互动编号不能为空");
            return serviceResult;
        }
        if (StringUtils.isBlank(studentCode)) {
            serviceResult.setMessage("未登录");
            return serviceResult;
        }
        User student = userMapper.findByUsercode(studentCode);
        if (student == null) {
            serviceResult.setMessage("您还不是我们系统的用户,快去注册吧");
            return serviceResult;
        }
        if (StringUtils.isBlank(answer)) {
            serviceResult.setMessage("答案不能为空");
            return serviceResult;
        }
        Classhd classhd = classhdMapper.selectByPrimaryKey(classhdId);
        if (classhd.getClasshdstatus() == 0) {
            serviceResult.setMessage("作答失败,该题目已关闭");
            return serviceResult;
        }
        ClasshdStudent oldClasshdStudent = classhdStudentMapper.selectByClasshdIdAndStudentCode(classhdId, studentCode);
        // 如果不存在作答记录
        if (oldClasshdStudent == null) {
            // 新建作答记录,默认做错
            ClasshdStudent classhdStudent = new ClasshdStudent(null, classhdId, studentCode, 0);
            // 不存在作答记录
            if (checkAnswer(classhd, answer)) {
                // 作对
                classhdStudent.setResult(1);
                serviceResult.setSuccess(true);
                serviceResult.setData(new ResponseMsg(Msg.OK));
                classhdMapper.updateRightNumber(classhdId);
            } else {
                serviceResult.setMessage("答案错误");
                return serviceResult;
            }
            // 插入作答数据
            classhdStudentMapper.insert(classhdStudent);
            return serviceResult;
        }
        // 如果已经存在了该学生的答题记录
        // 已经回答正确了
        if (oldClasshdStudent.getResult().equals(1)) {
            serviceResult.setMessage("已作正确,请勿重复作答");
            return serviceResult;
        }
        // 如果做错了,就允许修改答案
        if (!checkAnswer(classhd, answer)) {
            serviceResult.setMessage("答案错误");
            return serviceResult;
        }
        // 做对
        oldClasshdStudent.setResult(1);
        // 更新
        classhdStudentMapper.updateByPrimaryKey(oldClasshdStudent);
        classhdMapper.updateRightNumber(classhdId);
        serviceResult.setSuccess(true);
        serviceResult.setData(new ResponseMsg(Msg.OK));
        return serviceResult;
    }

    @Override
    public ServiceResult<ResponseMsg> save(Classhd classhd, String classCode, String courseCode) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        if (StringUtils.isBlank(classCode)) {
            serviceResult.setMessage("班级编号不能为空");
            return serviceResult;
        }
        if (StringUtils.isBlank(courseCode)) {
            serviceResult.setMessage("课程编号不能为空");
            return serviceResult;
        }
        ClassCourse classCourse = classCourseMapper.selectByClassCodeAndCourseCode(classCode, courseCode);
        classhd.setClasscourseid(classCourse.getClasscourseid());
        classhd.setClasshdcreatetime(IclassUtil.getDateTimeNow());
        classhd.setRightnumber(0);
        classhd.setClasshdstatus(1);
        classhdMapper.insert(classhd);
        serviceResult.setSuccess(true);
        serviceResult.setData(new ResponseMsg(Msg.OK));
        return serviceResult;
    }

    @Override
    public ServiceResult<ResponseMsg> del(Integer classhdid) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        if (classhdid == null) {
            serviceResult.setMessage("课堂编号不能为空");
            return serviceResult;
        }
        classhdMapper.deleteByPrimaryKey(classhdid);
        serviceResult.setSuccess(true);
        serviceResult.setData(new ResponseMsg(Msg.OK));
        return serviceResult;
    }

    @Override
    public ServiceResult<ResponseMsg> update(Classhd classhd) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        classhdMapper.updateByPrimaryKeySelective(classhd);
        serviceResult.setSuccess(true);
        serviceResult.setData(new ResponseMsg(Msg.OK));
        return serviceResult;
    }

    @Override
    public ServiceResult<ClasshdVo> get(Integer classhdId) {
        ServiceResult<ClasshdVo> serviceResult = new ServiceResult<>();
        if (classhdId == null) {
            serviceResult.setMessage("课堂互动编号不能为空");
            return serviceResult;
        }
        Classhd classhd = classhdMapper.selectByPrimaryKey(classhdId);
        Integer classCourseId = classhd.getClasscourseid();
        ClassCourse classCourse = classCourseMapper.selectByPrimaryKey(classCourseId);
        serviceResult.setSuccess(true);
        serviceResult.setData(new ClasshdVo(classhd, classCourse.getClasscode(), classCourse.getCoursecode()));
        return serviceResult;
    }

    @Override
    public ServiceResult<ResponseMsg> updateStatus(Classhd classhd, int status) {
        return null;
    }

    /**
     * 获取图表数据
     * @param classCreator
     * @return
     */
    @Override
    public ServiceResult<List<ChartClasshdData>> getChartData(String classCreator) {
        ServiceResult<List<ChartClasshdData>> serviceResult = new ServiceResult<>();
        if (StringUtils.isBlank(classCreator)) {
            serviceResult.setMessage("未登录");
            return serviceResult;
        }
        // 获取教师所创建的课堂, 不分页
        List<ClassCourse> classCourseList = classCourseMapper.selectByClassCreatorNoLimit(classCreator);
        if (classCourseList == null || classCourseList.size() == 0) {
            serviceResult.setMessage("该教师还未创建课堂");
            return serviceResult;
        }
        // 最后要返回的图表数据
        List<ChartClasshdData> chartClasshdDataList = new ArrayList<>();
        // 课堂数据有的话
        for (ClassCourse cc : classCourseList) {
            String classCode = cc.getClasscode();
            String courseCode = cc.getCoursecode();
            // 获取课堂名
            String classRoomName = getClassRoomName(classCode, courseCode);

            // 获取课堂的人数
            Integer studentTotalOfTheClass = classCourseStudentMapper.countByClassCourseId(cc.getClasscourseid());

            // 获取该课堂的所有题目
            List<Classhd> classhdList = classhdMapper.selectByClassCourseId(cc.getClasscourseid());

            // 该课堂答题正确的人的总数
            Integer answeredStudentsOfTheClassCourse = 0;

            // 没有互动数据
            if (classhdList == null || classhdList.size() == 0) {
                continue;
            }

            List<ChartMetaData> chartMetaDataList = new ArrayList<>();
            for (Classhd classhd : classhdList) {
                // 累计答题正确的总人数
                answeredStudentsOfTheClassCourse += classhd.getRightnumber();
                // 互动的题目的标题： ChartMetaData.name
                String title = classhd.getClasshdcontent();

                // 单个题目的正确率
                // 作对题目的人数 / 该课堂的人数
                Double rightRate = 0.0;
                if (studentTotalOfTheClass != 0) {
                    rightRate = (double) classhd.getRightnumber() / (double) studentTotalOfTheClass;
                }
                chartMetaDataList.add(new ChartMetaData(title, rightRate));
            }

            // 详情数据的id
            String drilldown = classRoomName;

            ChartClasshdSeriesData chartClasshdSeriesData = new ChartClasshdSeriesData(classRoomName, classRoomName, chartMetaDataList);

            chartClasshdDataList.add(new ChartClasshdData(classRoomName, answeredStudentsOfTheClassCourse, drilldown, chartClasshdSeriesData));
        }

        serviceResult.setData(chartClasshdDataList);
        serviceResult.setSuccess(true);
        return serviceResult;
    }

    /**
     * 答案匹配，目前是做到全词匹配
     * @param classhd
     * @param answer
     * @return
     */
    private Boolean checkAnswer(Classhd classhd, String answer) {
        return classhd.getClasshdanswer().equalsIgnoreCase(answer);
    }

    /**
     *  处理数据
     */
    public ClasshdDTO doData(Classhd classhd, Integer classcourseId, String teacherName, Integer studentNum) {
        //2.1根据课堂id获取课堂数据
        ClassCourse classRoom = classCourseMapper.selectByPrimaryKey(classcourseId);
        String classCode = classRoom.getClasscode();
        //2.2获取班级信息
        Class c = classMapper.selectByClassCode(classCode);
        String className = c.getClassname();
        //2.3获取课堂信息
        String courseCode = classRoom.getCoursecode();
        Course course = courseMapper.selectByCourseCode(courseCode);
        String courseName = course.getCoursename();
        //2.4生成课堂名字
        String classRoomName = className + ":" + courseName ;
        return new ClasshdDTO(classhd, classRoom, classRoomName, teacherName, studentNum);
    }

    /**
     * 获取课堂数据
     * @param classCode
     * @param courseCode
     * @return
     */
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
}
