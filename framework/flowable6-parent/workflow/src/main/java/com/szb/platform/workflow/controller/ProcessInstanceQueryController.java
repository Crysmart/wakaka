package com.szb.platform.workflow.controller;

import com.szb.platform.commons.core.enums.ResponseCode;
import com.szb.platform.commons.core.result.WebResponse;
import com.szb.platform.commons.core.web.BaseController;
import com.szb.platform.workflow.service.IProcessInstanceQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查询
 * @author Wang.hm
 */
@RestController
@RequestMapping(value = "/query")
@Api(tags = "流程查询", value = "流程查询相关接口")
public class ProcessInstanceQueryController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessInstanceQueryController.class);

    @Autowired
    private IProcessInstanceQueryService iProcessInstanceQueryService;

    /**
     * 获取分组任务（角色任务）
     * @param processInstanceId 实例id
     * @param currentPage 当前页
     * @param pageSize 每页多少条
     * @return
     */
    @RequestMapping(value = "/getTaskListByGroup")
    @ApiOperation(value = "获取分组任务",httpMethod = "GET")
    public WebResponse getTaskListByGroup(String processInstanceId,String processInstanceBusinessKey,Integer currentPage,Integer pageSize){
        try {
            return super.ok(
                    iProcessInstanceQueryService.getTaskListByGroup(processInstanceId,processInstanceBusinessKey,currentPage,pageSize)
                    , ResponseCode.SUCCESS.message()
            );
        } catch (Exception e){
            LOGGER.error(ResponseCode.ERROR.message(),e);
            return super.error(ResponseCode.ERROR,e.getMessage());
        }
    }

    /**
     * 当前角色的当前人任务
     * @param candidateGroup 分组
     * @param owner 拥有者
     * @param processInstanceBusinessKey 业务表示
     * @param currentPage 当前页
     * @param pageSize 每页大小
     * @return
     */
    @RequestMapping(value = "/getTaskListByGroupOwner")
    @ApiOperation(value = "当前角色的当前人任务",httpMethod = "GET")
    public WebResponse getTaskListByGroupOwner(
            @RequestParam("candidateGroup") String candidateGroup,
            @RequestParam("owner") String owner,
            @RequestParam("processInstanceBusinessKey") String processInstanceBusinessKey,
            @RequestParam("currentPage") int currentPage,
            @RequestParam("pageSize") int pageSize){
        try {
            return super.ok(
                    iProcessInstanceQueryService.getTaskListByGroupOwner(candidateGroup, owner, processInstanceBusinessKey, currentPage, pageSize)
                    , ResponseCode.SUCCESS.message()
            );
        } catch (Exception e){
            LOGGER.error(ResponseCode.ERROR.message(),e);
            return super.error(ResponseCode.ERROR,e.getMessage());
        }
    }

    /**
     * 当前人所拥有的任务
     * @param owner 拥有者
     * @param processInstanceBusinessKey 业务表示
     * @param currentPage 当前页
     * @param pageSize 每页大小
     * @return
     */
    @RequestMapping(value = "/getTaskListByOwner")
    @ApiOperation(value = "当前角色的当前人任务",httpMethod = "GET")
    public WebResponse getTaskListByOwner(
            @RequestParam("owner") String owner,
            @RequestParam("processInstanceBusinessKey") String processInstanceBusinessKey,
            @RequestParam("currentPage") int currentPage,
            @RequestParam("pageSize") int pageSize){
        try {
            return super.ok(
                    iProcessInstanceQueryService.getTaskListByOwner(owner, processInstanceBusinessKey, currentPage, pageSize)
                    , ResponseCode.SUCCESS.message()
            );
        } catch (Exception e){
            LOGGER.error(ResponseCode.ERROR.message(),e);
            return super.error(ResponseCode.ERROR,e.getMessage());
        }
    }

    /**
     * 获取任务
     * @param processInstanceId 实例id
     * @return
     */
    @RequestMapping(value = "/getTaskById")
    @ApiOperation(value = "获取任务",httpMethod = "GET")
    public WebResponse getTaskById(
            @RequestParam("processInstanceId") String processInstanceId,
            @RequestParam("taskId") String taskId){
        try {
            return super.ok(
                    iProcessInstanceQueryService.getTaskById(processInstanceId,taskId)
                    , ResponseCode.SUCCESS.message()
            );
        } catch (Exception e){
            LOGGER.error(ResponseCode.ERROR.message(),e);
            return super.error(ResponseCode.ERROR,e.getMessage());
        }
    }

    /**
     * 当前人是否拥有此任务
     * @param processInstanceId 实例id
     * @param taskId 任务id
     * @param owner 拥有者
     * @return
     */
    @RequestMapping(value = "/getTaskByOwner")
    @ApiOperation(value = "获取流程进度",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processInstanceId",value = "流程实例id",required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "taskId",value = "任务id",required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "owner",value = "拥有者",required = true,dataType = "String",paramType = "query"),
    })
    public WebResponse getTaskByOwner(
            @RequestParam("processInstanceId") String processInstanceId,
            @RequestParam("taskId") String taskId,
            @RequestParam("owner") String owner){
        try {
            return super.ok(
                    iProcessInstanceQueryService.getTaskByOwner(processInstanceId, taskId, owner)
                    , ResponseCode.SUCCESS.message()
            );
        } catch (Exception e){
            LOGGER.error(ResponseCode.ERROR.message(),e);
            return super.error(ResponseCode.ERROR,e.getMessage());
        }
    }

    /**
     * 获取任务
     * @param processInstanceId 实例id
     * @return
     */
    @RequestMapping(value = "/getHiTaskListByProcessInstanceId")
    @ApiOperation(value = "获取流程进度",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processInstanceId",value = "流程实例id",required = true,dataType = "String",paramType = "query"),
    })
    public WebResponse getHiTaskListByProcessInstanceId(
            @RequestParam("processInstanceId") String processInstanceId){
        try {
            return super.ok(
                    iProcessInstanceQueryService.getHiTaskListByProcessInstanceId(processInstanceId)
                    , ResponseCode.SUCCESS.message()
            );
        } catch (Exception e){
            LOGGER.error(ResponseCode.ERROR.message(),e);
            return super.error(ResponseCode.ERROR,e.getMessage());
        }
    }

    /**
     * 通过参数查询同类型流程
     * @param processInstanceBusinessKey 业务标识
     * @param arg 参数
     * @param currentPage 当前页数
     * @param pageSize 每页展示条数
     * @return
     */
    @RequestMapping(value = "/getBusinessTaskByArg")
    @ApiOperation(value = "通过参数查询同类型流程",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processInstanceBusinessKey",value = "业务标识",required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "arg",value = "筛选参数",required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "currentPage",value = "当前页",required = true,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "pageSize",value = "每页展示条数",required = true,dataType = "Integer",paramType = "query"),
    })
    public WebResponse getHiTaskListByProcessInstanceId(
            @RequestParam("processInstanceBusinessKey") String processInstanceBusinessKey,
            @RequestParam("arg") String arg,
            @RequestParam("currentPage") int currentPage,
            @RequestParam("pageSize") int pageSize){
        try {
            return super.ok(
                    iProcessInstanceQueryService.getBusinessTaskByArg(processInstanceBusinessKey, arg, currentPage, pageSize)
                    , ResponseCode.SUCCESS.message()
            );
        } catch (Exception e){
            LOGGER.error(ResponseCode.ERROR.message(),e);
            return super.error(ResponseCode.ERROR,e.getMessage());
        }
    }
}
