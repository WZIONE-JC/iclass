package com.iclass.ppt_hw.component.controller;

import com.iclass.mybatis.dto.RollCallDTO;
import com.iclass.ppt_hw.component.service.api.RollCallService;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by radishmaster on 15/04/17.
 */
@RestController
@RequestMapping("/rollcall")
public class RollCallController {

    @Autowired
    private RollCallService rollCallService;

    /**
     * 根据教师编号获取点名记录
     *
     * @param requestEntity
     * @param teacherCode
     * @return
     */
    @RequestMapping("/getAll")
    ServiceResult<List<RollCallDTO>> getAll(DataTablesRequestEntity requestEntity, String teacherCode) {

        return rollCallService.getAll(requestEntity, teacherCode);
    }

    /**
     * 删除点名记录
     *
     * @param rollcallId
     * @return
     */
    @RequestMapping("/del/{id}")
    ServiceResult<ResponseMsg> del(@PathVariable("id") Integer rollcallId) {

        return rollCallService.del(rollcallId);
    }

    /**
     * 点名
     *
     * @param teacherCode
     * @param classCourseId
     * @param time
     * @return
     */
    @RequestMapping(value = "/dorollcall", method = RequestMethod.POST)
    ServiceResult<String> rollcall(String teacherCode, Integer classCourseId, Integer time) {

        return rollCallService.rollcall(teacherCode, classCourseId, time);
    }

    /**
     * 签到
     * @param studentCode
     * @param classCourseId
     * @return
     */
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    ServiceResult<ResponseMsg> signIn(String studentCode, Integer classCourseId, String content) {

        return rollCallService.signIn(studentCode, classCourseId, content);
    }
}
