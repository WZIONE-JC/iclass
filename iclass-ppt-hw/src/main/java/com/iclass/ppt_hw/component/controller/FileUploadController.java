package com.iclass.ppt_hw.component.controller;

import com.iclass.mybatis.qo.FileQo;
import com.iclass.ppt_hw.component.service.api.FileUploadService;
import com.iclass.ppt_hw.config.fileConfig.FileConfig;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/11/2017 12:41 PM.
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

    private final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private FileConfig fileConfig;

    /**
     *
     * @param request 获取web应用的路径，来存放文件
     * @param file 上传的文件
     * @param response 设置CORS 进行跨域提交数据
     * @return 返回服务器响应信息
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = {RequestMethod.POST})
    public ServiceResult<ResponseMsg> upload(HttpServletRequest request, HttpServletResponse response, MultipartFile file, FileQo fileQo) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        logger.info("file = [" + file + "], fileQo = [" + fileQo + "]");
        return fileUploadService.doData(request, file, fileQo);
    }

    @RequestMapping(value = "/download", method = {RequestMethod.GET, RequestMethod.POST}, produces = "multipart/form-data")
    public ResponseEntity<byte[]> download(HttpServletResponse response, String fileCode, Integer fileType) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        return fileUploadService.download(response, fileCode, fileType);
    }
}
