package net.chensee.platform.erp.action.finance.service;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.finance.vo.BillVo;

/**
 * @author : shibo
 * @date : 2019/5/27 16:02
 */
public interface BillService {

    /**
     * 获取所有票据
     * @return
     */
    ObjectResponse getAllBill(Integer pageSize, Integer pageNumber, Integer direct);

    /**
     * 获取合同所有票据
     * @param direct
     * @param contractId
     * @return
     */
    ObjectResponse getByContractId(Integer direct, Long contractId, Integer pageSize, Integer pageNumber);

    /**
     * 根据客户名称或者票据编号查询票据
     * @param otherPartyName
     * @param number
     * @return
     */
    ObjectResponse getByOtherPartyNameAndNumber(String otherPartyName, String number, Integer pageSize, Integer pageNumber, Integer direct);

    /**
     * 添加票据
     * @param billVo
     * @return
     */
    BaseResponse addBill(BillVo billVo) throws Exception;

    /**
     * 更新票据
     * @param billVo
     */
    BaseResponse updateBill(BillVo billVo) throws Exception;

    /**
     * 删除票据
     * @param id
     */
    BaseResponse deleteBill(Long id);

    /**
     * 通过ID获取收款票据
     * @param id
     */
    ObjectResponse getReceiveById(Long id);

    /**
     * 通过ID获取付款票据
     * @param id
     */
    ObjectResponse getPayById(Long id);
}
