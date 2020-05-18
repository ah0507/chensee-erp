package net.chensee.platform.erp.action.order.mapper;

import net.chensee.platform.erp.action.order.po.OrderContractPayStagePo;
import net.chensee.platform.erp.action.order.vo.OrderContractPayStageVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : shibo
 * @date : 2019/5/20 17:37
 */
@Repository
public interface OrderContractPayStageDao {

    /**
     * 添加订单-合同-付款阶段
     * @param orderContractPayStagePo
     */
    void addOrderContractPayStage(OrderContractPayStagePo orderContractPayStagePo);

    /**
     * 修改订单-合同-付款阶段
     * @param orderContractPayStagePo
     */
    void updateOrderContractPayStage(OrderContractPayStagePo orderContractPayStagePo);

    /**
     * 删除订单-合同-付款阶段
     * @param id
     */
    void deleteOrderContractPayStage(Long id);

    /**
     * 通过订单ID删除订单-合同-付款阶段
     * @param id
     */
    void deleteByOrderId(Long id);

    /**
     * 通过合同ID查询付款阶段
     * @param contractId
     * @return
     */
    List<OrderContractPayStageVo> getByContractId(Long contractId);

    /**
     * 通过合同ID查询付款阶段
     * @param contractId
     * @return
     */
    List<OrderContractPayStagePo> getPoByContractId(Long contractId);
}
