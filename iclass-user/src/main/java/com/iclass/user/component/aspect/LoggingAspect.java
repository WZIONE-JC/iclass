package com.iclass.user.component.aspect;

import com.iclass.mybatis.dao.LogMapper;
import com.iclass.mybatis.po.Log;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.utils.IclassUtil;
import com.iclass.user.component.utils.LogUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by radishmaster on 10/04/17.
 * 使用AOP来记录日志
 */
@Aspect
@Component
public class LoggingAspect {

    private final static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Autowired
    private LogMapper logMapper;

    @Pointcut("execution(public * com.iclass.user.component.service..*.*(..))")
    public void pointCut() {}

    /**
     * 对service做日志记录
     * @param joinPoint
     */
    @Around("pointCut()")
    public Object beforeServiceImpl(ProceedingJoinPoint joinPoint) {

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Object result = null;

        String type = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        String fullMethodName = type + "." + methodName;

        String ip =  LogUtil.getIp(request);

        String device = LogUtil.getDevice(request);

        String http_method = LogUtil.getMethod(request);

        String operation = LogUtil.getOperation(request);

        String method = fullMethodName;

        String args = Arrays.toString(joinPoint.getArgs());

        String data = null;

        String error = null;

        String exeTime = null;
        try {

            startTime.set(System.currentTimeMillis());

            // 记录下请求内容
//            logger.info("Ip : " + ip);
//            logger.info("Device : " + device);
//            logger.info("HTTP_METHOD : " + http_method);
//            logger.info("Operation : " + operation);
//            logger.info("CLASS_METHOD : " +  method);
//            logger.info("ARGS : " + args);

            result = joinPoint.proceed();

            exeTime = System.currentTimeMillis() - startTime.get() + "ms";

//            logger.info("Data： {}", result);

            if (result instanceof ServiceResult) {
                ServiceResult serviceResult = (ServiceResult) result;
                if (!serviceResult.getSuccess()) {
                    data = serviceResult.toString();
                }
            } else {
                data = result.toString();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logger.info("Error： {}", throwable);

            error = throwable.toString();
        }
        if (data != null) {
            // 保存日志
            Log log = new Log(null, ip, device, http_method, operation, method, args, exeTime, IclassUtil.getDateTimeNow(), data, error);

            logMapper.insert(log);
        }
        return result;
    }
}
