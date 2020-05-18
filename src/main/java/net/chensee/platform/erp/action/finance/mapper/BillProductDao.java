package net.chensee.platform.erp.action.finance.mapper;

import net.chensee.platform.erp.action.finance.po.BillProductPo;
import net.chensee.platform.erp.action.finance.vo.BillProductVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : shibo
 * @date : 2019/5/27 11:19
 */
@Repository
public interface BillProductDao {

    /**
     * 根据票据ID查询票据产品
     * @param billId
     * @return
     */
    List<BillProductVo> getByBillId(Long billId);

    /**
     * 添加票据产品
     * @param billProductPo
     * @return
     */
    int addBillProduct(BillProductPo billProductPo);

    /**
     * 修改票据产品
     * @param billProductPo
     */
    void updateBillProduct(BillProductPo billProductPo);

    /**
     * 删除票据产品
     * @param billId
     */
    void deleteByBillId(Long billId);
}
