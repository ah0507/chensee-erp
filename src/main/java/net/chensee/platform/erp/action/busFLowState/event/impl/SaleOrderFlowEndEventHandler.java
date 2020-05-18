package net.chensee.platform.erp.action.busFLowState.event.impl;

import lombok.extern.slf4j.Slf4j;
import net.chensee.platform.erp.action.busFLowState.event.EventHandler;
import net.chensee.platform.erp.action.contract.mapper.ContractDao;
import net.chensee.platform.erp.action.contract.service.ContractService;
import net.chensee.platform.erp.action.order.service.OrderService;
import net.chensee.platform.erp.action.order.vo.OrderContractVo;
import net.chensee.platform.erp.action.order.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author wh
 * @version 1.0
 * @date 2020/3/26 15:59
 */
@Slf4j
@Component
public class SaleOrderFlowEndEventHandler implements EventHandler {

    private String SALE_ORDER = "saleOrder";

    @Autowired
    private OrderService orderService;
    @Autowired
    private ContractService contractService;

    @Override
    public void doHandler(HttpServletRequest request) {
        String busId = getBusId(request);
        OrderVo orderVo = (OrderVo) orderService.getById(1, Long.valueOf(busId)).getData();
        List<OrderContractVo> contracts = orderVo.getContracts();
        for (OrderContractVo orderContractVo : contracts) {
            try {
                contractService.addContract(orderContractVo.getId(),1);
                log.debug("=======销售订单"+orderVo.getId()+"生成合同成功=====");
            } catch (Exception e) {
                log.error(orderContractVo.getId()+"生成销售合同异常!",e);
            }
        }
    }

    @Override
    public boolean canHandler(HttpServletRequest request) {
        String taskType = getTaskType(request);
        String busCode = getBusCode(request);
        return END.equals(taskType) && SALE_ORDER.equals(busCode);
    }
}
