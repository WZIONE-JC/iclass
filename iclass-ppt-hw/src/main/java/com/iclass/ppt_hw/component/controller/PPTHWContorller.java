package com.iclass.ppt_hw.component.controller;

import com.iclass.mybatis.dto.IclassfileDTO;
import com.iclass.mybatis.dto.PPTHWDTO;
import com.iclass.mybatis.qo.FileQo;
import com.iclass.ppt_hw.component.service.api.PPTHWService;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public ServiceResult<List<PPTHWDTO>> getPPTInfo(DataTablesRequestEntity requestEntity, String classCreator) {

        return ppthwService.getPPTInfo(requestEntity, classCreator);
    }

    @RequestMapping(value = "/getHWInfo",method = {RequestMethod.POST, RequestMethod.GET})
    public ServiceResult<List<PPTHWDTO>> getHWInfo(DataTablesRequestEntity requestEntity, String classCreator) {

        return ppthwService.getHWInfo(requestEntity, classCreator);
    }

    @RequestMapping(value = "/getPPTFileInfo", method = {RequestMethod.POST, RequestMethod.GET})
    public ServiceResult<List<IclassfileDTO>> getPPTFileInfo(DataTablesRequestEntity requestEntity, FileQo fileQo) {

        return ppthwService.getPPTHWFileInfo(requestEntity, fileQo);
    }
}
