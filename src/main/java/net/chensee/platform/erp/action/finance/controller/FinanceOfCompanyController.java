package net.chensee.platform.erp.action.finance.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.finance.po.FinanceOfCompanyPo;
import net.chensee.platform.erp.action.finance.service.FinanceOfCompanyService;
import net.chensee.platform.erp.action.product.po.ProductTypeUnitPo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("")
@Slf4j
public class FinanceOfCompanyController {


    @Resource
    private FinanceOfCompanyService financeOfCompanyService;


    @ApiOperation(value = "获取所有本公司财务信息记录")
    @ResponseBody
    @RequestMapping(value = "/finance/company", method = RequestMethod.GET)
    public ObjectResponse getAllRecords(@RequestParam(required = false) Integer pageSize,
                                        @RequestParam(required = false) Integer pageNumber) {
        return financeOfCompanyService.getAllRecords(pageSize, pageNumber);
    }

    @ApiOperation(value = "根据条件获取本公司财务信息记录")
    @ResponseBody
    @RequestMapping(value = "/finance/company/search/condition", method = RequestMethod.GET)
    public ObjectResponse getByCondition(@RequestParam(required = false) String userName,
                                         @RequestParam(required = false) Integer pageSize,
                                         @RequestParam(required = false) Integer pageNumber) {
        return financeOfCompanyService.getByCondition(userName, pageSize, pageNumber);
    }

    @ApiOperation(value = "新增本公司财务信息记录")
    @ResponseBody
    @RequestMapping(value = "/finance/company", method = RequestMethod.POST)
    public BaseResponse addRecord(@RequestBody @Validated FinanceOfCompanyPo financeOfCompanyPo) {
        return financeOfCompanyService.addRecord(financeOfCompanyPo);
    }

    @ApiOperation(value = "更新本公司财务信息记录")
    @ResponseBody
    @RequestMapping(value = "/finance/company", method = RequestMethod.PUT)
    public BaseResponse updateRecord(@RequestBody @Validated FinanceOfCompanyPo financeOfCompanyPo) {
        return financeOfCompanyService.updateRecord(financeOfCompanyPo);
    }

    @ApiOperation(value = "根据ID删除本公司财务信息记录")
    @ResponseBody
    @RequestMapping(value = "/finance/company/{id}", method = RequestMethod.DELETE)
    public BaseResponse updateRecord(@RequestParam Long id) {
        return financeOfCompanyService.deleteRecord(id);
    }
}
