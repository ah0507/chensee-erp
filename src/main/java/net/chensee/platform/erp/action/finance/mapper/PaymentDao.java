package net.chensee.platform.erp.action.finance.mapper;

import net.chensee.platform.erp.action.finance.po.PaymentPo;
import net.chensee.platform.erp.action.finance.vo.PaymentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author : shibo
 * @date : 2019/5/27 17:14
 */
@Repository
public interface PaymentDao {

    /**
     * 添加付款记录
     * @param paymentPo
     * @return
     */
    int addPayment(PaymentPo paymentPo);

    /**
     * 修改付款记录
     * @param paymentPo
     */
    void updatePayment(PaymentPo paymentPo);

    /**
     * 删除付款记录
     * @param id
     */
    void deletePayment(Long id);

    /**
     * 获取所有付款信息
     * @return
     */
    List<PaymentVo> getAllPayment(@Param("pageStart") Integer pageStart,
                                  @Param("pageSize") Integer pageSize,
                                  @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 根据供应商名称和付款编号查询
     * @param supplierName
     * @param number
     * @return
     */
    List<PaymentVo> getBySupplierNameAndNumber(String supplierName, String number, Integer pageStart, Integer pageSize);

    /**
     * 根据合同查询付款信息
     * @param contractId
     * @return
     */
    List<PaymentVo> getByContractId(Long contractId);

    /**
     * 获取数据条数
     * @param supplierName
     * @param number
     * @return
     */
    Integer getCount(String supplierName, String number, @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 根据ID获取付款信息
     * @param id
     * @param currentFolders
     * @return
     */
    List<PaymentVo> getPaymentById(Long id, @Param("currentFolders") Set<Long> currentFolders);
}
