package net.chensee.platform.erp.action.order.service;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.order.vo.OrderVo;

public interface OrderService {

    /**
     * 获取所有订单
     * @return
     */
    ObjectResponse getAllOrders(Integer pageSize, Integer pageNumber, Integer direct);

    /**
     * 根据名称和编号查询订单
     * @param name
     * @param no
     * @return
     */
    ObjectResponse getByNameAndNo(String name, String no, Integer pageSize, Integer pageNumber, Integer direct);

    /**
     * 添加订单
     * @param orderVo
     * @return
     */
    BaseResponse addOrder(OrderVo orderVo) throws Exception;

    /**
     * 修改订单
     * @param orderVo
     * @return
     */
    BaseResponse updateOrder(OrderVo orderVo) throws Exception;

    /**
     * 删除订单
     * @param id
     */
    BaseResponse deleteOrder(Long id) throws Exception;

    /**
     * 根据ID获取合同信息
     * @param direct
     * @param orderId
     */
    ObjectResponse getById(Integer direct, Long orderId);
}
