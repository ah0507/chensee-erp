package net.chensee.platform.erp.action.customer.service;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.customer.vo.CustomerVo;


/**
 * @author : shibo
 * @date : 2019/5/17 17:39
 */
public interface CustomerService {

    /**
     * 获取所有客户
     * @return
     */
    ObjectResponse getAllCustomers(Integer pageSize, Integer pageNumber);

    /**
     * 根据客户名称和编号查询客户
     * @param name
     * @param no
     * @return
     */
    ObjectResponse getByNameAndNo(String name, String no, Integer pageSize, Integer pageNumber);

    /**
     * 增加客户
     * @param customerVo
     */
    ObjectResponse addCustomer(CustomerVo customerVo) throws Exception;

    /**
     * 修改客户
     * @param customerVo
     */
    BaseResponse updateCustomer(CustomerVo customerVo) throws Exception;

    /**
     * 删除客户
     * @param id
     */
    BaseResponse deleteCustomer(Long id) throws Exception;

    /**
     * 根据ID查询客户
     * @param id
     */
    ObjectResponse getById(Long id);
}
