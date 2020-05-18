package net.chensee.platform.erp.action.customer.service;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.customer.po.LinkmanPo;

/**
 * @author : shibo
 * @date : 2019/5/20 9:27
 */
public interface CustomerLinkmanService {

    /**
     * 增加客户联系人
     * @param customerLinkmanPo
     */
    ObjectResponse addCustomerLinkman(LinkmanPo customerLinkmanPo);

    /**
     * 修改客户联系人
     * @param customerLinkmanPo
     */
    BaseResponse updateCustomerLinkman(LinkmanPo customerLinkmanPo);

    /**
     * 删除客户联系人
     * @param id
     */
    BaseResponse deleteCustomerLinkman(Long id);

    /**
     * 获取所有的客户联系人
     * @return
     */
    ObjectResponse getAllCustomerLinkman(Integer pageSize, Integer pageNumber);

    /**
     * 根据客户名称和联系人名称查找联系人
     * @param customerName
     * @param name
     * @return
     */
    ObjectResponse getByCustomerNameAndName(String customerName, String name, Integer pageSize, Integer pageNumber);

    /**
     * 查找客户联系人
     * @param customerId
     * @return
     */
    ObjectResponse getByCustomer(Long customerId, Integer pageSize, Integer pageNumber);

    /**
     * 根据ID查找客户联系人
     * @param id
     * @return
     */
    ObjectResponse getCustomerLinkmanById(Long id);
}
