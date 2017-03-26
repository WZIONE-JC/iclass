package com.iclass.ppt_hw.component.service.api;

import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/25/2017 11:39 PM.
 */
public interface CacheInfoService {

    /**
     * 通过session来缓存数据
     * @param request session
     * @param strings 缓存参数
     * @return
     */
    public ServiceResult<ResponseMsg> setCache(HttpServletRequest request, List<String> strings);

    /**
     * 通过session来获取数据
     * @param request
     * @return
     */
    public ServiceResult<List<String>> getCache(HttpServletRequest request);
}
