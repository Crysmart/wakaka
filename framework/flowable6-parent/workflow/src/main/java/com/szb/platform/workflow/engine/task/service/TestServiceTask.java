package com.szb.platform.workflow.engine.task.service;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 测试服务任务
 *
 * @author Wang.hm
 */
public class TestServiceTask implements JavaDelegate, Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestServiceTask.class);
    @Override
    public void execute(DelegateExecution execution) {
        LOGGER.info("服务任务触发了");
    }
}
