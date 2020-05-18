package net.chensee.platform.erp.action.contract.mapper;

import net.chensee.platform.erp.action.contract.po.ContractPayStagePo;
import net.chensee.platform.erp.action.contract.vo.ContractPayStageVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : shibo
 * @date : 2019/5/20 17:37
 */
@Repository
public interface ContractPayStageDao {

    /**
     * 添加合同-付款阶段
     * @param contractPayStagePo
     */
    void addContractPayStage(ContractPayStagePo contractPayStagePo);

    /**
     * 修改合同-付款阶段
     * @param contractPayStagePo
     */
    void updateContractPayStage(ContractPayStagePo contractPayStagePo);

    /**
     * 删除合同-付款阶段
     * @param id
     */
    void deleteContractPayStage(Long id);

    /**
     * 通过订单ID删除合同-付款阶段
     * @param id
     */
    void deleteByContractId(Long id);

    /**
     * 通过合同ID查询付款阶段
     * @param contractId
     * @return
     */
    List<ContractPayStageVo> getByContractId(Long contractId);
}
