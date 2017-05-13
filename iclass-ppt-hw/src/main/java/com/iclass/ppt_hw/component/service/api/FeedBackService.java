package com.iclass.ppt_hw.component.service.api;

import com.iclass.mybatis.dto.FeedBackDTO;
import com.iclass.mybatis.po.Feedback;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;

import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 4/26/2017 10:05 PM.
 */
public interface FeedBackService {

    /**
     * 根据教师编号查询该教师的问题反馈
     *
     * @param requestEntity
     * @param userCode
     * @param parentId 获取标题内容时,传0,获取详情时,传具体的id
     * @param isLimit app端不需要分页
     * @return
     */
    ServiceResult<List<FeedBackDTO>> getAll(DataTablesRequestEntity requestEntity, String userCode, Integer parentId, Boolean isLimit);

    /**
     * 保存或者回复 问题
     *
     * @param feedback
     * @return
     */
    ServiceResult<ResponseMsg> save(Feedback feedback);

    /**
     * 修改问题 或者 修改他的状态
     * @param feedback
     * @return
     */
    ServiceResult<ResponseMsg> update(Feedback feedback);

    /**
     * 根据id获取反馈问题
     * @param id
     * @return
     */
    ServiceResult<Feedback> get(Integer id);
}
