package com.iclass.ppt_hw.component.service.impl;

import com.iclass.ppt_hw.component.service.api.CacheInfoService;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.Msg;
import com.iclass.user.component.msg.ResponseMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/25/2017 11:41 PM.
 */
@Service("cacheInfoService")
public class CacheInfoServiceImpl implements CacheInfoService{

    private final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Override
    public ServiceResult<ResponseMsg> setCache(HttpServletRequest request, List<String> strings) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        HttpSession session = request.getSession();
        if (strings.size() == 0) {
            logger.warn("缓存设置失败,没有数据需要缓存");
            serviceResult.setMessage("缓存设置失败");
        }
        logger.warn("设置缓存成功,sessionId为"+session.getId());
        session.setAttribute(session.getId(), strings);
        serviceResult.setData(new ResponseMsg(Msg.CACHE_SUCCESS));
        serviceResult.setSuccess(true);
        return serviceResult;
    }

    @Override
    public ServiceResult<List<String>> getCache(HttpServletRequest request) {
        ServiceResult<List<String>> serviceResult = new ServiceResult<>();
        HttpSession session = request.getSession();
        try {
            List<String> list = (List<String>) session.getAttribute(session.getId());
            if (list == null) {
                logger.info("获取缓存信息失败,对应sessionId:"+session.getId());
                serviceResult.setMessage("获取缓存信息失败");
                return serviceResult;
            }
            serviceResult.setData(list);
            serviceResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setMessage(e.toString());
        }
        return serviceResult;
    }
}
