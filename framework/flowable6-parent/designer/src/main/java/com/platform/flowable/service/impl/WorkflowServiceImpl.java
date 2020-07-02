package com.platform.flowable.service.impl;

import com.platform.flowable.dal.entity.ResponseResult;
import com.platform.flowable.service.IWorkflowService;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.ui.modeler.domain.Model;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * @author wang.hm
 */
@Service
public class WorkflowServiceImpl implements IWorkflowService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkflowServiceImpl.class);
    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private ModelService modelService;

    /**
     * 直接通过de_model进行流程部署
     *
     * @return
     */
    @Override
    public Deployment deployModel(String modelId) throws UnsupportedEncodingException {
        Model model = modelService.getModel(modelId);
        byte[] bpmnXML = modelService.getBpmnXML(model);
        if (bpmnXML == null) {
            throw new NullPointerException("模型数据为空，请先设计流程并成功保存再进行发布！");
        }
        BpmnModel bpmnModel = modelService.getBpmnModel(model);
        byte[] bytes = new BpmnXMLConverter().convertToXML(bpmnModel);
        if (bpmnModel.getProcesses().size() == 0) {
            throw new IllegalArgumentException("请至少设计一条主线流程！");
        }
        String processName = model.getName() + ".bpmn20.xml";
        RepositoryService repositoryService = processEngine.getRepositoryService();

        Deployment deploy = repositoryService.createDeployment()
                .name(model.getName())
                .addString(processName, new String(bytes, "UTF-8"))
                .deploy();
        LOGGER.info("部署成功，部署Id:{},名称:{}", deploy.getId(), deploy.getName());
        return deploy;
    }
}
