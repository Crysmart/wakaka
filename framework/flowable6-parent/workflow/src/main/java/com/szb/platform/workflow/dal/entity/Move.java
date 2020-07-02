package com.szb.platform.workflow.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 节点跳转实体
 *
 * @author Wang.hm
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ac_ru_move")
public class Move implements Serializable {
    /** 主键 */
    private Long id;
    /** 实例id */
    private String procInstId;
    /** 来自节点id */
    private String fromId;
    /** 来自节点名称 */
    private String fromName;
    /** 去往节点id */
    private String toId;
    /** 去往节点名称 */
    private String toName;
    /** 执行是否成功 */
    private Boolean success;
    /** 原因 */
    private String reason;
}
