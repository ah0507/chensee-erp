package net.chensee.platform.erp.action.customer.mapper;

import net.chensee.platform.erp.action.customer.po.FinancialInfoPo;
import org.springframework.stereotype.Repository;

/**
 * @author : shibo
 * @date : 2019/5/20 15:43
 */
@Repository
public interface FinancialInfoDao {

    /**
     * 通过ID查找客户财务信息
     * @param id
     * @return
     */
    FinancialInfoPo getById(Long id);

    /**
     * 添加客户财务信息
     * @param financialInfoPo
     */
    void addFinancialInfo(FinancialInfoPo financialInfoPo);

    /**
     * 修改客户财务信息
     * @param financialInfoPo
     */
    void updateFinancialInfo(FinancialInfoPo financialInfoPo);

    /**
     * 删除客户财务信息
     * @param id
     */
    void deleteFinancialInfo(Long id);

    /**
     * 通过客户ID删除财务信息
     * @param id
     */
    void deleteByCustomerId(Long id);

    /**
     * 通过供应商ID删除财务信息
     * @param id
     */
    void deleteBySupplierId(Long id);

    /**
     * 通过客户ID查找财务信息
     * @param id
     */
    FinancialInfoPo getByCustomerId(Long id);

    /**
     * 通过供应商ID查找财务信息
     * @param id
     */
    FinancialInfoPo getBySupplierId(Long id);
}
