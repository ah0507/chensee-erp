package net.chensee.platform.erp.action.customer.service;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.customer.po.CustomerActivityPo;

/**
 * @author : shibo
 * @date : 2019/5/20 9:27
 */
public interface CustomerActivityService {

    /**
     * 增加客户活动
     * @param customerActivityPo
     */
    ObjectResponse addCustomerActivity(CustomerActivityPo customerActivityPo);

    /**
     * 修改客户活动
     * @param customerActivityPo
     */
    BaseResponse updateCustomerActivity(CustomerActivityPo customerActivityPo);

    /**
     * 删除客户活动
     * @param id
     */
    BaseResponse deleteCustomerActivity(Long id);

    /**
     * 获取所有的客户活动
     * @return
     */
    ObjectResponse getAllCustomerActivity(Integer pageSize, Integer pageNumber);

    /**
     * 根据活动主题和编号查找活动
     * @param theme
     * @param no
     * @return
     */
    ObjectResponse getByThemeAndNo(String theme, String no, Integer pageSize, Integer pageNumber);

    /**
     * 根据ID获取客户活动
     * @param id
     * @return
     */
    BaseResponse getCustomerActivityById(Long id);
}
