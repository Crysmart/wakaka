package controller;

import com.google.common.collect.Maps;
import com.szb.platform.commons.tools.util.FileUtils;
import com.szb.platform.workflow.WorkflowApplication;
import com.szb.platform.workflow.service.ITemplateService;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.MessageFlow;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.*;
import org.flowable.engine.form.FormProperty;
import org.flowable.engine.form.TaskFormData;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.task.Comment;
import org.flowable.engine.test.FlowableRule;
import org.flowable.identitylink.api.IdentityLink;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WorkflowApplication.class)
public class TemplateControllerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateControllerTest.class);

    @Rule
    public FlowableRule flowableRule = new FlowableRule();

    @Autowired
    private ITemplateService iTemplateService;


    /**
     * 部署应用
     */
    @Test
    public void loadApp() {
        try {
            String fileName = "workflowApp/量房程序.zip";
            MultipartFile multipartFile = FileUtils.fileToMultipartFile("D:\\Workspace\\Java\\szb-platform\\szb-platform-workflow\\src\\test\\resources\\" + fileName);
            iTemplateService.loadApp(multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 启动流程应用
     */
    @Test
    public void startApp() {
        String s = iTemplateService.startApp("量房订单"
                , "process:1:61da32cb-4feb-11ea-8244-7a0cb83a4486"
                , "measure1Key"
                , "measure1BusinessKey"
                , null);
        System.out.println("s = " + s);
    }

    /**
     * 服务任务测试
     */
    @Test
    @org.flowable.engine.test.Deployment(resources = "bpmnTest/testServiceTask.bpmn20.xml")
    public void serviceTask(){
        ProcessDefinition processDefinition = flowableRule.getRepositoryService().createProcessDefinitionQuery().singleResult();
        ProcessInstance processInstance = flowableRule.getRuntimeService().startProcessInstanceById(processDefinition.getId());
        Task task = flowableRule.getTaskService().createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
        HashMap<String,Object> map = Maps.newHashMap();
        //map.put("myBean", new MyBean());
        //flowableRule.getTaskService().complete(task.getId(),map);
    }

    /**
     * 定时任务测试
     */
    @Test
    @org.flowable.engine.test.Deployment(resources = "bpmnTest/testTimer.bpmn20.xml")
    public void timerStart(){
        ProcessDefinition processDefinition = flowableRule.getRepositoryService().createProcessDefinitionQuery().singleResult();
        ProcessInstance processInstance = flowableRule.getRuntimeService().startProcessInstanceById(processDefinition.getId());
    }

    /**
     * 节点完成
     */
    @Test
    public void complete() {
        Map<String, Object> vars = Maps.newHashMap();
        iTemplateService.completeTask("666bbec4-5dcc-11ea-a15a-309c234a61ea"
                , "313ad84a-5dc2-11ea-b589-309c234a61ea");
    }

    @Test
    public void function(){
        List<HistoricTaskInstance> list = flowableRule.getHistoryService().createHistoricTaskInstanceQuery().list();
        for (HistoricTaskInstance historicTaskInstance : list) {
            System.out.println("historicTaskInstance.getTaskDefinitionId() = " + historicTaskInstance.getTaskDefinitionId());
        }


        BpmnModel bpmnModel = flowableRule.getRepositoryService().getBpmnModel("measureTaskProcess:1:f21e7086-5643-11ea-98d9-7a0cb83a4486");
        List<Process> processes = bpmnModel.getProcesses();
        for (Process process : processes) {
        }
    }

    /**
     * 请假审批测试
     */
    @Test
    @org.flowable.engine.test.Deployment(resources = "bpmnTest/approve.bpmn20.xml")
    public void loadBPMNFinish() {
        ProcessEngine processEngine = flowableRule.getProcessEngine();
        LOGGER.info("实例化BPMN文件");
        ProcessInstance runProcessInstance = getProcessInstance(processEngine);
        while (true) {
            //完成任务
            List<Task> taskList = processEngine.getTaskService().createTaskQuery()
                    .processInstanceId(runProcessInstance.getId())
                    .listPage(0, 100);
            if (null == taskList || taskList.isEmpty()) {
                break;
            }
            int size = taskList.size();
            LOGGER.debug("当前任务数:[{}]", size);
            for (Task task : taskList) {
                //完成任务
                this.completeTask(processEngine, size, task);
                //taskListOwner(processEngine, runProcessInstance);
            }
        }
        LOGGER.info("流程结束");
    }

    /**
     * 请假审批测试
     */
    private ProcessInstance getProcessInstance(ProcessEngine processEngine) {
        Deployment deployment = processEngine.getRepositoryService().createDeploymentQuery().singleResult();
        String deployId = deployment.getId();
        ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery()
                .deploymentId(deployId)
                .singleResult();
        String processDefinitionId = processDefinition.getId();
        String processDefinitionName = processDefinition.getName();
        LOGGER.debug("processDefinitionId:[{}],processDefinitionName:[{}]", processDefinitionId, processDefinitionName);
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceById(processDefinitionId);
        LOGGER.debug("processInstanceId:[{}]", processInstance.getId());
        return processEngine.getRuntimeService().createProcessInstanceQuery()
                .processInstanceId(processInstance.getId())
                .singleResult();
    }
    /**
     * 请假审批测试
     */
    private void completeTask(ProcessEngine processEngine, int size, Task task) {
        HashMap<String, Object> variables = Maps.newHashMap();
        variables.put("key", "value");
        variables.put("message", "这是一个替换信息");
        LOGGER.debug("任务开始循环,当前任务名称:[{}],实例id:[{}]", task.getName(), task.getProcessInstanceId());
        TaskFormData taskFormData = processEngine.getFormService().getTaskFormData(task.getId());
        String formKey = taskFormData.getFormKey();
        LOGGER.debug("动态表单的FormKey:[{}]", formKey);
        List<FormProperty> formProperties = taskFormData.getFormProperties();
        for (FormProperty formProperty : formProperties) {
            LOGGER.debug("表单属性id:[{}],表单属性名称:[{}],表单属性类型:[{}]", formProperty.getId(), formProperty.getName(), formProperty.getType().getName());
            //Scanner scanner = new Scanner(System.in);
            String text = "true";
            variables.put(formProperty.getId(), text);
        }
        TaskService taskService = processEngine.getTaskService();
        taskService.addComment(task.getId(), task.getProcessInstanceId(), "message1");
        taskService.addComment(task.getId(), task.getProcessInstanceId(), "message2");
        //当前任务描述
        /*List<Comment> taskComments = taskService.getTaskComments(task.getId());
        for (Comment taskComment : taskComments) {
            LOGGER.info("任务评论属性:[{}]", ToStringBuilder.reflectionToString(taskComment, ToStringStyle.JSON_STYLE));
        }*/

        //流程任务描述（包含所有描述信息）
        List<Comment> processInstanceComments = taskService.getProcessInstanceComments(task.getProcessInstanceId());
        for (Comment processInstanceComment : processInstanceComments) {
            LOGGER.debug("任务评论属性:[{}]", ToStringBuilder.reflectionToString(processInstanceComment, ToStringStyle.JSON_STYLE));
        }
        //完成任务
        taskService.complete(task.getId(), variables);
        LOGGER.debug("任务:[{}] 已完成,剩余待完成数:[{}]", task.getName(), size - 1);

    }
    /**
     * 请假审批测试
     */
    private static void taskListOwner(ProcessEngine processEngine, ProcessInstance runProcessInstance) {
        List<Task> taskList = processEngine.getTaskService().createTaskQuery()
                .processInstanceId(runProcessInstance.getId())
                .taskCandidateGroup("1")
                .listPage(0, 100);
        LOGGER.debug("当前任务查询数:[{}]", taskList.size());

        List<IdentityLink> identityLinksForTask = processEngine.getTaskService().getIdentityLinksForTask(taskList.get(0).getId());
        for (IdentityLink identityLink : identityLinksForTask) {
            LOGGER.debug("身份链接:[{}]", identityLink);
        }

        //只能通过taskId进行设置，在任务内设置只能通过任务内get获取。并没有设置到数据库中
        processEngine.getTaskService().setAssignee(taskList.get(0).getId(), "username");
        processEngine.getTaskService().setOwner(taskList.get(0).getId(), "password");
        List<Task> username = processEngine.getTaskService().createTaskQuery().taskAssignee("username").list();
        for (Task task : username) {
            LOGGER.debug("username的任务:[{}]", task.getName());
        }
    }
}
