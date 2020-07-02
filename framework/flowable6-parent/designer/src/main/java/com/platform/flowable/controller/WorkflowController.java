package com.platform.flowable.controller;


import com.platform.flowable.service.IWorkflowService;
import com.szb.platform.commons.core.enums.ResponseCode;
import com.szb.platform.commons.core.result.WebResponse;
import com.szb.platform.commons.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wang.hm
 */
@RestController
@RequestMapping("/workflow")
public class WorkflowController extends BaseController {

    @Autowired
    private IWorkflowService iWorkflowService;

    /**
     * 直接通过流程图部署
     * @param modelId
     * @return
     */
    @RequestMapping(value = "/deployModel/{modelId}", method = RequestMethod.GET, produces = "application/json")
    public WebResponse deployModel(@PathVariable String modelId) {
        try {
            return super.ok(iWorkflowService.deployModel(modelId),"成功");
        }catch (Exception e){
            return super.error(ResponseCode.ERROR,e.getMessage());
        }
    }
}
