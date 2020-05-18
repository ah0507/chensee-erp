package net.chensee.platform.erp.action.supplier.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import net.chensee.base.annotation.CheckFolderAnnontation;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.customer.po.LinkmanPo;
import net.chensee.platform.erp.action.supplier.service.SupplierLinkmanService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : shibo
 * @date : 2019/5/20 9:25
 */
@RestController
@RequestMapping("")
@Slf4j
public class SupplierLinkmanController {

    @Resource
    private SupplierLinkmanService supplierLinkmanService;
    @Value("${pageNumber}")
    private Integer PAGE_NUMBER;
    @Value("${pageSize}")
    private Integer PAGE_SIZE;

    @ApiOperation(value = "获取所有的供应商联系人")
    @RequestMapping(value = "/supplier/linkman", method = RequestMethod.GET)
    public ObjectResponse getAllSupplierLinkman(@RequestParam(required = false) Integer pageSize,
                                                @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return supplierLinkmanService.getAllSupplierLinkman(pageSize, pageNumber);
    }

    @ApiOperation(value = "根据供应商名称和联系人名称查询联系人")
    @RequestMapping(value = "/supplier/linkman/search/condition", method = RequestMethod.GET)
    public ObjectResponse getBySupplierNameAndName(@RequestParam(required = false) String supplierName,
                                                   @RequestParam(required = false) String name,
                                                   @RequestParam(required = false) Integer pageSize,
                                                   @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return supplierLinkmanService.getBySupplierNameAndName(supplierName, name, pageSize, pageNumber);
    }

    @ApiOperation(value = "根据供应商查询联系人")
    @RequestMapping(value = "/supplier/{supplierId}/linkman", method = RequestMethod.GET)
    public ObjectResponse getByCustomer(@PathVariable Long supplierId, Integer pageSize, Integer pageNumber) {
        return supplierLinkmanService.getBySupplier(supplierId, pageSize, pageNumber);
    }

    @ApiOperation(value = "添加供应商联系人")
    @RequestMapping(value = "/supplier/linkman", method = RequestMethod.POST)
    @CheckFolderAnnontation(0)
    public ObjectResponse addSupplierLinkman(@RequestBody @Validated LinkmanPo linkmanPo) {
        return supplierLinkmanService.addSupplierLinkman(linkmanPo);
    }

    @ApiOperation(value = "修改供应商联系人")
    @RequestMapping(value = "/supplier/linkman", method = RequestMethod.PUT)
    @CheckFolderAnnontation(0)
    public BaseResponse updateSupplierLinkman(@RequestBody @Validated LinkmanPo linkmanPo) {
        return supplierLinkmanService.updateSupplierLinkman(linkmanPo);
    }

    @ApiOperation(value = "删除供应商联系人")
    @RequestMapping(value = "/supplier/linkman/{id}", method = RequestMethod.DELETE)
    public BaseResponse deleteSupplierLinkman(@PathVariable Long id) {
        return supplierLinkmanService.deleteSupplierLinkman(id);
    }

    @ApiOperation(value = "根据ID获取供应商联系人")
    @RequestMapping(value = "/supplier/linkman/{id}", method = RequestMethod.GET)
    public ObjectResponse getSupplierLinkmanById(@PathVariable Long id) {
        return supplierLinkmanService.getSupplierLinkmanById(id);
    }
}
