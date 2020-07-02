package controller;

import com.google.common.collect.Lists;
import com.szb.platform.workflow.WorkflowApplication;
import com.szb.platform.workflow.dal.entity.Vars;
import com.szb.platform.workflow.service.IVariablePoolService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

/**
 * 变量池测试
 *
 * @author Wang.hm
 * @date Created in 17:08 2020/2/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WorkflowApplication.class)
public class VariablePoolControllerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(VariablePoolControllerTest.class);

    @Autowired
    private IVariablePoolService iVariablePoolService;
    @Test
    public void insertBatch(){
        ArrayList<Vars> varsEntities = Lists.newArrayList(Vars.builder().id(123L).build());
        iVariablePoolService.insertBatch(varsEntities);
    }

    @Test
    public void selectVarsByProjectId(){
        iVariablePoolService.selectVarsByProjectId(123L);
    }

}
