package net.chensee.platform.erp.action.product.service;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.product.po.ProductTypeModelPo;
import net.chensee.platform.erp.action.product.po.ProductTypePo;


/**
 * @author : shibo
 * @date : 2019/5/21 14:49
 */
public interface ProductTypeService {

    /**
     * 获取所有产品类型
     * @return
     */
    ObjectResponse getAllProductTypes(Integer pageNumber, Integer pageSize);

    /**
     * 增加产品类型
     * @param productTypePo
     */
    ObjectResponse addProductType(ProductTypePo productTypePo);

    /**
     * 修改产品类型
     * @param productTypePo
     */
    BaseResponse updateProductType(ProductTypePo productTypePo);

    /**
     * 删除产品类型
     * @param id
     */
    BaseResponse deleteProductType(Long id);

    /**
     * 获取所有产品类型
     * @return
     */
    ObjectResponse getProductTypesByCondition(String name, String parentName, String unitName,
                                              Integer pageNumber, Integer pageSize);

    /**
     * 获取所有产品类型
     * @param id
     * @return
     */
    ObjectResponse getProductTypeById(Long id);

    /**
     * 根据类型ID添加产品型号
     * @param id
     * @param productTypeModelPo
     * @return
     */
    BaseResponse addProductTypeModel(Long id, ProductTypeModelPo productTypeModelPo);

    /**
     * 根据类型ID获取产品型号
     * @param id
     * @return
     */
    ObjectResponse getProductTypeModel(Long id,Integer pageNumber,Integer pageSize);

    /**
     * 根据类型ID删除产品型号
     * @param id
     * @return
     */
    BaseResponse deleteProductTypeModel(Long id);
}
