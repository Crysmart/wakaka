package com.szb.platform.workflow.dal.persistence;

import com.szb.platform.commons.tools.tkmapper.TkMapper;
import com.szb.platform.workflow.dal.entity.MainInvoke;
import com.szb.platform.workflow.dal.entity.MainInvokeAfter;
import org.apache.ibatis.annotations.Mapper;

/**
 * 主流程调方法调用之后
 *
 * @author Wang.hm
 * @date Created in 21:15 2020/3/1
 */
@Mapper
public interface MainInvokeAfterMapper extends TkMapper<MainInvokeAfter> {
}
