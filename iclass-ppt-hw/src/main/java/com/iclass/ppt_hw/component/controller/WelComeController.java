package com.iclass.ppt_hw.component.controller;

import com.iclass.mybatis.vo.WelcomeVo;
import com.iclass.ppt_hw.component.service.api.WelcomeService;
import com.iclass.user.component.entity.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * iclass
 * <p>
 * Created by JasonTang on 5/16/2017 10:52 AM.
 */
@RestController
@RequestMapping("/welcome")
public class WelComeController {

    @Autowired
    private WelcomeService welcomeService;

    /**
     * 请求Welcome 页面数据
     * @param userCode
     * 管理员：返回数据库中所有的数据
     * 教师：返回自己的数据
     * @return
     */
    @RequestMapping("/get")
    ServiceResult<WelcomeVo> getWelcomeData(String userCode) {

        return welcomeService.getWelcomeData(userCode);
    }
}
