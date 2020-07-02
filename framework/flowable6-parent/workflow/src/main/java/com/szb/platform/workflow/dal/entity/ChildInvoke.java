package com.szb.platform.workflow.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * 调用进度方法,子流程
 *
 * @author Wang.hm
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ac_re_child_invoke")
public class ChildInvoke implements Serializable {
    /** 主键 */
    private Long id;
    /** 节点id */
    private String taskDefKey;
    /** 调用的类 */
    private String invokeClass;
    /** 调用的方法 */
    private String invokeMethod;
    /** 实例id */
    private String procInstId;
}
