package net.chensee.platform.erp.action.product.service;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.product.po.ProductPo;
import net.chensee.platform.erp.action.product.vo.InputBillVo;
import net.chensee.platform.erp.action.product.vo.OutputBillVo;

import java.util.Date;

public interface ProductService {


    /**
     * 获取所有产品
     * @return
     */
    ObjectResponse getAllProducts(Integer pageSize, Integer pageNumber);

    /**
     * 根据ID查询获取产品
     * @param id
     */
    ObjectResponse getProductById(Long id);

    /**
     * 通常名称和类型查询商品
     * @param name
     * @param typeName
     * @return
     */
    ObjectResponse getByNameAndType(String name, String typeName, Integer pageSize, Integer pageNumber);

    /**
     * 增加产品
     * @param productPo
     */
    ObjectResponse addProduct(ProductPo productPo);

    /**
     * 修改产品
     * @param productPo
     */
    BaseResponse updateProduct(ProductPo productPo);

    /**
     * 删除产品
     * @param id
     */
    BaseResponse deleteProduct(Long id);

    /**
     * 产品入库
     * @param inputBillVo
     * @return
     */
    ObjectResponse addProductInput(InputBillVo inputBillVo) throws Exception;

    /**
     * 获取所有产品入库信息
     * @return
     */
    ObjectResponse getAllProductInputs(Integer pageSize, Integer pageNumber);

    /**
     * 根据时间产品入库信息
     * @param contractNumber
     * @param purchaserDeptName
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getInputByCondition(String contractNumber, String purchaserDeptName,
                                       Date startTime, Date endTime,
                                       Integer pageSize, Integer pageNumber);

    /**
     * 删除产品入库信息
     * @param id
     */
    BaseResponse deleteProductInput(Long id) throws Exception;

    /**
     * 产品出库
     * @param outputBillVo
     * @return
     */
    ObjectResponse addProductOutput(OutputBillVo outputBillVo);

    /**
     * 获取所有产品出库信息
     * @return
     */
    ObjectResponse getAllProductOutput(Integer pageSize, Integer pageNumber);

    /**
     * 根据条件查询产品出库信息
     * @param startTime
     * @param endTime
     * @return
     */
    ObjectResponse getOutputByCondition(String contractNumber,
                                        String employeeDeptName,
                                        Date startTime,
                                        Date endTime,
                                        Integer pageSize,
                                        Integer pageNumber);

    /**
     * 删除产品出库信息
     * @param id
     */
    BaseResponse deleteProductOutput(Long id) throws Exception;

    /**
     * 获取所有产品库存数量
     * @param pageSize
     * @param pageNumber
     */
    ObjectResponse getAllProductStockAmount(Integer pageSize, Integer pageNumber);

    /**
     * 条件查询获取所有产品库存数量
     * @param name
     * @param typeName
     * @param pageSize
     * @param pageNumber
     */
    ObjectResponse getAllProductStockAmountByCondition(String name, String typeName, Integer pageSize, Integer pageNumber);

    /**
     * 根据ID查询获取产品入库信息
     * @param id
     */
    ObjectResponse getProductInputById(Long id);

    /**
     * 根据ID查询获取产品出库信息
     * @param id
     */
    ObjectResponse getProductOutputById(Long id);
}
