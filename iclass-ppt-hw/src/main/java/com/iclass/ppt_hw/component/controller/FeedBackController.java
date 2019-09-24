package com.iclass.ppt_hw.component.controller;

import com.iclass.mybatis.dto.FeedBackDTO;
import com.iclass.mybatis.po.Feedback;
import com.iclass.ppt_hw.component.service.api.FeedBackService;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 4/26/2017 10:53 PM.
 */
@RestController
@RequestMapping("/feedback")
public class FeedBackController {

    @Autowired
    private FeedBackService feedBackService;

    /**
     * 根据教师编号查询该教师的问题反馈
     *
     * @param requestEntity
     * @param userCode
     * @param parentId 获取标题内容时,传0,获取详情时,传具体的id
     * @param isLimit app端不需要分页
     * @return
     */
    @RequestMapping(value = "/getAll", method = {RequestMethod.POST, RequestMethod.GET})
    ServiceResult<List<FeedBackDTO>> getAll(DataTablesRequestEntity requestEntity, String userCode, Integer parentId, Boolean isLimit, @RequestParam(required = false) Integer classCourseId) {
        if (isLimit == null) {
            isLimit = Boolean.TRUE;
        }
        if (parentId == null) {
            parentId = 0;
        }
        return feedBackService.getAll(requestEntity, userCode, parentId, isLimit, classCourseId);
    }

    /**
     * 保存或者回复 问题
     *
     * @param feedback
     * @return
     */
    @RequestMapping("/save")
    ServiceResult<ResponseMsg> save(Feedback feedback) {

        return feedBackService.save(feedback);
    }

    /**
     * 回复问题（教师）
     *
     * @param feedback parentid、content、feedbackcode
     * @return
     */
    @RequestMapping("/reply")
    ServiceResult<ResponseMsg> reply(Feedback feedback) {

        return feedBackService.reply(feedback);
    }

    /**
     * 修改问题 或者 修改状态
     * @param feedback
     * @return
     */
    @RequestMapping("/update")
    ServiceResult<ResponseMsg> update(Feedback feedback) {

        return feedBackService.update(feedback);
    }
    /**
     * 根据id获取反馈问题
     * @param id
     * @return
     */
    @RequestMapping("/get/{id}")
    ServiceResult<Feedback> get(@PathVariable("id") Integer id) {

        return feedBackService.get(id);
    }
}
