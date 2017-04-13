package com.iclass.ppt_hw.component.log;

import com.iclass.mybatis.po.Log;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 4/11/2017 3:10 PM.
 */
@RequestMapping("/log")
@RestController
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * 只有管理员才能查看日志数据
     *
     * @param requestEntity
     * @param userCode 只是用来验证权限
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    ServiceResult<List<Log>> getAll(DataTablesRequestEntity requestEntity, String userCode, HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        return logService.getAll(requestEntity, userCode);
    }

    /**
     * 删除日志
     * @param id
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    ServiceResult<ResponseMsg> del(Integer id, HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");


        return logService.del(id);
    }

    /**
     * 获取日志详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
    ServiceResult<Log> get(@Param("id") Integer id, HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        return logService.get(id);
    }
}
