package com.szb.platform.workflow.service.impl;

import com.szb.platform.commons.core.snowflake.SnowFlakeIdUtils;
import com.szb.platform.workflow.dal.entity.ChildInvokeAfter;
import com.szb.platform.workflow.dal.entity.Move;
import com.szb.platform.workflow.dal.persistence.MoveMapper;
import com.szb.platform.workflow.engine.util.DefaultProcessEngineUtil;
import com.szb.platform.workflow.service.ITemplateService;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.task.Comment;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.task.service.impl.persistence.entity.TaskEntityImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.zip.ZipInputStream;


/**
 * 模板操作
 *
 * @author Wang.hm
 * @date 2019年12月16日 17点54分
 */
@Service
public class TemplateServiceImpl implements ITemplateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateServiceImpl.class);
    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private MoveMapper moveMapper;

    @Override
    public void loadApp(MultipartFile file) throws IOException {
        RepositoryService repositoryService = processEngine.getRepositoryService();
                InputStream inputStream = file.getInputStream();
                ZipInputStream zipInputStream = new ZipInputStream(inputStream);
            Deployment deployment = repositoryService.createDeployment()
                    .name(file.getName())
                    .addZipInputStream(zipInputStream)
                    .deploy();
            String deploymentId = deployment.getId();
            String deploymentName = deployment.getName();
            LOGGER.info("部署文件名称:[{}],部署id:[{}],部署name[{}]", file.getName(), deploymentId, deploymentName);
    }

    @Override
    public String startApp(String processInstanceName, String processDefinitionId, String processDefinitionKey, String businessKey, Map<String, Object> variables) {
        try {
            RuntimeService runtimeService = processEngine.getRuntimeService();
            if (processDefinitionId == null) {
                throw new NullPointerException("ACT_RE_PROCDEF:ID_ is null");
            }
            ProcessInstance processInstance = runtimeService.createProcessInstanceBuilder()
                    .businessKey(businessKey)
                    .processDefinitionId(processDefinitionId)
                    .processDefinitionKey(processDefinitionKey)
                    .variables(variables)
                    .name(processInstanceName)
                    .start();
            String processInstanceId = processInstance.getId();
            processInstanceName = processInstance.getName();
            LOGGER.info("实例流程Id:[{}],实例流程name:[{}]", processInstanceId, processInstanceName);
            return processInstanceId;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public void completeTask(String taskId, String processInstanceId) {
        try {
            TaskService taskService = processEngine.getTaskService();
            if (taskId == null) {
                throw new NullPointerException("ACT_RU_TASK:ID_ is null");
            }
            Task task = taskService.createTaskQuery()
                    .taskId(taskId)
                    .processInstanceId(processInstanceId)
                    .singleResult();
            if (task != null) {
                String taskName = task.getName();
                //完成节点
                taskService.complete(taskId);
                HistoricTaskInstance historicTaskInstance = processEngine.getHistoryService().createHistoricTaskInstanceQuery()
                        .taskId(taskId)
                        .singleResult();
                Date createTime = historicTaskInstance.getCreateTime();
                Date endTime = historicTaskInstance.getEndTime();
                LOGGER.info("完成的节点任务名称:[{}],任务创建时间:[{}],任务结束时间:[{}]"
                        , taskName, createTime, endTime);
            } else {
                LOGGER.warn("没有[{}]的任务", taskId);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public InputStream getPngDiagram(String processInstanceId, String processDefinitionId) {
        try {
            RuntimeService runtimeService = processEngine.getRuntimeService();
            RepositoryService repositoryService = processEngine.getRepositoryService();
            // 正在执行的实例列表id
            List<String> acIds = new ArrayList<>();

            // 获取执行实例列表
            List<Execution> list = runtimeService.createExecutionQuery().processInstanceId(processInstanceId).list();
            for (Execution execution : list) {
                List<String> activeActivityIds = runtimeService.getActiveActivityIds(execution.getId());
                acIds.addAll(activeActivityIds);
            }
            // 制作实例历史活动追踪图
            //BpmnModel bpmnModel = DefaultProcessEngineUtil.getBpmnModel(processInstanceId);
            BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
            // 制作图表
            return DefaultProcessEngineUtil.drawGenerateDiagram(bpmnModel, acIds, Collections.emptyList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addTaskComment(String processInstanceId, String taskId, String message) {
        try {
            TaskService taskService = processEngine.getTaskService();
            taskService.addComment(taskId, processInstanceId, message);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Comment> getProcessComments(String processInstanceId) {
        TaskService taskService = processEngine.getTaskService();
        List<Comment> processInstanceComments = taskService
                .getProcessInstanceComments(processInstanceId);
        for (Comment processInstanceComment : processInstanceComments) {
            LOGGER.info("所有评论:[{}]", ToStringBuilder.reflectionToString(processInstanceComment, ToStringStyle.JSON_STYLE));
        }
        return processInstanceComments;
    }

    @Override
    public void setOwner(String taskId, String userId) {
        TaskService taskService = processEngine.getTaskService();
        taskService.setOwner(taskId, userId);
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();
        String owner = task.getOwner();
        String assignee = task.getAssignee();
        LOGGER.info("当前任务拥有者:[{}],代理人:[{}]", owner, assignee);
    }

    @Override
    public void loadBpmn(MultipartFile file, String name) throws IOException {
        DeploymentBuilder deploymentBuilder = processEngine.getRepositoryService().createDeployment();
        Deployment deployment = deploymentBuilder
                .addInputStream(name,file.getInputStream())
                .name(name)
                .deploy();
        String deploymentId = deployment.getId();
        LOGGER.info("部署文件名称:[{}],部署id:[{}],名称[{}]", file.getName(), deploymentId, name);
    }

    @Override
    public void setAssignee(String taskId, String userId) {
        TaskService taskService = processEngine.getTaskService();
        taskService.setAssignee(taskId, userId);
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .singleResult();
        String owner = task.getOwner();
        String assignee = task.getAssignee();
        LOGGER.info("当前任务拥有者:[{}],代理人:[{}]", owner, assignee);
    }

    @Override
    public boolean moveActivity(String currentNodeId, String newNodeId, String processInstanceId, String processDefinitionId, String processId) {
        BpmnModel bpmnModel = processEngine.getRepositoryService().getBpmnModel(processDefinitionId);
        List<Process> processes = bpmnModel.getProcesses();
        Process proc = null;
        for (Process process : processes) {
            if (process.getId().equals(processId)) {
                proc = process;
            }
        }
        if (proc != null) {
            Map<String, FlowElement> flowElementMap = proc.getFlowElementMap();
            String currentNodeName = flowElementMap.get(currentNodeId).getName();
            String newNodeName = flowElementMap.get(newNodeId).getName();
            //构建实体
            Move moveEntity = Move.builder()
                    .id(SnowFlakeIdUtils.nextId())
                    .fromId(currentNodeId)
                    .fromName(currentNodeName)
                    .toId(newNodeId)
                    .toName(newNodeName)
                    .procInstId(processInstanceId)
                    .build();
            try {
                //节点跳转
                RuntimeService runtimeService = processEngine.getRuntimeService();
                runtimeService.createChangeActivityStateBuilder()
                        .processInstanceId(processInstanceId)
                        .moveActivityIdTo(currentNodeId, newNodeId)
                        .changeState();
                moveEntity.setSuccess(true);
            } catch (Exception e) {
                moveEntity.setSuccess(false);
                moveEntity.setReason(e.getMessage());
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
            try {
                moveMapper.insert(moveEntity);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            }
        }
        return false;
    }


}
