package controller;

import com.google.common.collect.Lists;
import com.szb.platform.workflow.WorkflowApplication;
import com.szb.platform.workflow.service.IApproveService;
import com.szb.platform.workflow.service.IMoveService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 审批测试
 * @author wang.hm
 * @date 2020/3/4 10:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WorkflowApplication.class)
public class ApproveControllerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApproveControllerTest.class);

    @Autowired
    private IApproveService iApproveService;

    /**
     * 添加审批人员测试
     */
    @Test
    public void addApproves(){
        List<String> names = Lists.newArrayList("张三","李四","王二麻子");
        String processInstanceId = "313ad84a-5dc2-11ea-b589-309c234a61ea";
        String processInstanceBusinessKey = "aproveBusKey";
        iApproveService.addApproves(processInstanceId,processInstanceBusinessKey , names);
    }
}
