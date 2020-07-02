package com.szb.platform.workflow.service.impl;

import com.szb.platform.workflow.controller.ProcessInstanceQueryController;
import com.szb.platform.workflow.service.IApproveService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wang.hm
 * @date 2020/3/4 11:03
 */
@Service
public class ApproveServiceImpl implements IApproveService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApproveServiceImpl.class);
    @Autowired
    private ProcessEngine processEngine;

    /**
     * 添加审批人
     */
    @Override
    public void addApproves(String processInstanceId, String processInstanceBusinessKey,List<String> names) {
        TaskService taskService = processEngine.getTaskService();
        List<Task> taskList = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .processInstanceBusinessKey(processInstanceBusinessKey)
                .list();
        if (taskList.size() == names.size()) {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                taskService.setOwner(task.getId(),names.get(i));
                LOGGER.info("当前任务[{}],拥有者为[{}]",task.getName(),names.get(i));
            }
        }
    }
}
