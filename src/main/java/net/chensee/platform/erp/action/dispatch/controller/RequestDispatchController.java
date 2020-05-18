package net.chensee.platform.erp.action.dispatch.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import net.chensee.base.action.user.business.UserBus;
import net.chensee.base.annotation.CheckFolderAnnontation;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.dispatch.po.RequestDispatchPo;
import net.chensee.platform.erp.action.dispatch.service.RequestDispatchService;
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
public class RequestDispatchController {

    @Resource
    private RequestDispatchService requestDispatchService;
    @Value("${pageNumber}")
    private Integer PAGE_NUMBER;
    @Value("${pageSize}")
    private Integer PAGE_SIZE;

    @ApiOperation(value = "获取派工单列表")
    @ResponseBody
    @RequestMapping(value = "/requestDispatches", method = RequestMethod.GET)
    public ObjectResponse getAllRequestDispatch(@RequestParam(required = false) Integer pageSize,
                                                @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return requestDispatchService.getAllRequestDispatch(pageSize, pageNumber);
    }

    @ApiOperation(value = "根据供应商名称和编号获取派工单")
    @ResponseBody
    @RequestMapping(value = "/requestDispatch/search/condition", method = RequestMethod.GET)
    public ObjectResponse getRequestDispatchBySupplier(@RequestParam(required = false) String supplierName,
                                                       @RequestParam(required = false) String supplierNo,
                                                       @RequestParam(required = false) Integer pageSize,
                                                       @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return requestDispatchService.getRequestDispatchBySupplier(supplierName, supplierNo, pageSize, pageNumber);
    }

    @ApiOperation(value = "添加派工单")
    @RequestMapping(value = "/requestDispatch", method = RequestMethod.POST)
    @CheckFolderAnnontation(0)
    public ObjectResponse addRequestDispatch(@RequestBody @Validated RequestDispatchPo requestDispatchPo) {
        return requestDispatchService.addRequestDispatch(requestDispatchPo);
    }

    @ApiOperation(value = "修改派工单")
    @RequestMapping(value = "/requestDispatch", method = RequestMethod.PUT)
    @CheckFolderAnnontation(0)
    public BaseResponse updateRequestDispatch(@RequestBody @Validated RequestDispatchPo requestDispatchPo) {
        return requestDispatchService.updateRequestDispatch(requestDispatchPo);
    }

    @ApiOperation(value = "根据ID获取派工单")
    @ResponseBody
    @RequestMapping(value = "/requestDispatch/{id}", method = RequestMethod.GET)
    public ObjectResponse getById(@PathVariable Long id) {
        return requestDispatchService.getById(id);
    }

    @ApiOperation(value = "删除派工单")
    @RequestMapping(value = "/requestDispatch/{id}", method = RequestMethod.DELETE)
    public BaseResponse deleteRequestDispatch(@PathVariable Long id) {
        return requestDispatchService.deleteRequestDispatch(id);
    }
}
