package com.szb.platform.workflow.service.impl;

import com.google.common.collect.Lists;
import com.szb.platform.workflow.service.IProcessInstanceQueryService;
import com.szb.platform.workflow.vo.HiTaskProcessVO;
import com.szb.platform.workflow.vo.TaskVo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.flowable.common.engine.api.management.TableMetaData;
import org.flowable.engine.*;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 查询
 *
 * @author Wang.hm
 * @date Created in 14:03 2020/2/9
 */
@Service
public class ProcessInstanceQueryServiceImpl implements IProcessInstanceQueryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessInstanceQueryServiceImpl.class);

    @Autowired
    private ProcessEngine processEngine;

    /**
     * 获取指定业务实例任务
     *
     * @param processInstanceBusinessKey 业务标识
     * @param currentPage                当前页
     * @param pageSize                   每页大小
     * @return
     */
    @Override
    public List<TaskVo> getTaskListByBusKey(String processInstanceBusinessKey, int currentPage, int pageSize) {
        int firstResult = (currentPage - 1) * pageSize;
        List<Task> taskList = processEngine.getTaskService().createTaskQuery()
                .processInstanceBusinessKey(processInstanceBusinessKey)
                .listPage(firstResult, pageSize);
        LOGGER.info("taskList = [{}]", taskList);
        return getTaskVoList(taskList);
    }

    /**
     * 获取当前组实例任务
     * @param candidateGroup 分组
     * @param processInstanceBusinessKey 业务标识
     * @param currentPage 当前页
     * @param pageSize 每页大小
     * @return
     */
    @Override
    public List<TaskVo> getTaskListByGroup(String candidateGroup, String processInstanceBusinessKey, int currentPage, int pageSize) {
        int firstResult = (currentPage - 1) * pageSize;
        List<Task> taskList = processEngine.getTaskService().createTaskQuery()
                .taskCandidateGroup(candidateGroup)
                .processInstanceBusinessKey(processInstanceBusinessKey)
                .listPage(firstResult, pageSize);
        LOGGER.info("taskList = [{}]", taskList);
        return getTaskVoList(taskList);
    }

    /**
     * 当前人所拥有的任务
     *
     * @param owner                      拥有者
     * @param processInstanceBusinessKey 业务表示
     * @param currentPage                当前页
     * @param pageSize                   每页大小
     * @return
     */
    @Override
    public List<TaskVo> getTaskListByOwner(String owner, String processInstanceBusinessKey, int currentPage, int pageSize) {
        TaskService taskService = processEngine.getTaskService();
        int firstResult = (currentPage - 1) * pageSize;
        List<Task> taskList = taskService.createTaskQuery()
                .processInstanceBusinessKey(processInstanceBusinessKey)
                .taskOwner(owner)
                .listPage(firstResult, pageSize);
        LOGGER.info("taskList = [{}]", taskList);
        return getTaskVoList(taskList);
    }

    /**
     * 当前角色的当前人任务
     * @param candidateGroup 分组
     * @param owner 拥有者
     * @param processInstanceBusinessKey 业务表示
     * @param currentPage 当前页
     * @param pageSize 每页大小
     * @return
     */
    @Override
    public List<TaskVo> getTaskListByGroupOwner(String candidateGroup, String owner, String processInstanceBusinessKey, int currentPage, int pageSize) {
        TaskService taskService = processEngine.getTaskService();
        int firstResult = (currentPage - 1) * pageSize;
        List<Task> taskList = taskService.createTaskQuery()
                .taskCandidateGroup(candidateGroup)
                .processInstanceBusinessKey(processInstanceBusinessKey)
                .taskOwner(owner)
                .listPage(firstResult, pageSize);
        LOGGER.info("taskList = [{}]", taskList);
        return getTaskVoList(taskList);
    }

    /**
     * 通过实例id获取当前任务
     * @param processInstanceId 实例id
     * @param taskId 任务id
     * @return
     */
    @Override
    public TaskVo getTaskById(String processInstanceId, String taskId) {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        String businessKey = processInstance.getBusinessKey();
        String processInstanceName = processInstance.getName();
        LOGGER.info("processInstance[businessKey:{},processInstanceName:{}]", businessKey,processInstanceName);
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .taskId(taskId)
                .singleResult();
        LOGGER.info("task:[{}]", task);
        return getTaskVo(task);
    }

    /**
     * 当前人是否拥有此任务
     * @param processInstanceId 实例id
     * @param taskId 任务id
     * @param owner 拥有者
     * @return
     */
    @Override
    public TaskVo getTaskByOwner(String processInstanceId, String taskId, String owner) {
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .taskOwner(owner)
                .taskId(taskId)
                .processInstanceId(processInstanceId)
                .singleResult();
        LOGGER.info("task:[{}]",task);
        return getTaskVo(task);
    }

    /**
     * 节点历史进度
     * @param processInstanceId 实例id
     * @return
     */
    @Override
    public List<HiTaskProcessVO> getHiTaskListByProcessInstanceId(String processInstanceId) {
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .orderByTaskCreateTime()
                .desc()
                .list();
        List<HiTaskProcessVO> vos = getHiTaskProcessVOList(processInstanceId, list);
        //流程完成
        ProcessInstance processInstance = processEngine.getRuntimeService().createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        if (processInstance == null){
            HiTaskProcessVO hiTaskProcessVO = vos.get(0);
            HiTaskProcessVO build = HiTaskProcessVO.builder()
                    .createTime(hiTaskProcessVO.getEndTime())
                    .description("订单完成")
                    .processInstanceId(processInstanceId)
                    .taskName("结束")
                    .build();
            vos.add(0, build);
        }
        return vos;
    }

    /**
     * 通过参数查询同类型流程
     *
     * @param processInstanceBusinessKey
     * @param arg
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public List<TaskVo> getBusinessTaskByArg(String processInstanceBusinessKey, String arg,int currentPage, int pageSize) {
        int firstResult = (currentPage - 1) * pageSize;
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery()
                .processVariableExists(arg)
                .listPage(firstResult, pageSize);
        return getTaskVoList(tasks);
    }

    /**
     * 挂起流程
     *
     * @param processInstanceId
     */
    @Override
    public void suspendProcessInstanceById(String processInstanceId) {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        runtimeService.suspendProcessInstanceById(processInstanceId);
    }

    /**
     * 激活流程
     *
     * @param processInstanceId
     */
    @Override
    public void activateProcessInstanceById(String processInstanceId) {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        runtimeService.activateProcessInstanceById(processInstanceId);
    }

    /**
     * 转换单个Task
     * @param task
     * @return
     */
    private TaskVo getTaskVo(Task task) {
        return TaskVo.builder()
                .taskId(task.getId())
                .taskName(task.getName())
                .taskDefinitionKey(task.getTaskDefinitionKey())
                .createTime(task.getCreateTime())
                .description(task.getDescription())
                .processInstanceId(task.getProcessInstanceId())
                .isSuspended(task.isSuspended())
                .owner(task.getOwner())
                .assignee(task.getAssignee())
                .build();
    }

    /**
     * 转换多个task
     * @param taskList
     * @return
     */
    private List<TaskVo> getTaskVoList(List<Task> taskList){
        List<TaskVo> taskVoList = Lists.newArrayList();
        for (Task task : taskList) {
            taskVoList.add(getTaskVo(task));
        }
        return taskVoList;
    }

    /**
     * 转换单个HiTask
     * @param processInstanceId
     * @param historicTaskInstance
     * @return
     */
    private HiTaskProcessVO getHiTaskProcessVO(String processInstanceId, HistoricTaskInstance historicTaskInstance) {
        return HiTaskProcessVO.builder()
                .taskId(historicTaskInstance.getId())
                .taskName(historicTaskInstance.getName())
                .description(historicTaskInstance.getDescription())
                .processInstanceId(processInstanceId)
                .createTime(historicTaskInstance.getCreateTime())
                .endTime(historicTaskInstance.getEndTime())
                .taskDefinitionKey(historicTaskInstance.getTaskDefinitionKey())
                .build();
    }

    /**
     * 转换多个HiTask
     * @param processInstanceId
     * @param historicTaskInstanceList
     * @return
     */
    private List<HiTaskProcessVO> getHiTaskProcessVOList(String processInstanceId, List<HistoricTaskInstance> historicTaskInstanceList){
        List<HiTaskProcessVO> hiTaskProcessVOList = Lists.newArrayList();
        for (HistoricTaskInstance historicTaskInstance : historicTaskInstanceList) {
            hiTaskProcessVOList.add(getHiTaskProcessVO(processInstanceId,historicTaskInstance));
        }
        return hiTaskProcessVOList;
    }

}
