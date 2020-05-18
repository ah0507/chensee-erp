package net.chensee.platform.erp.action.product.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import net.chensee.base.annotation.CheckFolderAnnontation;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.product.po.ProductTypeModelPo;
import net.chensee.platform.erp.action.product.po.ProductTypePo;
import net.chensee.platform.erp.action.product.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : shibo
 * @date : 2019/5/21 14:48
 */

@RestController
@RequestMapping("")
@Slf4j
public class ProductTypeController {
    @Resource
    private ProductTypeService productTypeService;
    @Value("${pageNumber}")
    private Integer PAGE_NUMBER;
    @Value("${pageSize}")
    private Integer PAGE_SIZE;

    @ApiOperation(value = "获取所有产品类型")
    @RequestMapping(value = "/productType", method = RequestMethod.GET)
    public ObjectResponse getAllProductTypes(@RequestParam(required = false) Integer pageNumber,
                                             @RequestParam(required = false) Integer pageSize) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return productTypeService.getAllProductTypes(pageNumber,pageSize);
    }

    @ApiOperation(value = "条件获取产品类型")
    @RequestMapping(value = "/productType/search/condition", method = RequestMethod.GET)
    public ObjectResponse getProductTypesByCondition(@RequestParam(required = false) String name,
                                                     @RequestParam(required = false) String parentName,
                                                     @RequestParam(required = false) String unitName,
                                                     @RequestParam(required = false) Integer pageNumber,
                                                     @RequestParam(required = false) Integer pageSize) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return productTypeService.getProductTypesByCondition(name,parentName,unitName,pageNumber,pageSize);
    }

    @ApiOperation(value = "根据ID获取产品类型")
    @RequestMapping(value = "/productType/{id}", method = RequestMethod.GET)
    public ObjectResponse getProductTypeById(@PathVariable(value = "id") Long id) {
        return productTypeService.getProductTypeById(id);
    }

    @ApiOperation(value = "添加产品类型")
    @RequestMapping(value = "/productType", method = RequestMethod.POST)
    @CheckFolderAnnontation(0)
    public BaseResponse addProductType(@RequestBody @Validated ProductTypePo productTypePo) {
        return productTypeService.addProductType(productTypePo);
    }

    @ApiOperation(value = "修改产品类型")
    @RequestMapping(value = "/productType", method = RequestMethod.PUT)
    @CheckFolderAnnontation(0)
    public BaseResponse updateProductType(@RequestBody @Validated ProductTypePo productTypePo) {
        return productTypeService.updateProductType(productTypePo);
    }

    @ApiOperation(value = "删除产品类型")
    @RequestMapping(value = "/productType/{id}", method = RequestMethod.DELETE)
    public BaseResponse deleteProductType(@PathVariable Long id) {
        return productTypeService.deleteProductType(id);
    }

    @ApiOperation(value = "根据类型ID添加产品型号")
    @RequestMapping(value = "/productType/model/{id}", method = RequestMethod.POST)
    public BaseResponse addProductTypeModel(@PathVariable Long id,
                                            @RequestBody @Validated ProductTypeModelPo productTypeModelPo) {
        return productTypeService.addProductTypeModel(id,productTypeModelPo);
    }

    @ApiOperation(value = "根据类型ID获取产品型号")
    @RequestMapping(value = "/productType/model/{id}", method = RequestMethod.GET)
    public BaseResponse getProductTypeModel(@PathVariable Long id,
                                            @RequestParam(defaultValue = "1") Integer pageNumber,
                                            @RequestParam(defaultValue = "16") Integer pageSize) {
        return productTypeService.getProductTypeModel(id,pageNumber,pageSize);
    }

    @ApiOperation(value = "根据ID删除产品型号")
    @RequestMapping(value = "/productType/model/{id}", method = RequestMethod.DELETE)
    public BaseResponse deleteProductTypeModel(@PathVariable Long id) {
        return productTypeService.deleteProductTypeModel(id);
    }
}
