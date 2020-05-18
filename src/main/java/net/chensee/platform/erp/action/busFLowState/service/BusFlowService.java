package net.chensee.platform.erp.action.busFLowState.service;

import io.swagger.models.auth.In;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.flow.po.FlowBatchBusPo;
import net.chensee.platform.erp.action.flow.po.FlowPo;

public interface BusFlowService {

    BaseResponse saveOrUpdateBusFlowState(String runFlowId, String businessKey, String state);

    /**
     * 查询具体业务对应的流程状态
     * @param busCode
     * @param busIds
     * @return
     */
    ObjectResponse getBusFlowState(String busCode, String busIds);

}
