package com.iclass.user.component.service.impl;

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
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    /**
     * 描述：从数据库中获取有哪些角色信息
     * 通过：通过jsonp的方式来交互数据
     * 格式：jQuery191009626402738114481_1486911217796({"rolenames":[{"role":"教师"},{"role":"管理员"}],"size":"2"})
     * @param device : 表示设备，有web 和 app
     */
    @Override
    public String getRoleName(String device, String callback) {
        List<Role> roles = roleMapper.findRoleNameByDevice(device);
        System.out.println("RoleServiceImpl.getRoleName: " + roles.size());
        StringBuilder json = new StringBuilder();
        StringBuilder jsonp = new StringBuilder();
        if(roles.size() > 0) {
            //拼装为json数据
            jsonp.append(callback).append("(");
            json.append("{\"rolenames\":[");
            for (int i = 0; i < roles.size(); i++) {
                json.append("{\"").append("role").append("\":\"").
                        append(roles.get(i).getRolename()).append("\"},");
            }
            //去掉最后的 逗号
            json.deleteCharAt(json.length()-1);
            json.append("],");
            json.append("\"size\":").append("\"").append(roles.size()).append("\"}");
            jsonp.append(json).append(")");
        }
        System.out.println(jsonp.toString());
        return jsonp.toString();
    }
}
