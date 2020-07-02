package com.szb.platform.workflow.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * 调用进度方法,主流程
 *
 * @author Wang.hm
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ac_re_main_invoke")
public class MainInvoke implements Serializable {
    /** 主键 */
    private Long id;
    /** 节点id */
    private String taskDefKey;
    /** 子流程节点id */
    private String childTaskDefKey;
    /** 实例id */
    private String procInstId;
    /** 调用类全限定名 */
    private String invokeClass;
    /** 调用方法 */
    private String invokeMethod;
    /** 子流程实例id */
    private String childProcInstId;
}
