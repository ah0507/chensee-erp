package net.chensee.platform.erp.action.order.mapper;

import net.chensee.platform.erp.action.order.po.OrderPo;
import net.chensee.platform.erp.action.order.vo.OrderVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface OrderDao {

    /**
     * 获取所有订单
     * @return
     */
    List<OrderVo> getAllOrders(@Param("pageStart") Integer pageStart,
                               @Param("pageSize") Integer pageSize,
                               @Param("direct") Integer direct,
                               @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 通过名称和编号查找订单
     * @param name
     * @param number
     * @return
     */
    List<OrderVo> getByNameAndNo(String name, String number, Integer pageStart, Integer pageSize, Integer direct);

    /**
     * 增加订单
     * @param orderPo
     */
    int addOrder(OrderPo orderPo);

    /**
     * 修改订单
     * @param orderPo
     */
    void updateOrder(OrderPo orderPo);

    /**
     * 删除订单
     * @param id
     */
    void deleteOrder(Long id);

    /**
     * 获取数据条数
     * @param name
     * @param number
     * @return
     */
    Integer getCount(String name, String number, Integer direct, @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 通过合同查找订单
     * @param contractId
     * @return
     */
    OrderPo getByContractId(Long contractId);

    /**
     * 通过ID查找订单
     * @param direct
     * @param orderId
     * @return
     */
    List<OrderVo> getById(@Param("direct") Integer direct,
                          @Param("orderId") Long orderId,
                          @Param("currentFolders") Set<Long> currentFolders);
}
