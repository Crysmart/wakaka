package com.szb.platform.workflow.dal.persistence;

import com.szb.platform.commons.tools.tkmapper.TkMapper;
import com.szb.platform.workflow.dal.entity.ChildInvoke;
import com.szb.platform.workflow.dal.entity.ChildInvokeAfter;
import org.apache.ibatis.annotations.Mapper;

/**
 * 子流程调方法调用之后
 *
 * @author Wang.hm
 * @date Created in 21:15 2020/3/1
 */
@Mapper
public interface ChildInvokeAfterMapper extends TkMapper<ChildInvokeAfter> {
}
