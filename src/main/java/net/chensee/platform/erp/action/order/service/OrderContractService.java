package net.chensee.platform.erp.action.order.service;

import net.chensee.platform.erp.action.order.po.OrderContractPo;

public interface OrderContractService {

    /**
     * 添加订单可生成的合同
     * @param orderContractPo
     */
    Long addOrderContract(OrderContractPo orderContractPo);

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
}
