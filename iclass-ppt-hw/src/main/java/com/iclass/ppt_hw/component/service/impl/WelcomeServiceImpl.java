package com.iclass.ppt_hw.component.service.impl;

import com.iclass.mybatis.dao.*;
import com.iclass.mybatis.po.User;
import com.iclass.mybatis.vo.WelcomeVo;
import com.iclass.ppt_hw.component.service.api.WelcomeService;
import com.iclass.user.component.entity.ServiceResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * iclass
 * <p>
 * Created by JasonTang on 5/14/2017 10:36 AM.
 */
@Service("welcomeService")
public class WelcomeServiceImpl implements WelcomeService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ClassCourseMapper classCourseMapper;

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeacherCourseMapper teacherCourseMapper;

    @Autowired
    private IclassfileClassMapper fileMapper;

    @Override
    public ServiceResult<WelcomeVo> getWelcomeData(String userCode) {
        ServiceResult<WelcomeVo> serviceResult = new ServiceResult<>();
        if (StringUtils.isBlank(userCode)) {
            serviceResult.setMessage("未登录");
            return serviceResult;
        }
        User user = userMapper.findUserByUsercode(userCode);
        if (user == null) {
            serviceResult.setMessage("您还不是我们系统的用户");
            return serviceResult;
        }
        Integer classCourseNum;
        Integer classNum;
        Integer courseNum;
        Integer pptNum;
        Integer hwNum;

        // 如果是管理员就显示所有的数据
        if (user.getUserrole().equals("管理员")) {
            classCourseNum = classCourseMapper.countAll();
            classNum = classMapper.countAll();
            courseNum = courseMapper.countAll();
            pptNum = fileMapper.countAllByFileType(1);
            hwNum = fileMapper.countAllByFileType(0);
        } else {
        // 如果是教师,就显示教师所创建的数据
            classCourseNum = classCourseMapper.countByClassCreator(userCode);
            classNum = classMapper.countByClassCreator(userCode);
            courseNum = teacherCourseMapper.countByTeacherCode(userCode);
            pptNum = fileMapper.countByTeacherCodeAndFileType(userCode, 1);
            hwNum = fileMapper.countByTeacherCodeAndFileType(userCode, 0);
        }
        serviceResult.setData(new WelcomeVo(classCourseNum, classNum, courseNum, pptNum, hwNum));
        serviceResult.setSuccess(true);
        return serviceResult;
    }
}
