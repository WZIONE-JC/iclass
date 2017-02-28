package com.iclass.user.component.service.impl;

import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.service.api.RoleService;
import com.iclass.user.mybatis.dao.RoleMapper;
import com.iclass.user.mybatis.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleMapper roleMapper;
    /**
     * 描述：从数据库中获取有哪些角色信息
                * 通过：通过jsonp的方式来交互数据
                * @param device : 表示设备，有web 和 app
                */
        @Override
        public ServiceResult<List<Role>> getRoleName(String device) {
            ServiceResult<List<Role>> serviceResult = new ServiceResult<>();
            if(device!= null && !device.equals("")) {
                List<Role> roles = roleMapper.findRoleNameByDevice(device);
                logger.info("通过设备标识来获取角色信息成功:"+roles);
                serviceResult.setSuccess(true);
                serviceResult.setData(roles);
            } else {
                logger.error("通过设备标识来获取角色信息时,device参数不能为空");
                serviceResult.setMessage("通过设备标识来获取角色信息时,device参数不能为空");
                return serviceResult;
            }
            return serviceResult;
    }
}
