package com.iclass.user.component.controller;

import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.service.api.RoleService;
import com.iclass.user.mybatis.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/12/2017 6:54 PM.
 */
@RestController
@RequestMapping("/user")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/getRole", produces = "application/json", method = {RequestMethod.POST, RequestMethod.GET})
    public ServiceResult<List<Role>> getRole(String device) {

        ServiceResult<List<Role>> serviceResult = roleService.getRoleName(device);

        return serviceResult;
    }
}
