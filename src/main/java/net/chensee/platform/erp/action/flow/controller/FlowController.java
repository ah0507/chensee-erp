package net.chensee.platform.erp.action.flow.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.chensee.base.action.folder.service.FolderService;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.business.po.BusinessRolePo;
import net.chensee.platform.erp.action.business.service.BusRoleService;
import net.chensee.platform.erp.action.flow.po.FlowBatchBusPo;
import net.chensee.platform.erp.action.flow.po.FlowPo;
import net.chensee.platform.erp.action.flow.service.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flow")
@Slf4j
public class FlowController {

    @Autowired
    private FlowService flowService;

    //业务关联角色
    @ApiOperation(value = "获取业务流程关联信息")
    @RequestMapping(value = "/bus", method = RequestMethod.GET)
    public ObjectResponse getFlowBusRelations() {
        return flowService.getFlowBusRelations();
    }

    @ApiOperation(value = "根据ID获取业务流程关联信息")
    @RequestMapping(value = "/bus/{id}", method = RequestMethod.GET)
    public ObjectResponse getFlowBusRelationById(@PathVariable Long id) {
        return flowService.getFlowBusRelationById(id);
    }

    @ApiOperation(value = "添加业务流程关联信息")
    @RequestMapping(value = "/bus", method = RequestMethod.POST)
    public BaseResponse addFlowBusRelation(@RequestBody FlowPo flowPo) {
        return flowService.addFlowBusRelation(flowPo);
    }

    @ApiOperation(value = "删除业务流程关联信息")
    @RequestMapping(value = "/bus/{id}", method = RequestMethod.DELETE)
    public BaseResponse deleteFlowBusRelation(@PathVariable(value = "id") Long id) {
        return flowService.deleteFlowBusRelation(id);
    }

    @ApiOperation(value = "根据流程ID获取关联的业务信息")
    @RequestMapping(value = "/bus/relation/info", method = RequestMethod.GET)
    public BaseResponse getBusinessByFlowId(@RequestParam(value = "flowId") String flowId) {
        return flowService.getBusinessByFlowId(flowId);
    }

    @ApiOperation(value = "批量添加流程业务关联信息")
    @RequestMapping(value = "/bus/relation/info", method = RequestMethod.POST)
    public BaseResponse addBatchFlowBusRelations(@RequestBody FlowBatchBusPo flowBatchBusPo) {
        return flowService.addBatchFlowBusRelations(flowBatchBusPo);
    }

    @ApiOperation(value = "发起流程")
    @RequestMapping(value = "/start/{busCode}", method = RequestMethod.GET)
    public BaseResponse launchFlow(@PathVariable(value = "busCode") String busCode,
                                   @RequestParam(value = "busId") Long busId) {
        return flowService.launchFlow(busCode,busId);
    }

    @ApiOperation(value = "根据taskId获取当前表单及之前节点的表单")
    @RequestMapping(value = "/forms/{businessKey}", method = RequestMethod.GET)
    public BaseResponse getFormsByTaskId(@PathVariable(value = "businessKey") String businessKey) {
        return flowService.getFormsByTaskId(businessKey);
    }

    @ApiOperation(value = "根据已完成任务的taskId获取关联的表单")
    @RequestMapping(value = "/done/task/form/{taskId}", method = RequestMethod.GET)
    public BaseResponse getFormByDoneTaskId(@PathVariable(value = "taskId") String taskId) {
        return flowService.getFormByDoneTaskId(taskId);
    }

    @ApiOperation(value = "完成当前节点并提交当前表单")
    @RequestMapping(value = "/form/{formId}/business/{businessKey}/id/{userId}", method = RequestMethod.POST)
    public BaseResponse submitFormData(@PathVariable(value = "formId") Long formId,
                                       @PathVariable(value = "businessKey") String businessKey,
                                       @PathVariable(value = "userId") Long userId,
                                       @RequestBody String data) {
        return flowService.submitFormData(formId, businessKey, userId, data);
    }

    @ApiOperation(value = "查看已办任务")
    @RequestMapping(value = "/task/done/id/{userId}", method = RequestMethod.GET)
    public ObjectResponse getAlreadyDoneTask(@PathVariable(value = "userId") Long userId) {
        return flowService.getAlreadyDoneTask(userId);
    }

    @ApiOperation(value = "查看待办任务")
    @RequestMapping(value = "/task/todo/id/{userId}", method = RequestMethod.GET)
    public ObjectResponse getToDoTask(@PathVariable(value = "userId") Long userId) {
        return flowService.getToDoTask(userId);
    }

    @ApiOperation(value = "签收待办任务")
    @RequestMapping(value = "/task/todo/id/{userId}/task/{taskId}", method = RequestMethod.PUT)
    public BaseResponse signTodoTask(@PathVariable(value = "userId") Long userId,
                                     @PathVariable(value = "taskId") String taskId) {
        return flowService.signTodoTask(userId, taskId);
    }

    @ApiOperation(value = "查询具体业务关联的所有表单")
    @RequestMapping(value = "/business/{busCode}/flow/form/{busId}", method = RequestMethod.GET)
    public ObjectResponse getBusFormData(@PathVariable("busCode") String busCode,
                                         @RequestParam("busId") String busId) {
        return flowService.getBusFormData(busCode, busId);
    }

    @ApiOperation(value = "查询所有的流程")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ObjectResponse getAllFlows() {
        return flowService.getAllFlows();
    }
}
