package com.iclass.ppt_hw.component.service.api;

import com.iclass.mybatis.qo.FileQo;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件上传
 * <p>
 * Created by yang.tang on 2017/2/22 17:21.
 */
public interface FileUploadService {

    /**
     * 文件上传Service
     * @param request 获取服务器路径，来存储文件
     * @param file 客户端传过来的文件
     * @return 服务器相应信息
     */
    ServiceResult<ResponseMsg> doData(HttpServletRequest request, MultipartFile file, FileQo fileQo);

    ResponseEntity<byte[]> download(HttpServletResponse response, String fileCode, Integer fileType);
}
