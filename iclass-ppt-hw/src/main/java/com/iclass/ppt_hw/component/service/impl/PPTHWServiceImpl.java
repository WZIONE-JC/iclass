package com.iclass.ppt_hw.component.service.impl;

import com.iclass.mybatis.dao.*;
import com.iclass.mybatis.dto.IclassfileDTO;
import com.iclass.mybatis.dto.PPTHWDTO;
import com.iclass.mybatis.dto.SessionUser;
import com.iclass.mybatis.po.Class;
import com.iclass.mybatis.po.*;
import com.iclass.mybatis.qo.FileQo;
import com.iclass.ppt_hw.component.service.api.PPTHWService;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
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
 * Created by JasonTang on 3/12/2017 2:09 PM.
 * 已上传课件和作业信息页面
 */
@Service("pPTHWService")
public class PPTHWServiceImpl implements PPTHWService {

    private final Logger logger = LoggerFactory.getLogger(PPTHWServiceImpl.class);

    /**
     * 使用classCreator(在User表中对应UserCode)
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 使用classCreator 去class表中查询ClassCode 和 ClassCourseCode
     */
    @Autowired
    private ClassMapper classMapper;

    /**
     * 根据ClassCourseCode 去course表中查询Course实体信息
     */
    @Autowired
    private CourseMapper courseMapper;

    /**
     * 根据ClassCode 和 ClassCourseCode 在iclassfile_class 表中查询FileCode
     * 可能为空
     */
    @Autowired
    private IclassfileClassMapper iclassfileClassMapper;

    /**
     * 根据FileCode 去 iclassfile 中查询去IclassFile 实体信息
     * 上面为空，这里也为空
     */
    @Autowired
    private IclassfileMapper iclassfileMapper;

    /**
     * 根据classCode获取学生信息
     */
    @Autowired
    private ClassStudentMapper classStudentMapper;

    /**
     * 获取已上传课件页面所需展示的信息
     *
     * @param requestEntity datatables请求数据
     * @param classCreator  teacherCode
     * @return PPTHWDTO
     */
    @Override
    public ServiceResult<List<PPTHWDTO>> getPPTInfo(DataTablesRequestEntity requestEntity, String classCreator) {

        return doData(requestEntity, classCreator);
    }

    /**
     * 获取已上传作业页面所需展示的信息
     *
     * @param requestEntity datatables请求数据
     * @param classCreator  teacherCode
     * @return PPTHWDTO
     */
    @Override
    public ServiceResult<List<PPTHWDTO>> getHWInfo(DataTablesRequestEntity requestEntity, String classCreator) {

        return doData(requestEntity, classCreator);
    }

