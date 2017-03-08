package com.iclass.user.component.service.api;

import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.vo.SessionUser;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/13 15:10.
 *
 * 个人信息维护  服务
 */
public interface PersonalInfoService {

    /**
     * 根据工号获取用户信息
     * @param usercode 工号
     * @return SessionUser
     */
    public ServiceResult<SessionUser> getPersonalInfoByUsercode(String usercode);

    /**
     * 根据sessionId 获取用户信息
     * @param requestEntity 表格请求数据
     * @param session 获取sessionId
     * @return SessionUser
     */
    public ServiceResult<List<SessionUser>> getPersonalInfoBySession(DataTablesRequestEntity requestEntity, HttpSession session);

}
