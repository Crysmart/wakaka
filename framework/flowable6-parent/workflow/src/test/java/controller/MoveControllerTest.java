package controller;

import com.szb.platform.workflow.WorkflowApplication;
import com.szb.platform.workflow.service.IMoveService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 跳转节点
 *
 * @author Wang.hm
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WorkflowApplication.class)
public class MoveControllerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MoveControllerTest.class);
    @Autowired
    private IMoveService iMoveService;

    @Test
    public void invoke(){
        try {
            iMoveService.invokeChild("1","sid_123");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
