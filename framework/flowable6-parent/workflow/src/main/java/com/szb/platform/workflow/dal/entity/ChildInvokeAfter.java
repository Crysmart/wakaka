package com.szb.platform.workflow.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * 调用过的进度方法，子流程
 *
 * @author Wang.hm
 */
@Table(name = "ac_hi_child_invoke")
public class ChildInvokeAfter extends ChildInvoke {
}
