package net.chensee.platform.erp.action.contract.mapper;

import net.chensee.platform.erp.action.contract.po.ContractTrackPo;
import net.chensee.platform.erp.action.contract.vo.ContractTrackVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : shibo
 * @date : 2019/5/28 12:59
 */
@Repository
public interface ContractTrackDao {

    /**
     * 添加履约阶段
     * @param contractTrackPo
     * @return
     */
    int addContractTrack(ContractTrackPo contractTrackPo);

    /**
     * 更新履约阶段
     * @param contractTrackPo
     */
    void updateContractTrack(ContractTrackPo contractTrackPo);

    /**
     * 删除履约阶段
     * @param contractId
     */
    void deleteByContractId(Long contractId);

    /**
     * 根据合同获取履约阶段
     * @param contractId
     * @return
     */
    List<ContractTrackVo> getByContractId(Long contractId);

    /**
     * 删除已弃除的阶段
     * @param contractId
     * @param list
     */
    void deleteRedundancy(Long contractId, List<Long> list);
}
