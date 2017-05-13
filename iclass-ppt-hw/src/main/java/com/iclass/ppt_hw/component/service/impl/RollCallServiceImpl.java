package com.iclass.ppt_hw.component.service.impl;

import com.iclass.mybatis.dao.*;
import com.iclass.mybatis.dto.RollCallDTO;
import com.iclass.mybatis.dto.SessionUser;
import com.iclass.mybatis.po.Class;
import com.iclass.mybatis.po.*;
import com.iclass.mybatis.vo.RollcallVo;
import com.iclass.ppt_hw.component.service.api.RollCallService;
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

import java.util.*;

/**
 * Created by radishmaster on 15/04/17.
 *
 */
@Service("rollCallService")
public class RollCallServiceImpl implements RollCallService{

    private final Logger logger = LoggerFactory.getLogger(RollCallServiceImpl.class);

    private final String TIMEOUT = "timeout";

    /**
     * 字典
     */
    private final String[] worldtable = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    ,"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    /**
     * 生成口令的长度
     */
    private final Integer count = 5;

    /**
     * 默认时间
     */
    private final Integer defaultTime = 60;

    /**
     * 生成的口令
     */
    private Map<Integer, String> word = new HashMap<>();

    /**
     * 计时器
     */
    private Map<Integer, Long> timer = new HashMap<>();

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RollcallMapper rollcallMapper;

    @Autowired
    private ClassCourseMapper classCourseMapper;

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public ServiceResult<List<RollCallDTO>> getAll(DataTablesRequestEntity requestEntity, String teacherCode) {
        ServiceResult<List<RollCallDTO>> serviceResult = new ServiceResult<>();
        if (StringUtils.isBlank(teacherCode)) {
            serviceResult.setMessage("未登录");
            return serviceResult;
        }
        requestEntity = CheckDataTables.check(requestEntity);
        Integer draw = requestEntity.getDraw();
        Integer start = requestEntity.getStart();
        Integer length = requestEntity.getLength();

        //1.获取教师信息
        User teacher = userMapper.findByUsercode(teacherCode);
        String teacherName = teacher.getUserfullname();

        List<Rollcall> rollcalls = rollcallMapper.selectByTeacherCode(teacherCode, start, length);

        List<RollCallDTO> rollCallDTOList = new ArrayList<>();
        for (Rollcall rollcall : rollcalls) {
            //2.获取课堂id
            Integer classcourseId = rollcall.getClasscourseid();
            ClassCourse classCourse = classCourseMapper.selectByPrimaryKey(classcourseId);
            //3.获取班级信息
            String classCode = classCourse.getClasscode();
            Class c = classMapper.selectByClassCode(classCode);
            String className = c.getClassname();
            //4.获取课程信息
            String courseCode = classCourse.getCoursecode();
            Course course = courseMapper.selectByCourseCode(courseCode);
            String courseName = course.getCoursename();
            String classRoomName = className + ":" + courseName;
            //5.获取学生信息
            String studentCode = rollcall.getStudentcode();
            User student = userMapper.findByUsercode(studentCode);
            rollCallDTOList.add(new RollCallDTO(rollcall, teacherName, classRoomName, new SessionUser(student)));
        }
        serviceResult.setData(rollCallDTOList);
        serviceResult.setSuccess(true);
        serviceResult.setDraw(draw);
        serviceResult.setRecordsFiltered(rollCallDTOList.size());
        serviceResult.setRecordsTotal(rollCallDTOList.size());
        return serviceResult;
    }

