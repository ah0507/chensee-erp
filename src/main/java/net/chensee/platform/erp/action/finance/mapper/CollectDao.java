package net.chensee.platform.erp.action.finance.mapper;

import net.chensee.platform.erp.action.finance.po.CollectPo;
import net.chensee.platform.erp.action.finance.vo.CollectVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author : shibo
 * @date : 2019/5/27 17:14
 */
@Repository
public interface CollectDao {

    /**
     * 添加收款记录
     * @param collectPo
     * @return
     */
    int addCollect(CollectPo collectPo);

    /**
     * 修改收款记录
     * @param collectPo
     */
    void updateCollect(CollectPo collectPo);

    /**
     * 删除收款记录
     * @param id
     */
    void deleteCollect(Long id);

    /**
     * 获取所有收款信息
     * @return
     */
    List<CollectVo> getAllCollect(@Param("pageStart") Integer pageStart,
                                  @Param("pageSize") Integer pageSize,
                                  @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 根据客户名称和收款编号查询
     * @param customerName
     * @param number
     * @return
     */
    List<CollectVo> getByCustomerNameAndNumber(String customerName, String number, Integer pageStart, Integer pageSize);

    /**
     * 根据合同查询收款信息
     * @param contractId
     * @return
     */
    List<CollectVo> getByContractId(Long contractId);

    /**
     * 获取数据条数
     * @param customerName
     * @param number
     * @return
     */
    Integer getCount(String customerName, String number, @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 根据ID获取收款信息
     * @param id
     * @param currentFolders
     * @return
     */
    List<CollectVo> getCollectById(Long id, @Param("currentFolders") Set<Long> currentFolders);
}
