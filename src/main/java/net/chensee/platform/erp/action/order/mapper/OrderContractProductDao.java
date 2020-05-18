package net.chensee.platform.erp.action.order.mapper;

import net.chensee.platform.erp.action.order.po.OrderContractProductPo;
import net.chensee.platform.erp.action.order.vo.OrderContractProductVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : shibo
 * @date : 2019/5/20 17:37
 */
@Repository
public interface OrderContractProductDao {

    /**
     * 添加订单-合同-产品
     * @param orderContractProductPo
     */
    void addOrderContractProduct(OrderContractProductPo orderContractProductPo);

    /**
     * 修改订单-合同-产品
     * @param orderContractProductPo
     */
    void updateOrderContractProduct(OrderContractProductPo orderContractProductPo);

    /**
     * 删除订单-合同-产品
     * @param id
     */
    void deleteOrderContractProduct(Long id);

    /**
     * 通过订单ID删除订单-合同-产品
     * @param id
     */
    void deleteByOrderId(Long id);

    /**
     * 通过合同ID查询产品
     * @param contractId
     * @return
     */
    List<OrderContractProductVo> getByContractId(Long contractId);

    /**
     * 通过合同ID查询产品
     * @param contractId
     * @return
     */
    List<OrderContractProductPo> getPoByContractId(Long contractId);
}
