package net.chensee.platform.erp.action.supplier.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import net.chensee.base.annotation.CheckFolderAnnontation;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.supplier.service.SupplierService;
import net.chensee.platform.erp.action.supplier.vo.SupplierVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : shibo
 * @date : 2019/5/17 18:02
 */
@RestController
@RequestMapping("")
@Slf4j
public class SupplierController {

    @Resource
    private SupplierService supplierService;
    @Value("${pageNumber}")
    private Integer PAGE_NUMBER;
    @Value("${pageSize}")
    private Integer PAGE_SIZE;

    @ApiOperation(value = "获取所有供应商")
    @RequestMapping(value = "/supplier", method = RequestMethod.GET)
    public ObjectResponse getAllSupplier(@RequestParam(required = false) Integer pageSize,
                                         @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return supplierService.getAllSupplier(pageSize, pageNumber);
    }

    @ApiOperation(value = "根据供应商名称和编号查询供应商")
    @RequestMapping(value = "/supplier/search/condition", method = RequestMethod.GET)
    public ObjectResponse getByNameAndNo(@RequestParam(required = false) String name,
                                         @RequestParam(required = false) String no,
                                         @RequestParam(required = false) Integer pageSize,
                                         @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return supplierService.getByNameAndNo(name, no, pageSize, pageNumber);
    }

    @ApiOperation(value = "根据供应商ID查询供应商")
    @RequestMapping(value = "/supplier/{supplierId}", method = RequestMethod.GET)
    public ObjectResponse getById(@PathVariable Long supplierId) {
        return supplierService.getById(supplierId);
    }

    @ApiOperation(value = "添加供应商")
    @RequestMapping(value = "/supplier", method = RequestMethod.POST)
    @CheckFolderAnnontation(0)
    public ObjectResponse addSupplier(@RequestBody @Validated SupplierVo supplierVo) throws Exception{
        return supplierService.addSupplier(supplierVo);
    }

    @ApiOperation(value = "修改供应商")
    @RequestMapping(value = "/supplier", method = RequestMethod.PUT)
    @CheckFolderAnnontation(0)
    public BaseResponse updateSupplier(@RequestBody @Validated SupplierVo supplierVo) throws Exception{
        return supplierService.updateSupplier(supplierVo);
    }

    @ApiOperation(value = "删除供应商")
    @RequestMapping(value = "/supplier/{id}", method = RequestMethod.DELETE)
    public BaseResponse deleteSupplier(@PathVariable Long id) throws Exception{
        return supplierService.deleteSupplier(id);
    }

}