    @Override
    public ServiceResult<ResponseMsg> del(Integer rollcallId) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        if (rollcallId == null) {
            serviceResult.setMessage("记录编号不能为空");
            return serviceResult;
        }
        rollcallMapper.deleteByPrimaryKey(rollcallId);
        serviceResult.setSuccess(true);
        serviceResult.setData(new ResponseMsg(Msg.OK));
        return serviceResult;
    }

    @Override
    public ServiceResult<String> rollcall(String teacherCode, Integer classCourseId, Integer time) {
        ServiceResult<String> serviceResult = new ServiceResult<>();
        if (StringUtils.isBlank(teacherCode)) {
            serviceResult.setMessage("未登录");
            return serviceResult;
        }
        if (classCourseId == null) {
            serviceResult.setMessage("课堂编号不能为空");
            return serviceResult;
        }
        String result = getWord(classCourseId, time);
        logger.info("教师设置的时间为 {}", timer.get(classCourseId));
        // 设置口令
        word.put(classCourseId, result);
        serviceResult.setData(result);
        serviceResult.setSuccess(true);
        return serviceResult;
    }

    @Override
    public ServiceResult<ResponseMsg> signIn(String studentCode, Integer classCourseId, String content) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        if (StringUtils.isBlank(studentCode)) {
            serviceResult.setMessage("未登录");
            return serviceResult;
        }
        if (classCourseId == null) {
            serviceResult.setMessage("课堂编号不能为空");
            return serviceResult;
        }
        if (word.get(classCourseId) == null) {
            serviceResult.setMessage("点名还没开始");
            return serviceResult;
        }
        Rollcall rollcall;
        int status;
        if (word.get(classCourseId).equals(content) || word.get(classCourseId).equals(TIMEOUT)) {
            //如果超时
            Long currentTime = System.currentTimeMillis();
            Long endtime = timer.get(classCourseId);
            if (currentTime > endtime || word.get(classCourseId).equals(TIMEOUT)) {
                word.put(classCourseId, TIMEOUT);
                status = 0;
            } else {
                status = 1;
            }
            rollcall = setValue(classCourseId, studentCode, status);
            rollcallMapper.insert(rollcall);

            serviceResult.setSuccess(true);
            serviceResult.setData(new ResponseMsg(Msg.OK));
        } else {
            serviceResult.setMessage("口令错误");
        }
        return serviceResult;
    }

    /**
     * 获取当前课堂的点名情况
     * @param classCourseId
     * @return
     */
    @Override
    public ServiceResult<RollcallVo> show(Integer classCourseId) {
        ServiceResult<RollcallVo> serviceResult = new ServiceResult<>();
        //1.根据课堂Id获取当前课堂的学生信息
        if (classCourseId == null) {
            serviceResult.setMessage("课堂编号不能为空");
            return serviceResult;
        }
//        List<Rollcall> rollcalls = rollcallMapper.selectByClassCourseId(classCourseId,);
//        for (Rollcall rollcall : rollcalls) {

//        }
        return null;
    }

    /**
     * 设置数据
     * @param classCourseId
     * @param studentCode
     * @param status
     * @return
     */
    private Rollcall setValue(Integer classCourseId, String studentCode, int status) {
        ClassCourse classCourse = classCourseMapper.selectByPrimaryKey(classCourseId);
        String classCode = classCourse.getClasscode();
        Class c = classMapper.selectByClassCode(classCode);
        String teacherCode = c.getClasscreator();
        Rollcall rollcall = new Rollcall();
        rollcall.setStudentcode(studentCode);
        rollcall.setTeachercode(teacherCode);
        rollcall.setRollcalltime(IclassUtil.getDateTimeNow());
        rollcall.setClasscourseid(classCourseId);
        rollcall.setRollcallstatus(status);
        return rollcall;
    }
    /**
     * 获得口令,并设置时间,默认为defaultTime
     * @param time
     * @return
     */
    private String getWord(Integer classCourseId, Integer time) {
        StringBuilder result = new StringBuilder();
        Random randomWord = new Random();
        if (time == null) {
            time = defaultTime;
        }
        timer.put(classCourseId, System.currentTimeMillis() + time * 1000);
        for (int i = 0; i < count; i ++) {
            Integer wordIndex = randomWord.nextInt(worldtable.length);
            result.append(worldtable[wordIndex]);
        }
        return result.toString();
    }
}
