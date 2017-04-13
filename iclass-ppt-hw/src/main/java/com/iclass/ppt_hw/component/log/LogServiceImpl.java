package com.iclass.ppt_hw.component.log;

import com.iclass.mybatis.dao.AdminMapper;
import com.iclass.mybatis.dao.LogMapper;
import com.iclass.mybatis.po.Admin;
import com.iclass.mybatis.po.Log;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.Msg;
import com.iclass.user.component.msg.ResponseMsg;
import com.iclass.user.component.utils.CheckDataTables;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 4/11/2017 2:08 PM.
 */
@Service("logService")
public class LogServiceImpl implements LogService{

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private LogMapper logMapper;

    @Override
    public ServiceResult<List<Log>> getAll(DataTablesRequestEntity requestEntity, String userCode) {
        ServiceResult<List<Log>> serviceResult = new ServiceResult<>();
        if (StringUtils.isBlank(userCode)) {
            serviceResult.setMessage("用户未登录");
            return serviceResult;
        }
        Admin admin = adminMapper.selectByAdminCode(userCode);

        if (admin == null) {
            serviceResult.setMessage("您没有权限");
            return serviceResult;
        }

        requestEntity = CheckDataTables.check(requestEntity);

        Integer draw = requestEntity.getDraw();
        Integer start = requestEntity.getStart();
        Integer length = requestEntity.getLength();
        Integer count = logMapper.countLogRecord();

        List<Log> logList = logMapper.selectAll(start, length);
        serviceResult.setData(logList);
        serviceResult.setSuccess(true);
        serviceResult.setRecordsFiltered(count);
        serviceResult.setRecordsTotal(count);
        serviceResult.setDraw(draw);
        return serviceResult;
    }

    @Override
    public ServiceResult<ResponseMsg> del(Integer id) {
        ServiceResult<ResponseMsg> serviceResult = new ServiceResult<>();
        logMapper.deleteByPrimaryKey(id);
        serviceResult.setData(new ResponseMsg(Msg.OK));
        serviceResult.setSuccess(true);
        return serviceResult;
    }

    @Override
    public ServiceResult<Log> get(Integer id) {
        ServiceResult<Log> serviceResult = new ServiceResult<>();
        Log log = logMapper.selectByPrimaryKey(id);
        serviceResult.setData(log);
        serviceResult.setSuccess(true);
        return serviceResult;
    }
}
