package net.chensee.platform.erp.action.product.service;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.product.po.ProductTypeUnitPo;


/**
 * @author : shibo
 * @date : 2019/5/21 14:49
 */
public interface ProductTypeUnitService {

    /**
     * 分页获取所有产品类型单位
     * @return
     */
    ObjectResponse getAllProductTypeUnitsPagination(Integer pageSize, Integer pageNumber);

    /**
     * 获取所有产品类型单位
     * @return
     */
    ObjectResponse getAllProductTypeUnits();

    /**
     * 通过名称和类型查询产品类型单位
     * @param name
     * @param typeName
     * @param pageSize
     * @param pageNumber
     */
    ObjectResponse getByNameAndType(String name, String typeName, Integer pageSize, Integer pageNumber);

    /**
     * 根据ID查询产品类型单位
     * @param id
     */
    ObjectResponse getProductTypeUnitById(Long id);

    /**
     * 增加产品类型单位
     * @param productTypeUnitPo
     */
    ObjectResponse addProductTypeUnit(ProductTypeUnitPo productTypeUnitPo);

    /**
     * 修改产品类型单位
     * @param productTypeUnitPo
     */
    BaseResponse updateProductTypeUnit(ProductTypeUnitPo productTypeUnitPo);

    /**
     * 删除产品类型单位
     * @param id
     */
    BaseResponse deleteProductTypeUnit(Long id);


}
