package com.iclass.user.component.service.impl;

import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.service.api.RoleService;
import com.iclass.user.mybatis.dao.RoleMapper;
import com.iclass.user.mybatis.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/12/2017 5:42 PM.
 */
@Service("RoleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    /**
     * 描述：从数据库中获取有哪些角色信息
     * 通过：通过jsonp的方式来交互数据
     * @param device : 表示设备，有web 和 app
     */
    @Override
    public ServiceResult<List<Role>> getRoleName(String device) {
        ServiceResult<List<Role>> serviceResult = new ServiceResult<>(false);
        List<Role> roles = roleMapper.findRoleNameByDevice(device);
        serviceResult.setIsSuccess(true);
        serviceResult.setDataMap(roles);
        return serviceResult;
    }
}
