package net.chensee.platform.erp.action.busFLowState.event.impl;

import lombok.extern.slf4j.Slf4j;
import net.chensee.platform.erp.action.busFLowState.event.EventHandler;
import net.chensee.platform.erp.action.busFLowState.service.BusFlowService;
import net.chensee.platform.erp.action.contract.service.ContractService;
import net.chensee.platform.erp.action.order.service.OrderService;
import net.chensee.platform.erp.action.order.vo.OrderContractVo;
import net.chensee.platform.erp.action.order.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author wh
 * @version 1.0
 * @date 2020/3/26 15:59
 */
@Slf4j
@Component
public class CommonFlowStartEventHandler implements EventHandler {

    @Autowired
    private BusFlowService busFlowService;

    @Override
    public void doHandler(HttpServletRequest request) {
        //添加 busFlowState记录
        try {
            busFlowService.saveOrUpdateBusFlowState(getRunFlowId(request), getBusKey(request), getState(request));
        } catch (Exception e) {
            log.error(getBusKey(request)+"添加审批记录异常!",e);
        }
    }

    @Override
    public boolean canHandler(HttpServletRequest request) {
        return START.equals(getTaskType(request));
    }
}
