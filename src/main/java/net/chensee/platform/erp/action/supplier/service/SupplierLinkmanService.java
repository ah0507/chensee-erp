package net.chensee.platform.erp.action.supplier.service;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.customer.po.LinkmanPo;

/**
 * @author : shibo
 * @date : 2019/6/20 9:18
 */
public interface SupplierLinkmanService {

    /**
     * 增加供应商联系人
     * @param linkmanPo
     */
    ObjectResponse addSupplierLinkman(LinkmanPo linkmanPo);

    /**
     * 修改供应商联系人
     * @param linkmanPo
     */
    BaseResponse updateSupplierLinkman(LinkmanPo linkmanPo);

    /**
     * 删除供应商联系人
     * @param id
     */
    BaseResponse deleteSupplierLinkman(Long id);

    /**
     * 获取所有的供应商联系人
     * @return
     */
    ObjectResponse getAllSupplierLinkman(Integer pageSize, Integer pageNumber);

    /**
     * 根据供应商名称和联系人名称查找联系人
     * @param supplierName
     * @param name
     * @return
     */
    ObjectResponse getBySupplierNameAndName(String supplierName, String name, Integer pageSize, Integer pageNumber);

    /**
     * 查找供应商联系人
     * @param supplierId
     * @return
     */
    ObjectResponse getBySupplier(Long supplierId, Integer pageSize, Integer pageNumber);

    /**
     * 根据ID查找供应商联系人
     * @param id
     * @return
     */
    ObjectResponse getSupplierLinkmanById(Long id);
}
