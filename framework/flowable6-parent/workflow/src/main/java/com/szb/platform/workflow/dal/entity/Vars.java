package com.szb.platform.workflow.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 变量
 *
 * @author Wang.hm
 * @date Created in 22:06 2020/2/11
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ac_ru_vars")
public class Vars implements Serializable {
    /** 主键 */
    private Long id;
    /** 项目id */
    private Long projectId;
    /** 实例id */
    private String procInstId;
    /** 执行实例id */
    private String executionId;
    /** 键 */
    private String keyName;
    /** 值 */
    private String keyValue;

}
