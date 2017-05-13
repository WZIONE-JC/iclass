package com.iclass.ppt_hw.component.service.impl;

import com.iclass.mybatis.dao.*;
import com.iclass.mybatis.dto.FeedBackDTO;
import com.iclass.mybatis.po.Class;
import com.iclass.mybatis.po.*;
import com.iclass.ppt_hw.component.service.api.FeedBackService;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.Msg;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.utils.CheckDataTables;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 4/26/2017 10:25 PM.
 */
@Service("feedBackService")
public class FeedBackServiceImpl implements FeedBackService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ClassCourseMapper classCourseMapper;

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private IclassfileMapper fileMapper;

    @Autowired
    private FeedbackMapper feedbackMapper;

    /**
     *
     * @param requestEntity
     * @param userCode
     * @param parentId 获取标题内容时,传0,获取详情时,传具体的id
     * @param isLimit app端不需要分页
     * @return
     */
    @Override
    public ServiceResult<List<FeedBackDTO>> getAll(DataTablesRequestEntity requestEntity, String userCode, Integer parentId, Boolean isLimit) {
        ServiceResult<List<FeedBackDTO>> serviceResult = new ServiceResult<>();
        if (StringUtils.isBlank(userCode)) {
            serviceResult.setMessage("用户未登录");
            return serviceResult;
        }
        requestEntity = CheckDataTables.check(requestEntity);
        Integer start = requestEntity.getStart();
        Integer draw = requestEntity.getDraw();
        Integer length = requestEntity.getLength();

        //1. 检查用户身份
        User user = userMapper.findUserByUsercode(userCode);
        Integer isStudent = 0;
        String className = "";
        List<Feedback> feedbackList = null;
        if (user.getUserrole().equals("学生")) {
            //1.1 根据学生编号获取问题
            if (isLimit) {
                feedbackList = feedbackMapper.selectByUserCode(userCode, parentId, start, length);
            } else {
                feedbackList = feedbackMapper.selectByUserCodeNoLimit(userCode, parentId);
            }
            isStudent = 1;
           // 查询出学生所在的班级
        } else if (user.getUserrole().equals("教师")) {
            //1.2 根据教师编号获取问题
            if (isLimit) {
                feedbackList = feedbackMapper.selectByTeacherCode(userCode, parentId, start, length);
            } else {
                feedbackList = feedbackMapper.selectByTeacherCodeNoLimit(userCode, parentId);
            }
        }
        if (feedbackList == null || feedbackList.size() == 0) {
            serviceResult.setMessage("该教师所带的课堂还没有学生来提问哦");
            serviceResult.setRecordsTotal(0);
            serviceResult.setRecordsFiltered(0);
            return serviceResult;
        }
        String classRoomName;

        List<FeedBackDTO> feedBackDTOList = new ArrayList<>();

        for (Feedback fb : feedbackList) {
            //3. 获取课堂编号,并且生成课堂名称
            Integer classCourseId = fb.getClasscourseid();
            ClassCourse classCourse = classCourseMapper.selectByPrimaryKey(classCourseId);
            //3.1 获取班级信息
            Class c = classMapper.selectByClassCode(classCourse.getClasscode());
            //3.2 获取课程信息
            Course course = courseMapper.selectByCourseCode(classCourse.getCoursecode());
            //3.3 生成课堂名称
            classRoomName = c.getClassname() + ":" + course.getCoursename();
            //4. 获取提问者信息
            User student = userMapper.findByUsercode(fb.getFeedbackcode());
            String studentName = student.getUserfullname();
            Iclassfile file = fileMapper.selectByPrimaryKey(fb.getFileid());
            //5. 获取帖子的子贴个数
            Integer feedbackNum = 0;
            if (parentId == 0) {
                feedbackNum = feedbackMapper.countByParentId(fb.getFeedbackid());
            }
            feedBackDTOList.add(new FeedBackDTO(classRoomName, fb, studentName, "", feedbackNum, isStudent, file));
        }
        serviceResult.setData(feedBackDTOList);
        serviceResult.setSuccess(true);
        serviceResult.setDraw(draw);
        serviceResult.setRecordsFiltered(feedBackDTOList.size());
        serviceResult.setRecordsTotal(feedBackDTOList.size());
        return serviceResult;
    }

    /**
     *
     * @param feedback
     * @return
     */
    @Override
    public ServiceResult<ResponseMsg> save(Feedback feedback) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        if (feedback == null) {
            serviceResult.setMessage("提问失败,参数错误");
            return serviceResult;
        }
        Integer classCourseId = feedback.getClasscourseid();
        ClassCourse classCourse = classCourseMapper.selectByPrimaryKey(classCourseId);
        String classCode = classCourse.getClasscode();
        Class c = classMapper.selectByClassCode(classCode);
        String teacherCode = c.getClasscreator();
        feedback.setTeachercode(teacherCode);
        feedback.setStatus(Byte.valueOf("1"));
        feedbackMapper.insert(feedback);
        serviceResult.setData(new ResponseMsg(Msg.OK));
        serviceResult.setSuccess(true);
        return serviceResult;
    }

    /**
     *
     * @param feedback
     * @return
     */
    @Override
    public ServiceResult<ResponseMsg> update(Feedback feedback) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        if (feedback == null || feedback.getFeedbackid() == null) {
            serviceResult.setMessage("更新失败,参数错误");
            return serviceResult;
        }
        feedbackMapper.updateByPrimaryKeySelective(feedback);
        serviceResult.setSuccess(true);
        serviceResult.setData(new ResponseMsg(Msg.OK));
        return serviceResult;
    }

    @Override
    public ServiceResult<Feedback> get(Integer id) {
        ServiceResult<Feedback> serviceResult = new ServiceResult<>();
        if (id == null) {
            serviceResult.setMessage("问题编号不能为空");
            return serviceResult;
        }
        Feedback feedback = feedbackMapper.selectByPrimaryKey(id);
        if (feedback == null) {
            serviceResult.setMessage("没有找到对应的问题");
            return serviceResult;
        }
        serviceResult.setData(feedback);
        serviceResult.setSuccess(true);
        return serviceResult;
    }
}
