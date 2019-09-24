package com.iclass.ppt_hw.component.service.api;

import com.iclass.mybatis.vo.WelcomeVo;
import com.iclass.user.component.entity.ServiceResult;

/**
 * iclass
 * <p>
 * Created by JasonTang on 5/14/2017 10:30 AM.
 *
 * 欢迎页面的数据
 */
public interface WelcomeService {

    /**
     * 请求Welcome 页面数据
     * @param userCode
     * 管理员：返回数据库中所有的数据
     * 教师：返回自己的数据
     * @return
     */
    ServiceResult<WelcomeVo> getWelcomeData(String userCode);
}
