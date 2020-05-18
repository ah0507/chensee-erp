package net.chensee.platform.erp.action.product.mapper;

import net.chensee.platform.erp.action.product.po.ProductInputPo;
import net.chensee.platform.erp.action.product.vo.ProductInputVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : shibo
 * @date : 2019/5/24 15:50
 */
@Repository
public interface ProductInputDao {

    /**
     * 产品入库
     * @param productInputPo
     * @return
     */
    int addProductInput(ProductInputPo productInputPo);

    /**
     * 删除产品入库信息
     * @param billId
     */
    void deleteProductInputByBillId(Long billId);

    /**
     * 根据出库票据查找入库产品
     * @param billId
     * @return
     */
    List<ProductInputVo> getByBillId(Long billId);

    /**
     * 根据产品ID查找入库产品
     * @param productId
     * @return
     */
    List<ProductInputVo> getByProductId(Long productId);
}
