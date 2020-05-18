package net.chensee.platform.erp.action.finance.service;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.finance.po.PaymentPo;

/**
 * @author : shibo
 * @date : 2019/5/27 17:30
 */
public interface PaymentService {

    /**
     * 添加付款记录
     * @param paymentPo
     * @return
     */
    ObjectResponse addPayment(PaymentPo paymentPo);

    /**
     * 修改付款记录
     * @param paymentPo
     */
    BaseResponse updatePayment(PaymentPo paymentPo);

    /**
     * 删除付款记录
     * @param id
     */
    BaseResponse deletePayment(Long id);

    /**
     * 获取所有付款信息
     * @return
     */
    ObjectResponse getAllPayment(Integer pageSize, Integer pageNumber);

    /**
     * 根据供应商名称和付款编号查询
     * @param supplierName
     * @param number
     * @return
     */
    ObjectResponse getBySupplierNameAndNumber(String supplierName, String number, Integer pageSize, Integer pageNumber);

    /**
     * 根据合同查询付款信息
     * @param contractId
     * @return
     */
    ObjectResponse getByContractId(Long contractId);

    /**
     * 根据合同查询付款信息
     * @param id
     * @return
     */
    ObjectResponse getPaymentById(Long id);
}
