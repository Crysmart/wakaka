package com.szb.platform.workflow.engine.util;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.ProcessEngines;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/**
 * 流程引擎工具类
 * @author Wang.hm
 * @date 2019年12月16日 14点06分
 */
public class DefaultProcessEngineUtil {

    private static ProcessEngine processEngine;

    /** 图片后缀 */
    private static final String SUFFIX = "png";
    /** 字体格式设置 */
    private static final String FONT = "宋体";
    /** 是否显示网关条件描述 */
    private static final Boolean GATEWAY = true;
    /** 是否展示网关条件 */
    private static final Double SCALE_FACTOR = 1.0;

    /**
     * 获取流程对象
     * @param processInstanceId 流程实例id
     */
    public static ProcessInstance getProcessInstance(String processInstanceId){
        return ProcessEngines.getDefaultProcessEngine()
                .getRuntimeService()
                .createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
    }

    /**
     * 获取BPMN模型
     * @param processInstanceId 流程实例id
     * @return BpmnModel
     */
    public static BpmnModel getBpmnModel(String processInstanceId){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        ProcessInstance processInstance = defaultProcessEngine.getRuntimeService()
                .createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        if (processInstance == null){
            throw new NullPointerException("ProcessInstance is null,the process was over");
        }
        return defaultProcessEngine.getRepositoryService()
                .getBpmnModel(processInstance.getProcessDefinitionId());
    }

    /**
     * 制作实例历史活动追踪图
     * @param bpmnModel BPMN模型
     * @param activityIds 所有执行实例id
     * @param activityFlowIds 所有执行实例线（应该是历史执行线ids）
     */
    public static InputStream drawGenerateDiagram(BpmnModel bpmnModel, List<String> activityIds, List<String> activityFlowIds){
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngines.getDefaultProcessEngine()
                .getProcessEngineConfiguration();
        ProcessDiagramGenerator processDiagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        //制作图表
        return processDiagramGenerator.generateDiagram(bpmnModel
                , SUFFIX
                , activityIds
                , activityFlowIds
                , FONT, FONT, FONT
                , processEngineConfiguration.getClassLoader()
                , SCALE_FACTOR
                , GATEWAY
        );
    }

}
