package com.szb.platform.workflow.controller;

import com.szb.platform.commons.core.enums.ResponseCode;
import com.szb.platform.commons.core.web.BaseController;
import com.szb.platform.workflow.dal.entity.Vars;
import com.szb.platform.workflow.service.IVariablePoolService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * 变量池
 * 作用于业务扭转
 *
 * @author Wang.hm
 */
@RestController
@RequestMapping(value = "/variables")
@Api(tags = "变量池", value = "作用于业务扭转相关接口")
public class VariablePoolController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VariablePoolController.class);

    @Autowired
    private IVariablePoolService iVariablePoolService;

    @RequestMapping(value = "/insertBatch")
    public void insertBatch(ArrayList<Vars> varsEntities){
        try {
            iVariablePoolService.insertBatch(varsEntities);
            super.ok(null, ResponseCode.SUCCESS.message());
        } catch (Exception e){
            super.error(ResponseCode.ERROR, e.getMessage());
        }
    }
}
