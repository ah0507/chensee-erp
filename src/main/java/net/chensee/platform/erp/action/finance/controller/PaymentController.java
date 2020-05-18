package net.chensee.platform.erp.action.finance.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import net.chensee.base.annotation.CheckFolderAnnontation;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.finance.po.PaymentPo;
import net.chensee.platform.erp.action.finance.service.PaymentService;
import net.chensee.platform.erp.action.finance.vo.PaymentVo;
import net.chensee.platform.erp.utils.DataHandlerUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : shibo
 * @date : 2019/5/27 18:39
 */
@RestController
@RequestMapping("")
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;
    @Value("${pageNumber}")
    private Integer PAGE_NUMBER;
    @Value("${pageSize}")
    private Integer PAGE_SIZE;

    @ApiOperation(value = "获取所有付款记录")
    @ResponseBody
    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public ObjectResponse getAllPayment(@RequestParam(required = false) Integer pageSize,
                                        @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return paymentService.getAllPayment(pageSize, pageNumber);
    }

    @ApiOperation(value = "根据供应商名称和付款编号查询付款记录")
    @ResponseBody
    @RequestMapping(value = "/payment/search/condition", method = RequestMethod.GET)
    public ObjectResponse getBySupplierNameAndNumber(@RequestParam(required = false) String supplierName,
                                                     @RequestParam(required = false) String number,
                                                     @RequestParam(required = false) Integer pageSize,
                                                     @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return paymentService.getBySupplierNameAndNumber(supplierName, number, pageSize, pageNumber);
    }

    @ApiOperation(value = "根据ID获取付款记录")
    @RequestMapping(value = "/payment/{id}", method = RequestMethod.GET)
    public BaseResponse getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    @ApiOperation(value = "添加付款记录")
    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    @CheckFolderAnnontation(0)
    public ObjectResponse addPayment(@RequestBody @Validated PaymentVo paymentVo) {
        PaymentPo paymentPo = new PaymentPo();
        BeanUtils.copyProperties(paymentVo, paymentPo);
        paymentPo.setAmount(DataHandlerUtil.enlargeFactor(paymentVo.getAmount()));
        return paymentService.addPayment(paymentPo);
    }

    @ApiOperation(value = "修改付款记录")
    @RequestMapping(value = "/payment", method = RequestMethod.PUT)
    @CheckFolderAnnontation(0)
    public BaseResponse updatePayment(@RequestBody @Validated PaymentVo paymentVo) {
        PaymentPo paymentPo = new PaymentPo();
        BeanUtils.copyProperties(paymentVo, paymentPo);
        paymentPo.setAmount(DataHandlerUtil.enlargeFactor(paymentVo.getAmount()));
        return paymentService.updatePayment(paymentPo);
    }

    @ApiOperation(value = "删除付款记录")
    @RequestMapping(value = "/payment/{id}", method = RequestMethod.DELETE)
    public BaseResponse deletePayment(@PathVariable Long id) {
        return paymentService.deletePayment(id);
    }

    @ApiOperation(value = "根据合同查询付款记录")
    @ResponseBody
    @RequestMapping(value = "/payment/contract/{contractId}", method = RequestMethod.GET)
    public ObjectResponse getByContractId(@PathVariable Long contractId) {
        return paymentService.getByContractId(contractId);
    }
}
