package com.iclass.ppt_hw.component.controller;

import com.iclass.mybatis.dto.RollCallDTO;
import com.iclass.mybatis.vo.RollCallVoApp;
import com.iclass.mybatis.vo.RollcallVo;
import com.iclass.mybatis.vo.TimesVo;
import com.iclass.ppt_hw.component.service.api.RollCallService;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;
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
    ServiceResult<List<RollCallDTO>> getAll(DataTablesRequestEntity requestEntity, String teacherCode, @RequestParam(required = false) Integer classCourseId) {

        return rollCallService.getAll(requestEntity, teacherCode, classCourseId);
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
    @RequestMapping(value = "/dorollcall", method = {RequestMethod.POST, RequestMethod.GET})
    ServiceResult<ResponseMsg> rollcall(String teacherCode, Integer classCourseId, Integer time) {

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

    /**
     * 查看当前课堂的点名统计信息
     * @param classCourseId
     * @return
     */
    @RequestMapping("/show")
    ServiceResult<List<RollcallVo>> show(String teacherCode, Integer classCourseId, Integer times) {

        return rollCallService.show(teacherCode, classCourseId, times);
    }

    /**
     * 获取点名次数
     * @param classCourseId
     * @return
     */
    @RequestMapping("/getTimes")
    ServiceResult<List<TimesVo>> getTimes(Integer classCourseId) {

        return rollCallService.getTimes(classCourseId);
    }

    /**
     * app端获取该学生加入的课堂的所有点名数据
     */
    @RequestMapping("/getRollCallData")
    ServiceResult<List<RollCallVoApp>> getRollCallDataApp(String studentCode) {

        return rollCallService.getRollCallDataApp(studentCode);
    }
}
