package com.iclass.ppt_hw.component.service.api;

import com.iclass.mybatis.dto.ClasshdDTO;
import com.iclass.mybatis.po.Classhd;
import com.iclass.mybatis.vo.ClasshdVo;
import com.iclass.user.component.entity.DataTablesRequestEntity;
import com.iclass.user.component.entity.ServiceResult;
import com.iclass.user.component.msg.ResponseMsg;

import java.util.List;

/**
 * iclass
 * <p>
 * Created by JasonTang on 4/14/2017 3:49 PM.
 */
public interface ClasshdService {

    /**
     * 根据教师编号获取教师所创建的互动内容
     * @param requestEntity
     * @param classhdCreator
     * @param isLimit web端需要分页, app端不需要分页
     * @return 返回状态为1的互动数据
     */
    ServiceResult<List<ClasshdDTO>> getAll(DataTablesRequestEntity requestEntity, String classhdCreator, Boolean isLimit);

    /**
     * 检查学生作答的答案
     * @param classhdId
     * @param studentCode
     * @param answer
     * @return
     */
    ServiceResult<ResponseMsg> checkAnswer(Integer classhdId, String studentCode, String answer);

    /**
     * 添加课堂互动内容
     * @param classhd
     * @param classCode
     * @param courseCode
     * @return
     */
    ServiceResult<ResponseMsg> save(Classhd classhd, String classCode, String courseCode);

    /**
     * 删除课堂互动内容
     * @param classhdid
     * @return
     */
    ServiceResult<ResponseMsg> del(Integer classhdid);

    /**
     * 更新课堂互动内容
     * @param classhd
     * @return
     */
    ServiceResult<ResponseMsg> update(Classhd classhd);

    /**
     * 根据课堂互动id来获取课堂互动的内容
     * @param classhdId
     * @return
     */
    ServiceResult<ClasshdVo> get(Integer classhdId);

    /**
     * 更新课堂互动内容的状态
     * @param classhd
     * @param status
     * @return
     */
    ServiceResult<ResponseMsg> updateStatus(Classhd classhd, int status);
}
