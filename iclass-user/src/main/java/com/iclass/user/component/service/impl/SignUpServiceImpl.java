package com.iclass.user.component.service.impl;

import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.md5.MD5;
import com.iclass.user.component.msg.Msg;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.service.api.StudentService;
import com.iclass.user.component.service.api.TeacherService;
import com.iclass.user.component.service.api.ValidateExistService;
import com.iclass.user.mybatis.dao.UserMapper;
import com.iclass.user.component.service.api.SignUpService;
import com.iclass.user.mybatis.model.Student;
import com.iclass.user.mybatis.model.Teacher;
import com.iclass.user.mybatis.model.User;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/12/2017 12:26 PM.
 */
@Service("SignUpService")
public class SignUpServiceImpl implements SignUpService {

    private final Logger logger = LoggerFactory.getLogger(SignUpServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    private ValidateExistService validateExistService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Override
    public String getMD5Password(String password) {
        return MD5.getPwd(password);
    }

    @Override
    @Transactional
    public ServiceResult<ResponseMsg> signup(User user) {
        logger.info("注册时,获取的用户信息为:" + user);

        boolean isSuccess = false;
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        ResponseMsg responseMsg;
        /**
         * 在这里做一下拦截，避免用户注册成功后，重复提交注册信息,
         * 主要验证下用户名和工号
         */
        serviceResult = validateExistService.isExistUserCode(user.getUsercode());

        responseMsg = serviceResult.getData();

        if (responseMsg.getMsg().equals(Msg.USERCODE_EXISTED)) {
            logger.error("工号:" + user.getUsercode() + "已经存在");
        } else {
            serviceResult = validateExistService.isExistUsername(user.getUsername());
            if (responseMsg.getMsg().equals(Msg.USERNAME_EXISTED)) {
                logger.error("用户名:" + user.getUsername() + "已经存在");
            } else {
                //如果用户名 和 工号都不存在的话，就执行插入、
                //加密密码
                user.setUserpassword(getMD5Password(user.getUserpassword()));
                //处理注册日期
                user.setUserregisterdate(new String(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));

                int result = userMapper.insert(user);

                if (result == 1) {

                    //插入学生
                    if(user.getUserrole().equals("学生")) {
                        ServiceResult<Student> studentServiceResult;
                        Student student = new Student();
                        student.setStudentcode(user.getUsercode());
                        studentServiceResult = studentService.save(student);
                        if(studentServiceResult.getSuccess()) {
                            isSuccess = true;
                        } else {
                            serviceResult.setMessage(studentServiceResult.getMessage());
                        }
                    } else if (user.getUserrole().equals("教师")) {
                        //插入教师
                        ServiceResult<Teacher> teacherServiceResult;
                        Teacher teacher = new Teacher();
                        teacher.setTeachercode(user.getUsercode());
                        teacherServiceResult = teacherService.save(teacher);
                        if(teacherServiceResult.getSuccess()) {
                            isSuccess = true;
                        } else {
                            serviceResult.setMessage(teacherServiceResult.getMessage());
                        }
                    }
                    if(isSuccess) {
                        responseMsg.setMsg(Msg.SIGNUP_SUCCESS);
                        serviceResult.setSuccess(true);
                        logger.info("注册成功");
                    } else {
                        responseMsg.setMsg(Msg.LOGIN_FAILED);
                        logger.info("注册失败,result = " + result);
                    }
                } else {
                    responseMsg.setMsg(Msg.LOGIN_FAILED);
                    logger.info("注册失败,result = " + result);
                }
            }
        }
        serviceResult.setData(responseMsg);
        return serviceResult;
    }
}
