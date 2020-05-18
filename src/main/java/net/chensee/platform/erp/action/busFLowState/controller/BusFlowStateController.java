package net.chensee.platform.erp.action.busFLowState.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.busFLowState.event.EventHandlerManager;
import net.chensee.platform.erp.action.busFLowState.service.BusFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wh
 * @version 1.0
 * @date 2020/3/23 16:11
 */
@RestController
@RequestMapping("/busFlow")
@Slf4j
public class BusFlowStateController {

    @Autowired
    private BusFlowService busFlowService;
    @Autowired
    private EventHandlerManager eventHandlerManager;

    @ApiOperation(value = "添加/更新业务的流程状态")
    @RequestMapping(value = "/update/state", method = RequestMethod.PUT)
    public BaseResponse saveOrUpdateBusFlowState(@RequestParam(value = "runFlowId") String runFlowId,
                                                 @RequestParam(value = "runTaskId") String runTaskId,
                                                 @RequestParam(value = "taskKey") String taskKey,
                                                 @RequestParam(value = "taskType") String taskType,
                                                 @RequestParam(value = "businessKey") String businessKey,
                                                 @RequestParam(value = "state") String state,
                                                 HttpServletRequest request) {
        eventHandlerManager.handler(request);
        return BaseResponse.ok();
    }

    @ApiOperation(value = "查询具体业务对应的流程状态")
    @RequestMapping(value = "/business/{busCode}/flow/state", method = RequestMethod.GET)
    public ObjectResponse getBusFlowState(@PathVariable("busCode") String busCode,
                                          @RequestParam("busIds") String busIds) {
        return busFlowService.getBusFlowState(busCode, busIds);
    }

}
