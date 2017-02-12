package com.iclass.user.controller;

import com.iclass.user.UserMsg.UserMsg;
import com.iclass.user.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * iclass
 * <p>
 * Created by JasonTang on 2/12/2017 6:54 PM.
 */
@RestController
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @RequestMapping(value = "/getRole", produces = "application/json")
    public String getRole(String device, String callback) {
        UserMsg userMsg;

        String roles = roleService.getRoleName(device, callback);

        return roles;
    }
}
