package net.chensee.platform.erp.action.finance.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import net.chensee.base.annotation.CheckFolderAnnontation;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.finance.service.BillService;
import net.chensee.platform.erp.action.finance.vo.BillVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : shibo
 * @date : 2019/5/27 16:12
 */
@RestController
@RequestMapping("")
@Slf4j
public class BillController {

    @Resource
    private BillService billService;
    @Value("${pageNumber}")
    private Integer PAGE_NUMBER;
    @Value("${pageSize}")
    private Integer PAGE_SIZE;

    @ApiOperation(value = "获取收款票据列表")
    @ResponseBody
    @RequestMapping(value = "/bill/receive", method = RequestMethod.GET)
    public ObjectResponse getAllReceiveBill(@RequestParam(required = false) Integer pageSize,
                                            @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return billService.getAllBill(pageSize, pageNumber, 1);
    }

    @ApiOperation(value = "获取付款票据列表")
    @ResponseBody
    @RequestMapping(value = "/bill/pay", method = RequestMethod.GET)
    public ObjectResponse getAllPayBill(@RequestParam(required = false) Integer pageSize,
                                        @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return billService.getAllBill(pageSize, pageNumber, -1);
    }

    @ApiOperation(value = "通过合同获取收款票据列表")
    @ResponseBody
    @RequestMapping(value = "/bill/receive/contract/{contractId}", method = RequestMethod.GET)
    public ObjectResponse getReceiveByContractId(@PathVariable Long contractId,
                                                 @RequestParam(required = false) Integer pageSize,
                                                 @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return billService.getByContractId(1, contractId, pageSize, pageNumber);
    }

    @ApiOperation(value = "通过ID获取收款票据")
    @ResponseBody
    @RequestMapping(value = "/bill/receive/{id}", method = RequestMethod.GET)
    public ObjectResponse getReceiveById(@PathVariable Long id) {
        return billService.getReceiveById(id);
    }

    @ApiOperation(value = "通过合同获取付款票据列表")
    @ResponseBody
    @RequestMapping(value = "/bill/pay/contract/{contractId}", method = RequestMethod.GET)
    public ObjectResponse getPayByContractId(@PathVariable Long contractId,
                                             @RequestParam(required = false) Integer pageSize,
                                             @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return billService.getByContractId(-1, contractId, pageSize, pageNumber);
    }

    @ApiOperation(value = "通过ID获取付款票据")
    @ResponseBody
    @RequestMapping(value = "/bill/pay/{id}", method = RequestMethod.GET)
    public ObjectResponse getPayById(@PathVariable Long id) {
        return billService.getPayById(id);
    }

    @ApiOperation(value = "添加收款票据")
    @RequestMapping(value = "/bill/receive", method = RequestMethod.POST)
    @CheckFolderAnnontation(0)
    public BaseResponse addReceiveBill(@RequestBody @Validated BillVo billVo) throws Exception{
        billVo.setDirect(1);
        return billService.addBill(billVo);
    }

    @ApiOperation(value = "添加付款票据")
    @RequestMapping(value = "/bill/pay", method = RequestMethod.POST)
    @CheckFolderAnnontation(0)
    public BaseResponse addPayBill(@RequestBody @Validated BillVo billVo) throws Exception{
        billVo.setDirect(-1);
        return billService.addBill(billVo);
    }

    @ApiOperation(value = "修改票据")
    @RequestMapping(value = "/bill", method = RequestMethod.PUT)
    @CheckFolderAnnontation(0)
    public BaseResponse updateDispatchSheet(@RequestBody @Validated BillVo billVo) throws Exception{
        return billService.updateBill(billVo);
    }

    @ApiOperation(value = "删除票据")
    @RequestMapping(value = "/bill/{id}", method = RequestMethod.DELETE)
    public BaseResponse deleteBill(@PathVariable Long id) {
        return billService.deleteBill(id);
    }

    @ApiOperation(value = "根据对方名称和票据编号获取收款票据")
    @ResponseBody
    @RequestMapping(value = "/bill/receive/search/condition", method = RequestMethod.GET)
    public ObjectResponse getReceiveByOtherPartyNameAndNumber(@RequestParam(required = false) String otherPartyName,
                                                              @RequestParam(required = false) String number,
                                                              @RequestParam(required = false) Integer pageSize,
                                                              @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return billService.getByOtherPartyNameAndNumber(otherPartyName, number, pageSize, pageNumber, 1);
    }

    @ApiOperation(value = "根据对方名称和票据编号获取付款票据")
    @ResponseBody
    @RequestMapping(value = "/bill/pay/search/condition", method = RequestMethod.GET)
    public ObjectResponse getPayByOtherPartyNameAndNumber(@RequestParam(required = false) String otherPartyName,
                                                          @RequestParam(required = false) String number,
                                                          @RequestParam(required = false) Integer pageSize,
                                                          @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return billService.getByOtherPartyNameAndNumber(otherPartyName, number, pageSize, pageNumber, -1);
    }
}
