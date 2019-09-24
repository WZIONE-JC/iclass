package com.iclass.ppt_hw.component.service.impl;

import com.iclass.mybatis.dao.ClassCourseMapper;
import com.iclass.mybatis.dao.IclassfileClassMapper;
import com.iclass.mybatis.dao.IclassfileMapper;
import com.iclass.mybatis.po.ClassCourse;
import com.iclass.mybatis.po.Iclassfile;
import com.iclass.mybatis.po.IclassfileClass;
import com.iclass.mybatis.qo.FileQo;
import com.iclass.ppt_hw.component.service.api.FileUploadService;
import com.iclass.ppt_hw.config.fileConfig.FileConfig;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.Msg;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.utils.IclassUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传
 * <p>
 * Created by yang.tang on 2017/2/22 17:22.
 */
@Service("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    @Autowired
    private IclassfileMapper iclassfileMapper;

    @Autowired
    private IclassfileClassMapper iclassfileClassMapper;

    @Autowired
    private ClassCourseMapper classCourseMapper;

    @Autowired
    private FileConfig fileConfig;

    /**
     * 上传文件时生成的UUID
     */
    private String fileCode;

    /**
     * 上传文件的文件名
     */
    private String fileName;

    /**
     * 文件存放的相对路径
     * /2017-04-11/xxxx-xxxx.doc
     */
    private String fileRelativePath;
    /**
     *
     * @param request 获取服务器路径，来存储文件
     * @param file 客户端传过来的文件
     * @param fileQo 客户端传来的数据
     *               fileType
     *               fileCreator
     *               courseCode
     *               classCode
     *               fileDesc
     * @return 返回处理信息
     */
    @Override
    @Transactional
    public ServiceResult<ResponseMsg> doData(HttpServletRequest request, MultipartFile file, FileQo fileQo) {

        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        ServiceResult<String> uplpadResult = upload(request, file);

        if(uplpadResult.getSuccess()) {
            String filePath = uplpadResult.getData();
            Iclassfile iclassFile = new Iclassfile();
            IclassfileClass fileClass = new IclassfileClass();
            if(StringUtils.isNotBlank(fileRelativePath)) {
                iclassFile.setFilerelativepath(fileRelativePath);
            }
            if(StringUtils.isNotBlank(filePath)) {
                iclassFile.setFilepath(filePath);
            }
            if(StringUtils.isNotBlank(fileName)) {
                iclassFile.setFilename(fileName);
            }
            if(StringUtils.isNotBlank(fileCode)) {
                iclassFile.setFilecode(fileCode);
                fileClass.setFilecode(fileCode);
            }
            if (fileQo.getFileType() != null) {
                iclassFile.setFiletype(fileQo.getFileType());
            }
            if (StringUtils.isNotBlank(fileQo.getFileCreator())) {
                iclassFile.setFilecreator(fileQo.getFileCreator());
            }
            if (StringUtils.isNotBlank(fileQo.getFileDesc())) {
                iclassFile.setFiledesc(fileQo.getFileDesc());
            }
            if (StringUtils.isBlank(fileQo.getClassCode())) {
                serviceResult.setMessage("班级编号不能为空");
                delete(filePath);
                return serviceResult;
            }
            if (StringUtils.isBlank(fileQo.getCourseCode())) {
                serviceResult.setMessage("课程编号不能为空");
                delete(filePath);
                return serviceResult;
            }
            String classCode = fileQo.getClassCode();

            String courseCode = fileQo.getCourseCode();

            ClassCourse classCourse = classCourseMapper.selectByClassCodeAndCourseCode(classCode, courseCode);

            if (classCourse == null) {
                serviceResult.setMessage("没有找到班级编号为:" + classCode + ",课程编号为:" + courseCode + "的课堂");
                delete(filePath);
                return serviceResult;
            }
            fileClass.setClasscourseid(classCourse.getClasscourseid());
            //保存课件信息
            iclassFile.setFilecreatetime(IclassUtil.getDateTimeNow());
            iclassfileMapper.insertSelective(iclassFile);
            //保存课堂-文件记录
            iclassfileClassMapper.insert(fileClass);
            serviceResult.setSuccess(true);
            serviceResult.setData(new ResponseMsg(Msg.FILE_UPLOAD_SUCCESS));
        }
        serviceResult.setMessage(uplpadResult.getMessage());
        return serviceResult;
    }

    @Override
    public ResponseEntity<byte[]> download(HttpServletResponse response, String fileCode, Integer fileType) {
        HttpHeaders headers = new HttpHeaders();
        if (StringUtils.isBlank(fileCode)) {
            logger.warn("fileCode为空");
            return new ResponseEntity<>(null,
                    null, HttpStatus.NOT_FOUND);
        }
        if (fileType == null) {
            logger.warn("fileType为空");
            return new ResponseEntity<>(null,
                    null, HttpStatus.NOT_FOUND);
        }
        try {
            response.setCharacterEncoding("utf-8");
            Iclassfile iclassFile = iclassfileMapper.selectByFileCode(fileCode, fileType);
            if (iclassFile == null) {
                logger.info("没有找到文件,fileCoe:" + fileCode + ",fileType:" + fileType);
                return new ResponseEntity<>(null,
                        null, HttpStatus.NOT_FOUND);
            }
            String path = iclassFile.getFilepath();
            String fileName = iclassFile.getFilename();
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            File file = new File(path);
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(FileUtils.readFileToByteArray(file),
                    headers, HttpStatus.OK);
            iclassfileMapper.updateFileDownloadTimeByFileCode(fileCode);
            return responseEntity;
        } catch (FileNotFoundException e) {
            logger.info("文件不存在");
            return new ResponseEntity<>(null,
                    null, HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            logger.info("未知错误");
            e.printStackTrace();
            return new ResponseEntity<>(null,
                    null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 上传文件
     * @param request 获取web程序运行路径,获得文件存储路径
     * @param file 客户端接受到的文件
     * @return 返回处理信息
     */
    private ServiceResult<String> upload(HttpServletRequest request, MultipartFile file) {

        ServiceResult<String> serviceResult = new ServiceResult<>();

        if (file == null || file.isEmpty()) {
            logger.warn("文件为空");
            serviceResult.setMessage("文件为空");
            return serviceResult;
        }
        // 获取文件名
        fileName = file.getOriginalFilename();
        if(logger.isDebugEnabled()) {
            logger.debug("上传的文件名为：" + fileName);
        }
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        if(logger.isDebugEnabled()) {
            logger.debug("上传的后缀名为：" + suffixName);
        }

        // 文件存放目录
        String fileFloder = IclassUtil.getDateNow() + File.separator;

        if (logger.isDebugEnabled()) {
            logger.debug("文件存放目录为：" + fileFloder);
        }

        String filePath = fileConfig.getFilePath();

        if (StringUtils.isBlank(filePath)) {
            logger.info("文件路径读取失败");
            serviceResult.setMessage("文件路径读取失败");
            return serviceResult;
        }
        filePath = filePath + (File.separator + fileFloder);

        if(StringUtils.isBlank(filePath)) {
            logger.warn("获取文件存储路径失败");
            serviceResult.setMessage("获取文件存储路径失败");
            return serviceResult;
        }

        //fileCode
        fileCode = UUID.randomUUID()+"";

        //初始化文件相对路径
        fileRelativePath = fileFloder + fileCode + "-" + fileName;

        //文件存放目录
        File dest = new File(filePath + fileCode + "-" + fileName);

        // 文件存放绝对路径
        String absolutePath = dest.getAbsolutePath();
        if (logger.isDebugEnabled()) {
            logger.debug("文件真实存放的路径：" + absolutePath);
        }
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        // 生成保存文件的绝对路径
        File fileSavePath = new File(absolutePath);
        if (!fileSavePath.getParentFile().exists()) {
            fileSavePath.getParentFile().mkdirs();
        }
        try {
            file.transferTo(fileSavePath);
            logger.info("文件保存成功");
            serviceResult.setSuccess(true);
            serviceResult.setData(absolutePath);
            return serviceResult;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            serviceResult.setMessage(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            serviceResult.setMessage(e.toString());
        }
        serviceResult.setMessage("文件上传失败");
        logger.warn("文件上传失败");
        return serviceResult;
    }

    /**
     * 出现异常时删除上传的文件
     * @param filepath 文件路径
     */
    private void delete(String filepath) {
        File file = new File(filepath);
        if(file.exists()) {
            file.delete();
            logger.info("遇到错误,成功删除文件" + filepath);
        }
    }
}
