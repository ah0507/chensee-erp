package net.chensee.platform.erp.action.finance.service;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.finance.po.CollectPo;
import net.chensee.platform.erp.action.finance.vo.CollectVo;

/**
 * @author : shibo
 * @date : 2019/5/27 17:30
 */
public interface CollectService {

    /**
     * 添加收款记录
     * @param collectPo
     * @return
     */
    ObjectResponse addCollect(CollectPo collectPo);

    /**
     * 修改收款记录
     * @param collectPo
     */
    BaseResponse updateCollect(CollectPo collectPo);

    /**
     * 删除收款记录
     * @param id
     */
    BaseResponse deleteCollect(Long id);

    /**
     * 获取所有收款信息
     * @return
     */
    ObjectResponse getAllCollect(Integer pageSize, Integer pageNumber);

    /**
     * 根据客户名称和收款编号查询
     * @param customerName
     * @param number
     * @return
     */
    ObjectResponse getByCustomerNameAndNumber(String customerName, String number, Integer pageSize, Integer pageNumber);

    /**
     * 根据合同查询收款信息
     * @param contractId
     * @return
     */
    ObjectResponse getByContractId(Long contractId);

    /**
     * 根据ID查询收款信息
     * @param id
     * @return
     */
    ObjectResponse getCollectById(Long id);
}
