package com.szb.platform.workflow.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 当前任务
 *
 * @author Wang.hm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskVo implements Serializable {
    /** 任务id */
    private String taskId;
    /** 任务名称 */
    private String taskName;
    /** 实例id */
    private String processInstanceId;
    /** 创建时间 */
    private Date createTime;
    /** 描述 */
    private String description;
    /** 节点定义id */
    private String taskDefinitionKey;
    /** 是否挂起 */
    private Boolean isSuspended;
    /** 拥有者 */
    private String owner;
    /** 代理人 */
    private String assignee;
}
