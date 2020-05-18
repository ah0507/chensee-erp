package net.chensee.platform.erp.action.customer.service;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.customer.po.FinancialInfoPo;
/**
 * @author : shibo
 * @date : 2019/5/20 15:54
 */
public interface FinancialInfoService {

    /**
     * 通过ID查找客户财务信息
     * @param id
     * @return
     */
    ObjectResponse getById(Long id);

    /**
     * 添加客户财务信息
     * @param financialInfoPo
     */
    BaseResponse addFinancialInfo(FinancialInfoPo financialInfoPo);

    /**
     * 修改客户财务信息
     * @param financialInfoPo
     */
    BaseResponse updateFinancialInfo(FinancialInfoPo financialInfoPo);

    /**
     * 删除客户财务信息
     * @param id
     */
    BaseResponse deleteFinancialInfo(Long id);
}
