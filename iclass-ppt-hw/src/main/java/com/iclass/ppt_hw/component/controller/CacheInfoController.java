package com.iclass.ppt_hw.component.controller;

import com.iclass.ppt_hw.component.service.api.CacheInfoService;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/25/2017 11:54 PM.
 */
@RestController
@RequestMapping("/ppthw")
public class CacheInfoController {

    @Autowired
    private CacheInfoService cacheInfoService;

    @RequestMapping(value = "/setCache", method = {RequestMethod.POST, RequestMethod.GET})
    public ServiceResult<ResponseMsg> setCache(HttpServletRequest request, HttpServletResponse response,String classCode, String courseCode) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        List<String> strings = new ArrayList<>();
        strings.add(classCode);
        strings.add(courseCode);
        return cacheInfoService.setCache(request, strings);
    }

    @RequestMapping(value = "/getCache", method = {RequestMethod.POST, RequestMethod.GET})
    public ServiceResult<List<String>> getCache(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        return cacheInfoService.getCache(request);
    }
}
