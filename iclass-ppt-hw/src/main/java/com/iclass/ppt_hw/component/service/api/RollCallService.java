package com.iclass.ppt_hw.component.service.api;

import com.iclass.mybatis.dto.RollCallDTO;
import com.iclass.mybatis.vo.RollCallVoApp;
import com.iclass.mybatis.vo.RollcallVo;
import com.iclass.mybatis.vo.TimesVo;
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
     * @param classCourseId 课堂id
     * @return
     */
    ServiceResult<List<RollCallDTO>> getAll(DataTablesRequestEntity requestEntity, String teacherCode, Integer classCourseId);

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
    ServiceResult<ResponseMsg> rollcall(String teacherCode, Integer classCourseId, Integer time);

    /**
     * 签到
     * @param studentCode
     * @param classCourseId
     * @param content
     * @return
     */
    ServiceResult<ResponseMsg> signIn(String studentCode, Integer classCourseId, String content);

    /**
     * 查看当前课堂的点名统计信息
     * @param classCourseId
     * @return
     */
    ServiceResult<List<RollcallVo>> show(String teacherCode, Integer classCourseId, Integer times);

    /**
     * 获取点名次数
     * @param classCourseId
     * @return
     */
    ServiceResult<List<TimesVo>> getTimes(Integer classCourseId);

    /**
     * app端获取该学生加入的课堂的所有点名数据
     */
    ServiceResult<List<RollCallVoApp>> getRollCallDataApp(String studentCode);
}
