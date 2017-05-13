package com.iclass.ppt_hw.component.service.impl;

import com.iclass.mybatis.dao.*;
import com.iclass.mybatis.dto.ClasshdDTO;
import com.iclass.mybatis.po.Class;
import com.iclass.mybatis.po.*;
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

    @Override
    public ServiceResult<List<ClasshdDTO>> getAll(DataTablesRequestEntity requestEntity, String userCode, Boolean isLimit) {
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
                classhds = classhdMapper.selectByClasshdCreatorNolimit(userCode);
            }
        }

        List<ClasshdDTO> classhdDTOList = new ArrayList<>();
        //2.获取课堂数据
        for (Classhd classhd : classhds) {
            classhdDTOList.add(doData(classhd, classhd.getClasscourseid(), teacherName));
        }
        Integer count = classhdMapper.countByClasshdCreator(userCode);
        serviceResult.setDraw(draw);
        serviceResult.setData(classhdDTOList);
        serviceResult.setRecordsFiltered(classhdDTOList.size());
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
            serviceResult.setMessage("作答失败,该题目已下架");
            return serviceResult;
        }
        if (checkAnswer(classhd, answer)) {
            serviceResult.setSuccess(true);
            serviceResult.setData(new ResponseMsg(Msg.OK));
            classhdMapper.updateRightNumber(classhdId);
        } else {
            serviceResult.setMessage("答案错误");
            return serviceResult;
        }
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
    public ClasshdDTO doData(Classhd classhd, Integer classcourseId, String teacherName) {
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
        return new ClasshdDTO(classhd, classRoom, classRoomName, teacherName);
    }
}
