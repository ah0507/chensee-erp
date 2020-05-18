package net.chensee.platform.erp.action.contract.service;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.contract.vo.ContractVo;

public interface ContractService {


    /**
     * 获取所有合同
     * @param pageSize
     * @param pageNumber
     * @return
     */
    ObjectResponse getAllContracts(Integer pageSize, Integer pageNumber, Integer direct);

    /**
     * 获取对方所有合同
     * @param otherPartyId
     * @param pageSize
     * @param pageNumber
     * @return
     */
    ObjectResponse getByOtherPartyId(Long otherPartyId, Integer pageSize, Integer pageNumber, Integer direct);

    /**
     * 根据合同名称或合同编号查询合同
     * @param orderName
     * @param contractNo
     * @param pageSize
     * @param pageNumber
     * @return
     */
    ObjectResponse getByOrderNameAndContractNo(String orderName, String contractNo, Integer pageSize, Integer pageNumber, Integer direct);

    /**
     * 根据订单ID生成合同
     * @param orderContractId
     */
    BaseResponse addContract(Long orderContractId, Integer direct) throws Exception;

    /**
     * 修改合同
     * @param contractVo
     */
    BaseResponse updateContract(ContractVo contractVo);

    /**
     * 删除合同
     * @param id
     */
    BaseResponse deleteContract(Long id) throws Exception;

    /**
     * 根据合同ID查询合同
     * @param direct
     * @param contractId
     */
    ObjectResponse getById(Integer direct, Long contractId);
}
