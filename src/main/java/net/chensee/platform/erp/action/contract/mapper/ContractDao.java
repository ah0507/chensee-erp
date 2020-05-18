package net.chensee.platform.erp.action.contract.mapper;

import net.chensee.platform.erp.action.contract.po.ContractPo;
import net.chensee.platform.erp.action.contract.vo.ContractVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ContractDao {

    /**
     * 获取所有合同
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<ContractVo> getAllContracts(@Param("pageStart") Integer pageStart,
                                     @Param("pageSize") Integer pageSize,
                                     @Param("direct") Integer direct,
                                     @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 根据项目名称或合同编号查询合同
     * @param orderName
     * @param contractNo
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<ContractVo> getByOrderNameAndContractNo(String orderName, String contractNo, Integer pageStart, Integer pageSize, Integer direct);

    /**
     * 添加合同
     * @param contractPo
     */
    int addContract(ContractPo contractPo);

    /**
     * 修改合同
     * @param contractPo
     */
    void updateContract(ContractPo contractPo);

    /**
     * 删除合同
     * @param id
     */
    void deleteContract(Long id);

    /**
     * 通过对方ID查找合同
     * @param otherPartyId
     * @return
     */
    List<ContractVo> getByOtherPartyId(Long otherPartyId, Integer pageStart, Integer pageSize, Integer direct);

    /**
     * 查找条数
     * @param orderName
     * @param contractNo
     * @return
     */
    Integer getCount(String orderName, String contractNo, @Param("direct") Integer direct, @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 查找条数
     * @param otherPartyId
     * @return
     */
    Integer getCountByOtherPartyId(@Param("otherPartyId") Long otherPartyId, @Param("direct") Integer direct);

    /**
     * 根据合同ID查询合同
     * @param contractId
     * @param currentFolders
     * @return
     */
    List<ContractVo> getById(@Param("direct") Integer direct,
                             @Param("contractId") Long contractId,
                             @Param("currentFolders") Set<Long> currentFolders);
}
