package com.szb.platform.workflow.service;

import com.szb.platform.workflow.dal.entity.Vars;

import java.util.List;

/**
 * @author wang.hm
 */
public interface IVariablePoolService {

    /**
     * 批量插入
     * @param varsEntities 变量池对象
     */
    void insertBatch(List<Vars> varsEntities);

    /**
     * 通过项目id获取
     * @param projectId 项目id
     * @return
     */
    List<Vars> selectVarsByProjectId(Long projectId);
}
