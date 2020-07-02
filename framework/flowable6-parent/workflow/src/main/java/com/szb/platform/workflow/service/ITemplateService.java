package com.szb.platform.workflow.service;

import org.flowable.engine.task.Comment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author Wang.hm
 * @date 2019年12月16日 17点53分
 */
public interface ITemplateService {

    /**
     * 加载BPMN应用
     * @param file 文件 .zip或者.bar的流程设计
     */
    void loadApp(MultipartFile file) throws IOException;

    /**
     * 开始BPMN应用
     * @param processInstanceName 流程名称
     * @param processDefinitionId 流程定义id
     * @param processDefinitionKey 流程定义key
     * @param businessKey 业务标识key
     * @param variables 参数集合
     * @return processInstanceId 实例id
     */
    String startApp(String processInstanceName
            , String processDefinitionId
            , String processDefinitionKey
            , String businessKey
            , Map<String,Object> variables);

    /**
     * 完成节点
     * @param taskId 节点任务id
     * @param processInstanceId 实例id
     * @return Boolean
     */
    void completeTask(String taskId,String processInstanceId);

    /**
     * 获取流程图表
     * @param processInstanceId 流程实例id
     * @param processDefinitionId 流程定义id
     * @return InputStream 字节流
     */
    InputStream getPngDiagram(String processInstanceId,String processDefinitionId);

    /**
     * 节点评论
     * @param processInstanceId 流程实例id
     * @param taskId 节点任务id
     * @param message 节点消息
     */
    void addTaskComment(String processInstanceId, String taskId,String message);

    /**
     * 获取流程评论
     * @param processInstanceId 流程实例id
     * @return List
     */
    List<Comment> getProcessComments(String processInstanceId);

    /**
     * 设置拥有者
     * @param taskId
     * @param userId
     */
    void setOwner(String taskId,String userId);

    /**
     * 加载BPMN
     * @param file 文件
     * @param name 名称
     */
    void loadBpmn(MultipartFile file,String name) throws IOException;

    /**
     * 设置代理人
     * @param taskId
     * @param userId
     */
    void setAssignee(String taskId, String userId);

    /**
     * 移动节点
     * @param currentNodeId
     * @param newNodeId
     * @param processInstanceId
     * @param processDefinitionId
     * @param processId
     * @return
     */
    boolean moveActivity(String currentNodeId, String newNodeId, String processInstanceId, String processDefinitionId, String processId);


}
