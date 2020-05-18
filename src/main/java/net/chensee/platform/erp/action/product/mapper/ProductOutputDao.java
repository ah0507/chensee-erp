package net.chensee.platform.erp.action.product.mapper;

import net.chensee.platform.erp.action.product.po.ProductOutputPo;
import net.chensee.platform.erp.action.product.vo.ProductOutputVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : shibo
 * @date : 2019/5/24 15:50
 */
@Repository
public interface ProductOutputDao {

    /**
     * 产品出库
     * @param productOutputPo
     * @return
     */
    int addProductOutput(ProductOutputPo productOutputPo);

    /**
     * 删除产品出库信息
     * @param billId
     */
    void deleteProductOutputByBillId(Long billId);

    /**
     * 根据出库票据查找出库产品
     * @param billId
     * @return
     */
    List<ProductOutputVo> getByBillId(Long billId);
}
