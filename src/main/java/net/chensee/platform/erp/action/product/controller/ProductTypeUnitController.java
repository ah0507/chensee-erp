package net.chensee.platform.erp.action.product.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.chensee.base.annotation.CheckFolderAnnontation;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.product.po.ProductTypeUnitPo;
import net.chensee.platform.erp.action.product.service.ProductTypeUnitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("")
@Slf4j
public class ProductTypeUnitController {
    @Resource
    private ProductTypeUnitService productTypeUnitService;
    @Value("${pageNumber}")
    private Integer PAGE_NUMBER;
    @Value("${pageSize}")
    private Integer PAGE_SIZE;

    @ApiOperation(value = "分页获取所有产品类型单位")
    @RequestMapping(value = "/pType/Unit/pagination", method = RequestMethod.GET)
    public ObjectResponse getAllProductTypeUnitsPagination(@RequestParam(required = false) Integer pageSize,
                                                           @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return productTypeUnitService.getAllProductTypeUnitsPagination(pageSize,pageNumber);
    }

    @ApiOperation(value = "根据ID获取产品类型单位")
    @RequestMapping(value = "/pType/Unit/{id}", method = RequestMethod.GET)
    public ObjectResponse getProductTypeUnitById(@PathVariable Long id) {
        return productTypeUnitService.getProductTypeUnitById(id);
    }

    @ApiOperation(value = "通过名称和类型查询产品单位")
    @RequestMapping(value = "/pType/Unit/search/condition", method = RequestMethod.GET)
    public ObjectResponse getByNameAndType(@RequestParam(required = false) String name,
                                           @RequestParam(required = false) String typeName,
                                           @RequestParam(required = false) Integer pageSize,
                                           @RequestParam(required = false) Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        return productTypeUnitService.getByNameAndType(name, typeName, pageSize, pageNumber);
    }

    @ApiOperation(value = "获取所有产品类型单位")
    @RequestMapping(value = "/pType/Unit", method = RequestMethod.GET)
    public ObjectResponse getAllProductTypeUnits() {
        return productTypeUnitService.getAllProductTypeUnits();
    }

    @ApiOperation(value = "添加产品类型单位")
    @RequestMapping(value = "/pType/Unit", method = RequestMethod.POST)
    @CheckFolderAnnontation(0)
    public ObjectResponse addProductTypeUnit(@RequestBody @Validated ProductTypeUnitPo productTypeUnitPo) {
        return productTypeUnitService.addProductTypeUnit(productTypeUnitPo);
    }

    @ApiOperation(value = "修改产品类型单位")
    @RequestMapping(value = "/pType/Unit", method = RequestMethod.PUT)
    @CheckFolderAnnontation(0)
    public BaseResponse updateProductTypeUnit(@RequestBody @Validated ProductTypeUnitPo productTypeUnitPo) {
        return productTypeUnitService.updateProductTypeUnit(productTypeUnitPo);
    }

    @ApiOperation(value = "删除产品类型单位")
    @RequestMapping(value = "/pType/Unit/{id}", method = RequestMethod.DELETE)
    public BaseResponse deleteProductTypeUnit(@PathVariable Long id) {
        return productTypeUnitService.deleteProductTypeUnit(id);
    }
}
