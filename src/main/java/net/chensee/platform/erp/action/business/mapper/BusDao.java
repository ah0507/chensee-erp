package net.chensee.platform.erp.action.business.mapper;

import net.chensee.platform.erp.action.business.po.BusinessFlowLogPo;
import net.chensee.platform.erp.action.business.po.BusinessRolePo;
import net.chensee.platform.erp.action.business.vo.BusinessRoleVo;
import net.chensee.platform.erp.action.business.vo.BusinessVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BusDao {

    /**
     * 获取业务信息
     * @return
     */
    List<BusinessVo> getAllBusiness();

    /**
     * 根据Code获取业务信息
     * @param code
     * @return
     */
    List<BusinessVo> getBusinessByCode(@Param("code") String code);

    /**
     * 根据Code获取业务信息
     * @param businessFlowLogPo
     */
    void insertBusFlowInfo(BusinessFlowLogPo businessFlowLogPo);

    /**
     * 保存或更新业务对应的流程状态信息
     * @param busCode
     * @param busId
     * @param runFlowId
     */
    void saveOrUpdateBusFlowState(String busCode, Long busId, String runFlowId, boolean flowState);

    /**
     * 查询业务对应的流程状态信息
     * @param busCode
     * @param busId
     */
    String getFlowStateByBusCodeId(String busCode, Long busId);
}
