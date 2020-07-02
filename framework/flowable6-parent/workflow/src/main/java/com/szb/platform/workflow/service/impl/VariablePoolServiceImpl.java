package com.szb.platform.workflow.service.impl;

import com.szb.platform.workflow.dal.entity.Vars;
import com.szb.platform.workflow.dal.persistence.VariablePoolMapper;
import com.szb.platform.workflow.service.IVariablePoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 变量池操作
 *
 * @author Wang.hm
 * @date Created in 22:05 2020/2/11
 */
@Service
public class VariablePoolServiceImpl implements IVariablePoolService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VariablePoolServiceImpl.class);

    @Autowired
    private VariablePoolMapper variablePoolMapper;

    @Override
    public void insertBatch(List<Vars> varsEntities) {
        variablePoolMapper.insertList(varsEntities);
    }

    @Override
    public List<Vars> selectVarsByProjectId(Long projectId) {
        Example example = new Example(Vars.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId",projectId);
        List<Vars> varsEntities = variablePoolMapper.selectByExample(example);
        LOGGER.info("projectId:[{}],查询到的变量:[{}]", projectId,varsEntities.toString());
        return varsEntities;
    }
}
