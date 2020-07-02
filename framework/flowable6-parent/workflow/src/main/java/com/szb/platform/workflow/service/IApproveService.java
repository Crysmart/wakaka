package com.szb.platform.workflow.service;

import java.util.List;

/**
 * 审批
 * @author wang.hm
 * @date 2020/3/4 11:02
 */
public interface IApproveService {

    /**
     * 添加审批人 会签
     * @param processInstanceBusinessKey
     * @param names
     */
    void addApproves(String processInstanceId, String processInstanceBusinessKey, List<String> names);
}
