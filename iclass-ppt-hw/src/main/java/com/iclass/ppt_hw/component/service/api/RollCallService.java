package com.iclass.ppt_hw.component.service.api;

import com.iclass.mybatis.dto.RollCallDTO;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;

import java.util.List;

/**
 * Created by radishmaster on 15/04/17.
 */
public interface RollCallService {

    /**
     * 根据教师编号获取点名记录
     *
     * @param requestEntity
     * @param teacherCode
     * @return
     */
    ServiceResult<List<RollCallDTO>> getAll(DataTablesRequestEntity requestEntity, String teacherCode);

    /**
     * 删除点名记录
     *
     * @param rollcallId
     * @return
     */
    ServiceResult<ResponseMsg> del(Integer rollcallId);

    /**
     * 点名
     *
     * @param teacherCode
     * @param classCourseId
     * @param time
     * @return
     */
    ServiceResult<String> rollcall(String teacherCode, Integer classCourseId, Integer time);

    /**
     * 签到
     * @param studentCode
     * @param classCourseId
     * @param content
     * @return
     */
    ServiceResult<ResponseMsg> signIn(String studentCode, Integer classCourseId, String content);

}
