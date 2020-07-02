package com.szb.platform.workflow.service;


import com.szb.platform.workflow.vo.HiTaskProcessVO;
import com.szb.platform.workflow.vo.TaskVo;

import java.util.List;
import java.util.Map;

/**
 * 查询
 *
 * @author Wang.hm
 * @date Created in 14:03 2020/2/9
 */
public interface IProcessInstanceQueryService {

    /**
     * 获取指定业务实例任务
     * @param processInstanceBusinessKey 业务标识
     * @param currentPage 当前页
     * @param pageSize 每页大小
     * @return
     */
    List<TaskVo> getTaskListByBusKey(String processInstanceBusinessKey, int currentPage, int pageSize);

    /**
     * 获取当前组实例任务
     * @param candidateGroup 分组
     * @param processInstanceBusinessKey 业务标识
     * @param currentPage 当前页
     * @param pageSize 每页大小
     * @return
     */
    List<TaskVo> getTaskListByGroup(String candidateGroup, String processInstanceBusinessKey, int currentPage, int pageSize);

    /**
     * 当前人所拥有的任务
     * @param owner 拥有者
     * @param processInstanceBusinessKey 业务表示
     * @param currentPage 当前页
     * @param pageSize 每页大小
     * @return
     */
    List<TaskVo> getTaskListByOwner(String owner, String processInstanceBusinessKey, int currentPage, int pageSize);

    /**
     * 当前角色的当前人任务
     * @param candidateGroup 分组
     * @param owner 拥有者
     * @param processInstanceBusinessKey 业务表示
     * @param currentPage 当前页
     * @param pageSize 每页大小
     * @return
     */
    List<TaskVo> getTaskListByGroupOwner(String candidateGroup, String owner, String processInstanceBusinessKey, int currentPage, int pageSize);

    /**
     * 通过实例id获取当前任务
     * @param processInstanceId 实例id
     * @param taskId 任务id
     * @return
     */
    TaskVo getTaskById(String processInstanceId, String taskId);

    /**
     * 当前人是否拥有此任务
     * @param processInstanceId 实例id
     * @param taskId 任务id
     * @param owner 拥有者
     * @return
     */
    TaskVo getTaskByOwner(String processInstanceId, String taskId, String owner);

    /**
     * 节点历史进度
     * @param processInstanceId 实例id
     * @return
     */
    List<HiTaskProcessVO> getHiTaskListByProcessInstanceId(String processInstanceId);

    /**
     * 通过参数查询同类型流程
     * @param processInstanceBusinessKey
     * @param arg
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<TaskVo> getBusinessTaskByArg(String processInstanceBusinessKey, String arg,int currentPage, int pageSize);

    /**
     * 挂起流程
     * @param processInstanceId
     */
    void suspendProcessInstanceById(String processInstanceId);

    /**
     * 激活流程
     * @param processInstanceId
     */
    void activateProcessInstanceById(String processInstanceId);


}
