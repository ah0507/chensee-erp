package net.chensee.platform.erp.action.order.mapper;

import net.chensee.platform.erp.action.order.po.OrderContractPo;
import net.chensee.platform.erp.action.order.vo.OrderContractVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : shibo
 * @date : 2019/5/20 16:39
 */
@Repository
public interface OrderContractDao {

    /**
     * 添加订单可生成的合同
     * @param orderContractPo
     */
    int addOrderContract(OrderContractPo orderContractPo);

    /**
     * 修改订单可生成的合同
     * @param orderContractPo
     */
    void updateOrderContract(OrderContractPo orderContractPo);

    /**
     * 删除订单可生成的合同
     * @param id
     */
    void deleteOrderContract(Long id);

    /**
     * 通过订单ID删除合同
     * @param id
     */
    void deleteByOrderId(Long id);

    /**
     * 通过订单ID查询合同
     * @param orderId
     * @return
     */
    List<OrderContractVo> getByOrderId(Long orderId);


    /**
     * 通过ID查询合同
     * @param id
     * @return
     */
    OrderContractPo getById(Long id);
}
