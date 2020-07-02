package com.szb.platform.workflow.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 历史任务进度
 *
 * @author Wang.hm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HiTaskProcessVO implements Serializable {
    /** 任务id */
    private String taskId;
    /** 任务名称 */
    private String taskName;
    /** 实例id */
    private String processInstanceId;
    /** 创建时间 */
    private Date createTime;
    /** 结束时间 */
    private Date endTime;
    /** 描述 */
    private String description;
    /** 节点定义id */
    private String taskDefinitionKey;
}
