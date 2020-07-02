package controller;

import com.szb.platform.workflow.WorkflowApplication;
import com.szb.platform.workflow.service.IProcessInstanceQueryService;
import com.szb.platform.workflow.vo.TaskVo;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 量房订单测试
 *
 * @author Wang.hm
 * @date Created in 18:20 2020/2/7
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WorkflowApplication.class)
public class ProcessInstanceQueryControllerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessInstanceQueryControllerTest.class);
    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private IProcessInstanceQueryService iProcessInstanceQueryService;

    @Test
    public void function(){

    }

    /**
     * 获取当前人是否有这个任务
     */
    @Test
    public void getTask() {
        String busKey = "aproveBusKey";
        iProcessInstanceQueryService.getTaskListByBusKey(busKey,0,1);
    }

    @Test
    public void getHiTaskList(){
        iProcessInstanceQueryService
                .getHiTaskListByProcessInstanceId("9a0096de-4feb-11ea-afc2-7a0cb83a4486");
    }

    @Test
    public void getTaskListByOwner(){
        List<TaskVo> businessTaskByArg = iProcessInstanceQueryService
                .getBusinessTaskByArg("15", null, 1, 100);
        System.out.println(businessTaskByArg);
    }

}
