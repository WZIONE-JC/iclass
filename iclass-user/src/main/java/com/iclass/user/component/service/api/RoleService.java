package com.iclass.user.component.service.api;

import com.iclass.mybatis.po.Role;
import com.iclass.user.component.entity.ServiceResult;

import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/12/2017 5:41 PM.
 */
public interface RoleService {

    /**
     * 获取角色信息
     * @param device 设备表示 app/web
     * @return 返回角色jsonp
     */
    public ServiceResult<List<Role>> getRoleName(String device);
}
