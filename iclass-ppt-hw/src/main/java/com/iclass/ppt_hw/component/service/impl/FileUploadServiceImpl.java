package com.iclass.ppt_hw.component.service.impl;

import com.iclass.mybatis.dao.IclassfileClassMapper;
import com.iclass.mybatis.dao.IclassfileMapper;
import com.iclass.mybatis.po.Iclassfile;
import com.iclass.mybatis.po.IclassfileClass;
import com.iclass.mybatis.qo.FileQo;
import com.iclass.ppt_hw.component.service.api.FileUploadService;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.Msg;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.utils.Util;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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

    /**
     * 上传文件时生成的UUID
     */
    private String fileCode;

    /**
     * 上传文件的文件名
     */
    private String fileName;
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
            if (StringUtils.isNotBlank(fileQo.getFileType())) {
                iclassFile.setFiletype(Integer.parseInt(fileQo.getFileType()));
            }
            if (StringUtils.isNotBlank(fileQo.getFileCreator())) {
                iclassFile.setFilecreator(fileQo.getFileCreator());
            }
            if (StringUtils.isNotBlank(fileQo.getFileDesc())) {
                iclassFile.setFiledesc(fileQo.getFileDesc());
            }
            if (StringUtils.isNotBlank(fileQo.getClassCode())) {
               fileClass.setClasscode(fileQo.getClassCode());
            }
            if (StringUtils.isNotBlank(fileQo.getCourseCode())) {
                fileClass.setCoursecode(fileQo.getCourseCode());
            }
            iclassFile.setFilecreatetime(Util.getDateTimeNow());

            int fileResult = iclassfileMapper.insertSelective(iclassFile);
            if (fileResult == 1) {
                int fileClassResult = iclassfileClassMapper.insertSelective(fileClass);
                if(fileClassResult == 1) {
                    serviceResult.setSuccess(true);
                    serviceResult.setData(new ResponseMsg(Msg.FILE_UPLOAD_SUCCESS));
                } else {
                    logger.warn("文件关系表建立失败");
                    serviceResult.setMessage("文件关系表建立失败");
                    delete(filePath);
                }
            } else {
                logger.warn("文件记录失败");
                serviceResult.setMessage("文件记录失败");
                delete(filePath);
            }
        }
        return serviceResult;
    }

    /**
     * 上传文件
     * @param request 获取web程序运行路径,获得文件存储路径
     * @param file 客户端接受到的文件
     * @return 返回处理信息
     */
    private ServiceResult<String> upload(HttpServletRequest request, MultipartFile file) {

        ServiceResult<String> serviceResult = new ServiceResult<>();

        if (file.isEmpty()) {
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
        String fileFloder = Util.getDateNow() + "/";

        if (logger.isDebugEnabled()) {
            logger.debug("文件存放目录为：" + fileFloder);
        }

        String filePath = request.getSession().getServletContext().getRealPath("/files/"+fileFloder);

        if(StringUtils.isBlank(filePath)) {
            logger.warn("获取文件存储路径失败");
            serviceResult.setMessage("获取文件存储路径失败");
            return serviceResult;
        }

        //fileCode
        fileCode = UUID.randomUUID()+"";

        //文件名唯一处理
        fileName = fileCode + "-" + fileName;

        //文件存放目录
        File dest = new File(filePath+fileName);

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
        }
    }
}
