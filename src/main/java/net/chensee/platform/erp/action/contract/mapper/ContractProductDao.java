package net.chensee.platform.erp.action.contract.mapper;

import net.chensee.platform.erp.action.contract.po.ContractProductPo;
import net.chensee.platform.erp.action.contract.vo.ContractProductVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : shibo
 * @date : 2019/5/20 17:37
 */
@Repository
public interface ContractProductDao {

    /**
     * 添加合同-产品
     * @param contractProductPo
     */
    void addContractProduct(ContractProductPo contractProductPo);

    /**
     * 修改合同-产品
     * @param contractProductPo
     */
    void updateContractProduct(ContractProductPo contractProductPo);

    /**
     * 删除合同-产品
     * @param id
     */
    void deleteContractProduct(Long id);

    /**
     * 通过订单ID删除合同-产品
     * @param id
     */
    void deleteByContractId(Long id);

    /**
     * 通过合同ID查询产品
     * @param contractId
     * @return
     */
    List<ContractProductVo> getByContractId(Long contractId);
}
