package net.chensee.platform.erp.action.finance.service;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.finance.po.FinanceOfCompanyPo;
import net.chensee.platform.erp.action.finance.vo.BillVo;

public interface FinanceOfCompanyService {

    /**
     * 获取所有本公司财务信息记录
     * @param pageSize
     * @param pageNumber
     * @return
     */
    ObjectResponse getAllRecords(Integer pageSize, Integer pageNumber);

    /**
     * 根据条件获取本公司财务信息记录
     * @param userName
     * @param pageSize
     * @param pageNumber
     * @return
     */
    ObjectResponse getByCondition(String userName, Integer pageSize, Integer pageNumber);

    /**
     * 新增本公司财务信息记录
     * @param financeOfCompanyPo
     * @return
     */
    BaseResponse addRecord(FinanceOfCompanyPo financeOfCompanyPo);

    /**
     * 更新本公司财务信息记录
     * @param financeOfCompanyPo
     * @return
     */
    BaseResponse updateRecord(FinanceOfCompanyPo financeOfCompanyPo);

    /**
     * 根据ID删除本公司财务信息记录
     * @param id
     * @return
     */
    BaseResponse deleteRecord(Long id);
}
