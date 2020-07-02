package com.szb.platform.workflow.controller;

/**
 * 审批
 *
 * @author wang.hm
 */

import com.szb.platform.commons.core.enums.ResponseCode;
import com.szb.platform.commons.core.result.WebResponse;
import com.szb.platform.commons.core.web.BaseController;
import com.szb.platform.workflow.service.IApproveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * 查询
 * @author Wang.hm
 */
@RestController
@RequestMapping(value = "/query")
@Api(tags = "审批", value = "量房订单流程相关接口")
public class ApproveController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApproveController.class);

    @Autowired
    private IApproveService iApproveService;

    /**
     * 添加审批人
     */
    @RequestMapping(value = "/addApproves")
    @ApiOperation(value = "添加审批人", httpMethod = "POST")
    public WebResponse addApproves(
            @RequestParam("processInstanceId") String processInstanceId,
            @RequestParam("processInstanceBusinessKey") String processInstanceBusinessKey,
            @RequestParam("names") ArrayList<String> names
    ) {
        try {
            iApproveService.addApproves(processInstanceId,processInstanceBusinessKey,names);
            return super.ok(
                    null
                    , ResponseCode.SUCCESS.message()
            );
        } catch (Exception e) {
            LOGGER.error(ResponseCode.ERROR.message(),e);
            return super.error(ResponseCode.ERROR, e.getMessage());
        }
    }

}
