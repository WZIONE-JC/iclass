package com.iclass.ppt_hw.component.log;

import com.iclass.mybatis.po.Log;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;

import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 4/11/2017 2:05 PM.
 */
public interface LogService {

    /**
     * 只有管理员才能查看日志数据
     *
     * @param requestEntity
     * @param userCode 只是用来验证权限
     * @return
     */
    ServiceResult<List<Log>> getAll(DataTablesRequestEntity requestEntity, String userCode);

    /**
     * 批量删除日志
     * @param id
     * @return
     */
    ServiceResult<ResponseMsg> del(Integer id);

    /**
     * 获取日志详情
     * @param id
     * @return
     */
    ServiceResult<Log> get(Integer id);
}