    /**
     *
     * @param requestEntity datatables请求数据
     * @param classCreator teacherCode
     * @return PPTHWDTO 实体
     */
    private ServiceResult<List<PPTHWDTO>> doData(DataTablesRequestEntity requestEntity, String classCreator) {
        ServiceResult<List<PPTHWDTO>> serviceResult = new ServiceResult<>();
        requestEntity = CheckDataTables.check(requestEntity);
        Integer draw = requestEntity.getDraw();
        Integer start = requestEntity.getStart();
        Integer length = requestEntity.getLength();
        if (StringUtils.isBlank(classCreator)) {
            logger.warn("classCreator不能为空");
            serviceResult.setMessage("用户未登录");
            return serviceResult;
        }
        User teacher = userMapper.findByUsercode(classCreator);
        String teacherName = teacher.getUserfullname();
        Integer recordsTotal = classMapper.countByClassCreator(classCreator);
        // 获取课堂信息
        List<Class> classes = classMapper.selectByClassCreator(classCreator, start, length);
        if (classes != null && classes.size() == 0) {
            logger.info(teacherName + " 教师还没有创建课堂");
            serviceResult.setMessage(teacherName + " 教师还没有创建课堂");
            return serviceResult;
        }
        List<PPTHWDTO> ppthwdtoList = new ArrayList<>();
        // 获取课堂的课程信息
        for (Class c : classes) {
            PPTHWDTO ppthwdto = new PPTHWDTO();
            String courseCode = c.getClasscoursecode();
            //根据courseCode 获取Course 信息
            Course course = courseMapper.selectByCourseCode(courseCode);
            String classCode = c.getClasscode();
            List<ClassStudent> classStudents = classStudentMapper.selectByClassCode(classCode);
            List<SessionUser> sessionUsers = new ArrayList<>();
            // 如果有学生的话
            if (classStudents != null && classStudents.size() > 0) {
                for (ClassStudent cs : classStudents) {
                    String studentCode = cs.getStudentcode();
                    User user = userMapper.findUserByUsercode(studentCode);
                    sessionUsers.add(new SessionUser(user));
                }
            }
            ppthwdto.setStudents(sessionUsers);
            ppthwdto.setaClass(c);
            ppthwdto.setCourse(course);
            ppthwdto.setTeacherName(teacherName);
            ppthwdtoList.add(ppthwdto);
        }
        serviceResult.setSuccess(true);
        serviceResult.setData(ppthwdtoList);
        serviceResult.setDraw(draw);
        serviceResult.setRecordsTotal(recordsTotal);
        serviceResult.setRecordsFiltered(classes.size());
        logger.info("PPTINFO 信息初始化完成");
        return serviceResult;
    }

    @Override
    public ServiceResult<List<IclassfileDTO>> getPPTHWFileInfo(DataTablesRequestEntity requestEntity, FileQo fileQo) {
        ServiceResult<List<IclassfileDTO>> serviceResult = new ServiceResult<>();
        requestEntity = CheckDataTables.check(requestEntity);
        Integer draw = requestEntity.getDraw();
        Integer start = requestEntity.getStart();
        Integer length = requestEntity.getLength();
        if (fileQo == null) {
            logger.info("fileQo为空");
            serviceResult.setMessage("参数错误");
            return serviceResult;
        }
        if (StringUtils.isBlank(fileQo.getClassCode())) {
            logger.warn("classCode为空");
            serviceResult.setMessage("classCode为空");
            return serviceResult;
        }
        if (StringUtils.isBlank(fileQo.getCourseCode())) {
            logger.warn("courseCode为空");
            serviceResult.setMessage("courseCode为空");
            return serviceResult;
        }
        if (fileQo.getFileType() == null) {
            logger.warn("fileType为空");
            serviceResult.setMessage("fileType为空");
            return serviceResult;
        }
        List<IclassfileClass> iclassfileClassList = iclassfileClassMapper.selectByClassCodeAndCourseCode(fileQo.getClassCode(), fileQo.getCourseCode(), start, length);
        if (iclassfileClassList == null || iclassfileClassList.size() == 0) {
            logger.info("classcode为{}, courseCode为{} 的文件信息没有找到", fileQo.getClassCode(), fileQo.getCourseCode());
            serviceResult.setMessage("没有找到文件信息");
        }
        List<IclassfileDTO> iclassfileList = new ArrayList<>();
        for (IclassfileClass file : iclassfileClassList) {
            String fileCode = file.getFilecode();
            Iclassfile iclassfile = iclassfileMapper.selectByFileCode(fileCode, fileQo.getFileType());
            if (iclassfile != null) {
                User teacher = userMapper.findByUsercode(iclassfile.getFilecreator());
                String teacherName = teacher.getUserfullname();
                iclassfileList.add(new IclassfileDTO(iclassfile, teacherName));
        }
        }
        serviceResult.setData(iclassfileList);
        logger.info("文件信息查询成功" + iclassfileList.toString());
        serviceResult.setSuccess(true);
        serviceResult.setDraw(draw);
        // 以后如果加入过滤就需要修改这里
        serviceResult.setRecordsFiltered(iclassfileList.size());
        serviceResult.setRecordsTotal(iclassfileList.size());
        return serviceResult;
    }
}
