package com.szb.platform.workflow.dal.persistence;

import com.szb.platform.commons.tools.tkmapper.TkMapper;
import com.szb.platform.workflow.dal.entity.Vars;
import org.apache.ibatis.annotations.Mapper;

/**
 * 变量池
 * @author wang.hm
 */
@Mapper
public interface VariablePoolMapper extends TkMapper<Vars> {
}
