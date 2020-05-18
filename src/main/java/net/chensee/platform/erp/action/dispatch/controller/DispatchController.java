package net.chensee.platform.erp.action.dispatch.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import net.chensee.base.annotation.CheckFolderAnnontation;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.dispatch.po.DispatchSheetPo;
import net.chensee.platform.erp.action.dispatch.service.DispatchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : shibo
 * @date : 2019/5/17 15:53
 */
@RestController
@RequestMapping("")
@Slf4j
public class DispatchController {

    @Resource
    private DispatchService dispatchService;
    @Value("${pageNumber}")
    private Integer PAGE_NUMBER;
    @Value("${pageSize}")
    private Integer PAGE_SIZE;

    @ApiOperation(value = "获取派工单列表")
    @ResponseBody
    @RequestMapping(value = "/dispatches", method = RequestMethod.GET)
    public ObjectResponse getAllDispatchSheet(@RequestParam(required = false) Integer pageSize,
                                              @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return dispatchService.getAllDispatchSheet(pageSize, pageNumber);
    }

    @ApiOperation(value = "根据客户名称和编号获取派工单")
    @ResponseBody
    @RequestMapping(value = "/dispatch/search/condition", method = RequestMethod.GET)
    public ObjectResponse getDispatchSheetByCustomers(@RequestParam(required = false) String customerName,
                                                      @RequestParam(required = false) String customerNo,
                                                      @RequestParam(required = false) Integer pageSize,
                                                      @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return dispatchService.getDispatchSheetByCustomers(customerName, customerNo, pageSize, pageNumber);
    }

    @ApiOperation(value = "添加派工单")
    @RequestMapping(value = "/dispatch", method = RequestMethod.POST)
    @CheckFolderAnnontation(0)
    public ObjectResponse addDispatchSheet(@RequestBody @Validated DispatchSheetPo dispatchSheetPo) {
        return dispatchService.addDispatchSheet(dispatchSheetPo);
    }

    @ApiOperation(value = "修改派工单")
    @RequestMapping(value = "/dispatch", method = RequestMethod.PUT)
    @CheckFolderAnnontation(0)
    public BaseResponse updateDispatchSheet(@RequestBody @Validated DispatchSheetPo dispatchSheetPo) {
        return dispatchService.updateDispatchSheet(dispatchSheetPo);
    }

    @ApiOperation(value = "根据ID获取派工单")
    @ResponseBody
    @RequestMapping(value = "/dispatch/{id}", method = RequestMethod.GET)
    public ObjectResponse getById(@PathVariable Long id) {
        return dispatchService.getById(id);
    }

    @ApiOperation(value = "删除派工单")
    @RequestMapping(value = "/dispatch/{id}", method = RequestMethod.DELETE)
    public BaseResponse deleteDispatchSheet(@PathVariable Long id) {
        return dispatchService.deleteDispatchSheet(id);
    }

    @ApiOperation(value = "提交流程申请")
    @RequestMapping(value = "/dispatch/flow", method = RequestMethod.POST)
    public BaseResponse submitFlow(@RequestBody DispatchSheetPo dispatchSheetPo) throws Exception{
        return dispatchService.submitFlow(dispatchSheetPo);
    }
}
