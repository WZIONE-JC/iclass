package com.iclass.ppt_hw.component.controller;

import com.iclass.mybatis.dto.ClassCourseDTO;
import com.iclass.mybatis.dto.IclassfileDTO;
import com.iclass.mybatis.qo.FileQo;
import com.iclass.ppt_hw.component.service.api.PPTHWService;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/12/2017 3:08 PM.
 */
@RequestMapping("/ppthw")
@RestController
public class PPTHWContorller {

    @Autowired
    private PPTHWService ppthwService;

    @RequestMapping(value = "/getPPTInfo",method = {RequestMethod.POST, RequestMethod.GET})
    public ServiceResult<List<ClassCourseDTO>> getPPTInfo(DataTablesRequestEntity requestEntity, String classCreator, @RequestParam(required = false) Integer classCourseId) {

        return ppthwService.getPPTInfo(requestEntity, classCreator, classCourseId);
    }

    @RequestMapping(value = "/getHWInfo",method = {RequestMethod.POST, RequestMethod.GET})
    public ServiceResult<List<ClassCourseDTO>> getHWInfo(DataTablesRequestEntity requestEntity, String classCreator, @RequestParam(required = false) Integer classCourseId) {

        return ppthwService.getHWInfo(requestEntity, classCreator, classCourseId);
    }

    @RequestMapping(value = "/getPPTFileInfo", method = {RequestMethod.POST, RequestMethod.GET})
    public ServiceResult<List<IclassfileDTO>> getPPTFileInfo(DataTablesRequestEntity requestEntity, FileQo fileQo) {

        return ppthwService.getPPTHWFileInfo(requestEntity, fileQo);
    }

    /**
     * 根据课堂id去获取它的文件信息
     * @param classCourseId
     * @return
     */
    @RequestMapping(value = "/getPPTFile", method = RequestMethod.POST)
    ServiceResult<List<IclassfileDTO>> getPPTFileInfo(Integer classCourseId) {

        return ppthwService.getPPTFileInfo(classCourseId);
    }

    /**
     * 根据课堂id去获取它的文件信息
     * @param classCourseId
     * @return
     */
    @RequestMapping(value = "/getHWFile", method = RequestMethod.POST)
    ServiceResult<List<IclassfileDTO>> getHWFileInfo(Integer classCourseId) {

        return ppthwService.getHWFileInfo(classCourseId);
    }
}
