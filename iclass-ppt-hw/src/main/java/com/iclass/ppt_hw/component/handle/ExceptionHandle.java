package com.iclass.ppt_hw.component.handle;

import com.iclass.user.component.entity.ServiceResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/26/2017 10:52 PM.
 * 自定义异常处理，捕捉RuntimeException
 */
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ServiceResult handle(Exception e) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setMessage(e.getMessage());
        return serviceResult;
    }
}
