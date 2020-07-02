package com.szb.platform.workflow.controller;

import com.google.common.collect.Maps;
import com.szb.platform.commons.core.enums.ResponseCode;
import com.szb.platform.commons.core.result.WebResponse;
import com.szb.platform.commons.core.web.BaseController;
import com.szb.platform.workflow.service.ITemplateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 流程操作
 * @author Wang.hm
 * @date 2019年12月16日 17点51分
 */
@RestController
@RequestMapping(value = "/template")
@Api(tags = "流程操作", value = "流程操作相关接口")
public class TemplateController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateController.class);

    @Autowired
    private ITemplateService iTemplateService;

    /**
     * 载入BPMN文件
     *
     * @param file BPMN文件包
     * @return
     */
    @RequestMapping(value = "/loadApp")
    @ApiOperation(value = "载入BPMN文件",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file",value = "流程应用",required = true,dataType = "long",paramType = "query"),
            @ApiImplicitParam(name = "name",value = "部署名称",required = true,dataType = "date",paramType = "query")
    })
    public WebResponse loadApp(@RequestParam("file") MultipartFile file) {
        try {
            iTemplateService.loadApp(file);
            return super.ok(null, ResponseCode.SUCCESS.message());
        } catch (Exception e){
            LOGGER.error(ResponseCode.ERROR.message(),e);
            return super.error(ResponseCode.ERROR,e.getMessage());
        }
    }

    /**
     * 载入BPMN文件
     *
     * @param file BPMN文件包
     * @return
     */
    @RequestMapping(value = "/loadBpmn")
    @ApiOperation(value = "载入BPMN文件",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file",value = "流程应用",required = true,dataType = "long",paramType = "query"),
            @ApiImplicitParam(name = "name",value = "部署名称",required = true,dataType = "date",paramType = "query")
    })
    public WebResponse loadBpmn(@RequestParam("file") MultipartFile file,@RequestParam("name") String name) {
        try {
            iTemplateService.loadBpmn(file,name);
            return super.ok(null, ResponseCode.SUCCESS.message());
        } catch (Exception e){
            LOGGER.error(ResponseCode.ERROR.message(),e);
            return super.error(ResponseCode.ERROR,e.getMessage());
        }
    }

    /**
     * 开始BPMN应用
     * @param processInstanceName 流程实例名称
     * @param processDefinitionId 流程定义id
     * @param processDefinitionKey 流程定义key
     * @param businessKey 业务标识
     * @return
     */
    @RequestMapping(value = "/startApp")
    @ApiOperation(value = "开始BPMN应用",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processInstanceName",value = "部署应用实例名称",required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "processDefinitionId",value = "部署应用Id ACT_RE_PROCDEF:ID_",required = true,dataType = "String",paramType = "query")
    })
    public WebResponse startApp(@RequestParam("processInstanceName") String processInstanceName
            , @RequestParam("processDefinitionId") String processDefinitionId
            , String processDefinitionKey
            , String businessKey) {
        try {
            String processInstanceId = iTemplateService.startApp(processInstanceName
                    , processDefinitionId
                    , processDefinitionKey
                    , businessKey
                    , Maps.newHashMap());
            return super.ok(processInstanceId, ResponseCode.SUCCESS.message());
        } catch (Exception e){
            LOGGER.error(ResponseCode.ERROR.message(),e);
            return super.error(ResponseCode.ERROR,e.getMessage());
        }
    }

    /**
     * 完成节点
     *
     * @param taskId 节点任务id
     * @param processInstanceId 流程实例id
     * @return Boolean
     */
    @RequestMapping(value = "/completeTask")
    @ApiOperation(value = "完成节点",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId",value = "节点任务id",required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "processInstanceId",value = "流程实例id",required = true,dataType = "String",paramType = "query"),
    })
    public WebResponse completeTask(@RequestParam("taskId") String taskId
            , @RequestParam("processInstanceId") String processInstanceId) {
        try {
            iTemplateService.completeTask(taskId,processInstanceId);
            return super.ok(null, ResponseCode.SUCCESS.message());
        } catch (Exception e){
            LOGGER.error(ResponseCode.ERROR.message(),e);
            return super.error(ResponseCode.ERROR,e.getMessage());
        }
    }

    /**
     * 获取流程图表
     *
     * @param processInstanceId 流程实例id
     * @return
     */
    @RequestMapping(value = "/getPngDiagram")
    @ApiOperation(value = "获取流程图表",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processInstanceId",value = "流程实例id",required = true,dataType = "String",paramType = "query"),
    })
    public WebResponse getPngDiagram(HttpServletResponse httpServletResponse
            ,@RequestParam("processInstanceId") String processInstanceId
            ,@RequestParam("processDefinitionId") String processDefinitionId) {
        try (InputStream in = iTemplateService.getPngDiagram(processInstanceId,processDefinitionId);
                OutputStream out = httpServletResponse.getOutputStream()) {
            int length;
            byte[] bytes = new byte[1024];
            if (in!=null){
                while ((length = in.read(bytes)) != -1) {
                    out.write(bytes, 0, length);
                }
            }
            return super.ok(null, ResponseCode.SUCCESS.message());
        } catch (Exception e){
            LOGGER.error(ResponseCode.ERROR.message(),e);
            return super.error(ResponseCode.ERROR, e.getMessage());
        }
    }

    /**
     * 节点评论
     *  @param processInstanceId 流程实例id
     * @param taskId            节点任务id
     * @param message           添加的消息
     * @return
     */
    @RequestMapping(value = "/addTaskComment")
    @ApiOperation(value = "节点评论",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processInstanceId",value = "流程实例id",required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "taskId",value = "节点任务id",required = true,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "message",value = "添加的消息",required = true,dataType = "String",paramType = "query"),
    })
    public WebResponse addTaskComment(HttpServletResponse httpServletResponse
            ,@RequestParam("processInstanceId") String processInstanceId
            ,@RequestParam("taskId") String taskId
            ,@RequestParam("message") String message) {
        try {
            iTemplateService.addTaskComment(processInstanceId, taskId, message);
            return super.ok(null, ResponseCode.SUCCESS.message());
        } catch (Exception e){
            LOGGER.error(ResponseCode.ERROR.message(),e);
            return super.error(ResponseCode.ERROR,e.getMessage());
        }
    }

    /**
     * 获取流程评论
     *
     * @param processInstanceId 流程实例id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getProcessComments")
    @ApiOperation(value = "获取流程评论",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processInstanceId",value = "流程实例id",required = true,dataType = "String",paramType = "query"),
    })
    public WebResponse getProcessComments(HttpServletResponse httpServletResponse
                        , @RequestParam("processInstanceId") String processInstanceId) {
        try {
            return super.ok(iTemplateService.getProcessComments(processInstanceId), ResponseCode.SUCCESS.message());
        } catch (Exception e){
            LOGGER.error(ResponseCode.ERROR.message(),e);
            return super.error(ResponseCode.ERROR,e.getMessage());
        }
    }

    /**
     * 设置当前任务拥有者
     * @param taskId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/setOwner")
    public WebResponse setOwner(@RequestParam("taskId") String taskId, @RequestParam("userId")String userId){
        try {
            iTemplateService.setOwner(taskId,userId);
            return super.ok(null, ResponseCode.SUCCESS.message());
        } catch (Exception e){
            LOGGER.error(ResponseCode.ERROR.message(),e);
            return super.error(ResponseCode.ERROR,e.getMessage());
        }
    }

    /**
     * 设置当前任务代理人
     * @param taskId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/setAssignee")
    public WebResponse setAssignee(@RequestParam("taskId") String taskId, @RequestParam("userId")String userId){
        try {
            iTemplateService.setAssignee(taskId,userId);
            return super.ok(null, ResponseCode.SUCCESS.message());
        } catch (Exception e){
            LOGGER.error(ResponseCode.ERROR.message(),e);
            return super.error(ResponseCode.ERROR,e.getMessage());
        }
    }


    /**
     * 移动节点
     * @param currentNodeId
     * @param newNodeId
     * @param processInstanceId
     * @return
     */
    @RequestMapping(value = "/moveActivity")
    public WebResponse moveActivity(@RequestParam("currentNodeId") String currentNodeId,
                                    @RequestParam("newNodeId") String newNodeId,
                                    @RequestParam("processInstanceId") String processInstanceId,
                                    @RequestParam("processDefinitionId") String processDefinitionId,
                                    @RequestParam("processId") String processId){
        try {
            iTemplateService.moveActivity(currentNodeId,newNodeId,processInstanceId,processDefinitionId,processId);
            return super.ok(null, ResponseCode.SUCCESS.message());
        } catch (Exception e){
            LOGGER.error(ResponseCode.ERROR.message(),e);
            return super.error(ResponseCode.ERROR,e.getMessage());
        }
    }
}
