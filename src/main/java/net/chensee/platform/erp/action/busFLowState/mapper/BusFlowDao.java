package net.chensee.platform.erp.action.busFLowState.mapper;

import net.chensee.platform.erp.action.busFLowState.vo.BusFlowVo;
import net.chensee.platform.erp.action.business.po.BusinessFlowLogPo;
import net.chensee.platform.erp.action.business.vo.BusinessVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusFlowDao {

    /**
     * 保存或更新业务对应的流程状态信息
     * @param busCode
     * @param busId
     * @param runFlowId
     */
    void saveOrUpdateBusFlowState(String busCode, Long busId, String runFlowId, int flowState);

    /**
     * 查询业务对应的流程状态信息
     * @param busCode
     * @param busId
     */
    String getFlowStateByBusCodeId(String busCode, Long busId);

    /**
     * 批量查询业务对应的流程状态信息
     * @param busCode
     * @param busIdList
     */
    List<BusFlowVo> getFlowStates(String busCode, List<String> busIdList);

    /**
     * 查询业务对应的流程
     * @param busCode
     * @param busId
     */
    BusFlowVo getFlow(String busCode, Long busId);
}
