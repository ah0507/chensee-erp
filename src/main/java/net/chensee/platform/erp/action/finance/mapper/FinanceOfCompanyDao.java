package net.chensee.platform.erp.action.finance.mapper;

import net.chensee.base.common.BaseResponse;
import net.chensee.platform.erp.action.finance.po.BillPo;
import net.chensee.platform.erp.action.finance.po.FinanceOfCompanyPo;
import net.chensee.platform.erp.action.finance.vo.BillVo;
import net.chensee.platform.erp.action.finance.vo.FinanceOfCompanyVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author : shibo
 * @date : 2019/5/27 11:12
 */
@Repository
public interface FinanceOfCompanyDao {

    /**
     * 获取所有本公司财务信息记录
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<FinanceOfCompanyVo> getAllRecords(Integer pageStart,
                                           Integer pageSize,
                                           Set<Long> currentFolders);

    /**
     * 根据条件获取本公司财务信息记录
     * @param userName
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<FinanceOfCompanyVo> getByCondition(String userName,
                                            Integer pageStart,
                                            Integer pageSize,
                                            Set<Long> currentFolders);

    /**
     * 获取数据条数
     * @param userName
     * @param currentFolders
     * @return
     */
    Integer getCount(String userName,
                     @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 新增本公司财务信息记录
     * @param financeOfCompanyPo
     * @return
     */
    BaseResponse addRecord(FinanceOfCompanyPo financeOfCompanyPo);

    /**
     * 更新本公司财务信息记录
     * @param financeOfCompanyPo
     * @return
     */
    BaseResponse updateRecord(FinanceOfCompanyPo financeOfCompanyPo);

    /**
     * 根据ID删除本公司财务信息记录
     * @param id
     * @return
     */
    BaseResponse deleteRecord(Long id);
}
