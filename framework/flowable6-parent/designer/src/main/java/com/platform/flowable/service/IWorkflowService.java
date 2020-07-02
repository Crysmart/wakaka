package com.platform.flowable.service;

import org.flowable.engine.repository.Deployment;

import java.io.UnsupportedEncodingException;

/**
 * @author wang.hm
 * @date 2020/3/9 10:27
 */
public interface IWorkflowService {

    /**
     * 直接通过de_model进行流程部署
     * @param modelId 模块id
     * @return
     */
    Deployment deployModel(String modelId) throws UnsupportedEncodingException;
}
