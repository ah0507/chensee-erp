package net.chensee.platform.erp.action.supplier.service;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.supplier.vo.SupplierVo;

/**
 * @author : shibo
 * @date : 2019/6/18 13:43
 */
public interface SupplierService {


    /**
     * 添加供应商信息
     * @param supplierVo
     */
    ObjectResponse addSupplier(SupplierVo supplierVo) throws Exception;

    /**
     * 修改供应商信息
     * @param supplierVo
     */
    BaseResponse updateSupplier(SupplierVo supplierVo) throws Exception;

    /**
     * 删除供应商
     * @param id
     */
    BaseResponse deleteSupplier(Long id) throws Exception;

    /**
     * 获取所有供应商
     * @param pageSize
     * @param pageNumber
     * @return
     */
    ObjectResponse getAllSupplier(Integer pageSize, Integer pageNumber);

    /**
     * 根据公司名称和编号查询供应商
     * @param name
     * @param number
     * @return
     */
    ObjectResponse getByNameAndNo(String name, String number, Integer pageSize, Integer pageNumber);

    /**
     * 根据公司名称和编号查询供应商
     * @param supplierId
     * @return
     */
    ObjectResponse getById(Long supplierId);
}
