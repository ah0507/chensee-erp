package net.chensee.platform.erp.action.finance.mapper;

import net.chensee.platform.erp.action.finance.po.BillPo;
import net.chensee.platform.erp.action.finance.vo.BillVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author : shibo
 * @date : 2019/5/27 11:12
 */
@Repository
public interface BillDao {

    /**
     * 获取所有票据
     * @param direct
     * @return
     */
    List<BillVo> getAllBill(Integer pageStart, Integer pageSize, @Param("direct") Integer direct, @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 获取合同所有票据
     * @param contractId
     * @param direct
     * @return
     */
    List<BillVo> getByContractId(Integer direct, Long contractId, Integer pageStart, Integer pageSize);

    /**
     * 根据对方名称或者票据编号查询票据
     * @param otherPartyName
     * @param number
     * @param direct
     * @return
     */
    List<BillVo> getByOtherPartyNameAndNumber(String otherPartyName, String number, Integer pageStart, Integer pageSize, @Param("direct") Integer direct);

    /**
     * 添加票据
     * @param billPo
     * @return
     */
    int addBill(BillPo billPo);

    /**
     * 更新票据
     * @param billPo
     */
    void updateBill(BillPo billPo);

    /**
     * 删除票据
     * @param id
     */
    void deleteBill(Long id);

    /**
     * 获取数据条数
     * @param otherPartyName
     * @param number
     * @param direct
     * @return
     */
    Integer getCount(String otherPartyName, String number, @Param("direct") Integer direct, @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 获取数据条数
     * @param direct
     * @param contractId
     * @return
     */
    Integer getCountByContractId(Integer direct, @Param("contractId") Long contractId);

    /**
     * 根据ID获取收款票据
     * @param id
     * @return
     */
    List<BillVo> getReceiveById(Long id);

    /**
     * 根据ID获取付款票据
     * @param id
     * @return
     */
    List<BillVo> getPayById(Long id);
}
