package com.iclass.ppt_hw.component.service.impl;

import com.iclass.mybatis.dao.*;
import com.iclass.mybatis.dto.IclassfileDTO;
import com.iclass.mybatis.dto.PPTHWDTO;
import com.iclass.mybatis.po.Class;
import com.iclass.mybatis.po.*;
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
     * 获取已上传课件页面所需展示的信息
     *
     * @param requestEntity datatables请求数据
     * @param classCreator  teacherCode
     * @return PPTHWDTO
     */
    @Override
    public ServiceResult<List<PPTHWDTO>> getPPTInfo(DataTablesRequestEntity requestEntity, String classCreator) {

        return doData(requestEntity, classCreator, 1);
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

        return doData(requestEntity, classCreator, 0);
    }

    /**
     *
     * @param requestEntity datatables请求数据
     * @param classCreator teacherCode
     * @param fileType 0:作业，1:PPT
     * @return PPTHWDTO 实体
     */
    private ServiceResult<List<PPTHWDTO>> doData(DataTablesRequestEntity requestEntity, String classCreator, Integer fileType) {
        ServiceResult<List<PPTHWDTO>> serviceResult = new ServiceResult<>();
        requestEntity = CheckDataTables.check(requestEntity);
        Integer draw = requestEntity.getDraw();
        Integer start = requestEntity.getStart();
        Integer length = requestEntity.getLength();
        if (StringUtils.isNotBlank(classCreator)) {
            User teacher = userMapper.findByUsercode(classCreator);
            String teacherName = teacher.getUserfullname();
            Integer recordsTotal = classMapper.countByClassCreator(classCreator);
            List<Class> classes = classMapper.selectByClassCreator(classCreator, start, length);
            if (classes != null && classes.size() == 0) {
                logger.info(teacherName + " 教师还没有创建课堂");
                serviceResult.setMessage(teacherName + " 教师还没有创建课堂");
                return serviceResult;
            }
            String classCode;
            String courseCode;
            String fileCode;
            Course course;
            List<IclassfileDTO> iclassfileDTOList = null;
            Iclassfile iclassfile = null;
            List<IclassfileClass> iclassfileClassList = null;
            List<PPTHWDTO> ppthwdtoList = new ArrayList<>();
            PPTHWDTO ppthwdto;

            for (Class c : classes) {
                courseCode = c.getClasscoursecode();
                //根据courseCode 获取Course 信息
                course = courseMapper.selectByCourseCode(courseCode);
                classCode = c.getClasscode();
                // 根据 classCode 和courseCode 获取 文件课堂关系 实体
                iclassfileClassList = iclassfileClassMapper.selectByClassCodeAndCourseCode(classCode, courseCode);
                ppthwdto = new PPTHWDTO();
                iclassfileDTOList = new ArrayList<>();
                if (iclassfileClassList != null && iclassfileClassList.size() > 0) {
                    for(IclassfileClass ifc : iclassfileClassList) {
                        fileCode = ifc.getFilecode();
                        if (StringUtils.isNotBlank(fileCode)) {
                            iclassfile = iclassfileMapper.selectByFileCode(fileCode, fileType);
                            if(iclassfile != null) {
                                iclassfileDTOList.add(new IclassfileDTO(iclassfile));
                                logger.info(iclassfileDTOList.toString());
                                logger.info("找到了classCode:" + classCode + ",courseCode:" + courseCode + "对应的文件关系");
                            }
                        } else {
                            logger.warn("没有找到fileCode为：" + fileCode + "的文件记录");
                        }
                    }
                    //添加进集合数据
                    ppthwdto.setIclassfiles(iclassfileDTOList);
                } else {
                    logger.warn("没有找到classCode:" + classCode + ",courseCode:" + courseCode + "对应的文件关系");
                }
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
        } else {
            logger.warn("classCreator不能为空");
            serviceResult.setMessage("用户未登录");
        }
        return serviceResult;
    }

}
