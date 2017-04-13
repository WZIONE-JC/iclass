package com.iclass.ppt_hw.component.service.impl;

import com.iclass.mybatis.dao.*;
import com.iclass.mybatis.dto.ClassCourseDTO;
import com.iclass.mybatis.dto.IclassfileDTO;
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
     * 使用classCreator 去class表中查询ClassCode
     */
    @Autowired
    private ClassMapper classMapper;

    /**
     * 使用ClassCode去查询courseCode
     */
    @Autowired
    private ClassCourseMapper classCourseMapper;

    /**
     * 根据courseCode 去course表中查询Course实体信息
     */
    @Autowired
    private CourseMapper courseMapper;

    /**
     * 根据classCourseId 在iclassfile_class 表中查询FileCode
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
    private ClassCourseStudentMapper classCourseStudentMapper;

    /**
     * 获取已上传课件页面所需展示的信息
     *
     * @param requestEntity datatables请求数据
     * @param classCreator  teacherCode
     * @return PPTHWDTO
     */
    @Override
    public ServiceResult<List<ClassCourseDTO>> getPPTInfo(DataTablesRequestEntity requestEntity, String classCreator) {

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
    public ServiceResult<List<ClassCourseDTO>> getHWInfo(DataTablesRequestEntity requestEntity, String classCreator) {

        return doData(requestEntity, classCreator);
    }

    /**
     * 1.先通过classCreator查询user表,查出user实体
     * 2.再通过classCreator查询class表,查出该老师所创建的class
     * 3.通过获取的classCode去class_course表中查询classCourse实体
     * 4.通过courseCode查询出course数据
     * 5.通过classCode去class_student表中查询学生数据
     * @param requestEntity datatables请求数据
     * @param classCreator teacherCode
     * @return PPTHWDTO 实体
     */
    private ServiceResult<List<ClassCourseDTO>> doData(DataTablesRequestEntity requestEntity, String classCreator) {
        ServiceResult<List<ClassCourseDTO>> serviceResult = new ServiceResult<>();

        requestEntity = CheckDataTables.check(requestEntity);
        Integer draw = requestEntity.getDraw();
        Integer start = requestEntity.getStart();
        Integer length = requestEntity.getLength();

        if (StringUtils.isBlank(classCreator)) {
            logger.warn("classCreator不能为空");
            serviceResult.setMessage("用户未登录");
            return serviceResult;
        }
        //1.获取教师信息
        User user = userMapper.findByUsercode(classCreator);
        String teacherName = user.getUserfullname();
        String role = user.getUserrole();
        Integer recordsTotal = classMapper.countByClassCreator(classCreator);
        //2.获取课堂信息
        List<Class> classes = classMapper.selectByClassCreator(classCreator, start, length);
        if (classes != null && classes.size() == 0) {
            logger.info(role + " " + teacherName + "还没有创建课堂");
            serviceResult.setMessage(role + " " + teacherName + "还没有创建课堂");
            return serviceResult;
        }
        List<ClassCourseDTO> classCourseDTOS = new ArrayList<>();
        //3.获取课堂的课程信息
        for (Class c : classes) {
            String classcode = c.getClasscode();

            //3.1根据classCode查询courseCode
            List<ClassCourse> classCourses = classCourseMapper.selectByClassCode(classcode);
            for (ClassCourse classCourse : classCourses) {
                //4.根据courseCode 获取Course 信息
                Course course = courseMapper.selectByCourseCode(classCourse.getCoursecode());
                //5.查询学生数据
                List<SessionUser> sessionUsers = new ArrayList<>();
                String createTime = classCourse.getCreatetime();
                String deadline = classCourse.getDeadline();
                Integer status = classCourse.getStatus();
                Integer classCourseId = classCourse.getClasscourseid();
                List<ClassCourseStudent> classCourseStudents = classCourseStudentMapper.selectByClassCourseId(classCourseId);
                // 如果有学生的话
                if (classCourseStudents != null && classCourseStudents.size() > 0) {
                    for (ClassCourseStudent cs : classCourseStudents) {
                        String studentCode = cs.getStudentcode();
                        User student = userMapper.findUserByUsercode(studentCode);
                        sessionUsers.add(new SessionUser(student));
                    }
                }
                ClassCourseDTO classCourseDTO = new ClassCourseDTO(classCourseId, c, course, teacherName, sessionUsers, createTime, deadline, status);
                classCourseDTOS.add(classCourseDTO);
            }
        }
        serviceResult.setSuccess(true);
        serviceResult.setData(classCourseDTOS);
        serviceResult.setDraw(draw);
        serviceResult.setRecordsTotal(classCourseDTOS.size());
        serviceResult.setRecordsFiltered(classCourseDTOS.size());
        logger.info("PPTINFO 信息初始化完成");
        return serviceResult;
    }

    /**
     * 根据classCode和courseCode去查询FileCode
     * 再通过FileCode去查询File信息
     * @param requestEntity datatables请求数据
     * @param fileQo fileQo
     * @return
     */
    @Override
    public ServiceResult<List<IclassfileDTO>> getPPTHWFileInfo(DataTablesRequestEntity requestEntity, FileQo fileQo) {
        ServiceResult<List<IclassfileDTO>> serviceResult = new ServiceResult<>();
        requestEntity = CheckDataTables.check(requestEntity);
        Integer draw = requestEntity.getDraw();
        Integer start = requestEntity.getStart();
        Integer length = requestEntity.getLength();
        if (fileQo == null) {
            serviceResult.setMessage("参数错误");
            return serviceResult;
        }
        if (StringUtils.isBlank(fileQo.getClassCode())) {
            serviceResult.setMessage("班级编号不能为空");
            return serviceResult;
        }
        if (StringUtils.isBlank(fileQo.getCourseCode())) {
            serviceResult.setMessage("课程编号不能为空");
            return serviceResult;
        }
        if (fileQo.getFileType() == null) {
            serviceResult.setMessage("文件类型为空");
            return serviceResult;
        }

        ClassCourse classCourse = classCourseMapper.selectByClassCodeAndCourseCode(fileQo.getClassCode(), fileQo.getCourseCode());

        if (classCourse == null) {
            serviceResult.setMessage("没有找到班级编号为:" + fileQo.getClassCode() + ",课程编号为:" + fileQo.getCourseCode() + "的课堂");
            return serviceResult;
        }
        List<IclassfileClass> iclassfileClassList = iclassfileClassMapper.selectByClassCourseId(classCourse.getClasscourseid(), start, length);
        if (iclassfileClassList == null || iclassfileClassList.size() == 0) {
            serviceResult.setMessage("没有找到文件信息");
            return serviceResult;
        }
        List<IclassfileDTO> iclassfileList = new ArrayList<>();
        for (IclassfileClass file : iclassfileClassList) {
            String fileCode = file.getFilecode();
            Iclassfile iclassfile = iclassfileMapper.selectByFileCode(fileCode, fileQo.getFileType());
            if (iclassfile != null) {
                User teacher = userMapper.findByUsercode(iclassfile.getFilecreator());
                String teacherName = teacher.getUserfullname();
                String fileRelativePath = iclassfile.getFilerelativepath();
                fileRelativePath = fileRelativePath.replace("\\", "/");
                String url = "www.aiiclass.cn" + "/files/" + fileRelativePath;
                iclassfileList.add(new IclassfileDTO(iclassfile, url, teacherName));
            }
        }
        serviceResult.setData(iclassfileList);
        serviceResult.setSuccess(true);
        serviceResult.setDraw(draw);
        // 以后如果加入过滤就需要修改这里
        serviceResult.setRecordsFiltered(iclassfileList.size());
        serviceResult.setRecordsTotal(iclassfileList.size());
        return serviceResult;
    }

    @Override
    public ServiceResult<List<IclassfileDTO>> getPPTFileInfo(Integer classCourseId) {
        ServiceResult<List<IclassfileDTO>> serviceResult = new ServiceResult<>();
        if (classCourseId == null) {
            serviceResult.setMessage("课堂编号不能为空");
            return serviceResult;
        }
        List<IclassfileClass> iclassfileClassList = iclassfileClassMapper.selectByClassCourseIdNoLimit(classCourseId);
        if (iclassfileClassList == null || iclassfileClassList.size() == 0) {
            serviceResult.setMessage("没有找到文件信息");
            return serviceResult;
        }
        List<IclassfileDTO> iclassfileList = getFile(iclassfileClassList, 1);

        serviceResult.setData(iclassfileList);
        serviceResult.setSuccess(true);
        return serviceResult;
    }

    @Override
    public ServiceResult<List<IclassfileDTO>> getHWFileInfo(Integer classCourseId) {
        ServiceResult<List<IclassfileDTO>> serviceResult = new ServiceResult<>();
        if (classCourseId == null) {
            serviceResult.setMessage("课堂编号不能为空");
            return serviceResult;
        }
        List<IclassfileClass> iclassfileClassList = iclassfileClassMapper.selectByClassCourseIdNoLimit(classCourseId);
        if (iclassfileClassList == null || iclassfileClassList.size() == 0) {
            serviceResult.setMessage("没有找到文件信息");
            return serviceResult;
        }
        List<IclassfileDTO> iclassfileList = getFile(iclassfileClassList, 0);

        serviceResult.setData(iclassfileList);
        serviceResult.setSuccess(true);
        return serviceResult;
    }

    /**
     * 获取文件数据
     * @param iclassfileClassList
     * @param fileType 1,表示ppt数据  0,表示作业数据
     * @return
     */
    private List<IclassfileDTO> getFile( List<IclassfileClass> iclassfileClassList, Integer fileType) {
        List<IclassfileDTO> iclassfileList = new ArrayList<>();
        for (IclassfileClass file : iclassfileClassList) {
            String fileCode = file.getFilecode();
            Iclassfile iclassfile = iclassfileMapper.selectByFileCode(fileCode, fileType);
            if (iclassfile != null) {
                User teacher = userMapper.findByUsercode(iclassfile.getFilecreator());
                String teacherName = teacher.getUserfullname();
                String fileRelativePath = iclassfile.getFilerelativepath();
                fileRelativePath = fileRelativePath.replace("\\", "/");
                String url = "www.aiiclass.cn" + "/files/" + fileRelativePath;
                iclassfileList.add(new IclassfileDTO(iclassfile, url, teacherName));
            }
        }
        return iclassfileList;
    }
}
