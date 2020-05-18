package net.chensee.platform.erp.action.flow.mapper;

import net.chensee.platform.erp.action.busFLowState.vo.BusFlowVo;
import net.chensee.platform.erp.action.business.po.BusinessRolePo;
import net.chensee.platform.erp.action.business.vo.BusinessRoleVo;
import net.chensee.platform.erp.action.business.vo.BusinessVo;
import net.chensee.platform.erp.action.flow.po.FlowPo;
import net.chensee.platform.erp.action.flow.vo.FlowVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowDao {

    /**
     * 获取业务流程关联信息
     * @return
     */
    List<FlowVo> getFlowBusRelations();

    /**
     * 根据ID获取业务流程关联信息
     * @param id
     * @return
     */
    List<FlowVo> getFlowBusRelationById(@Param("id") Long id);

    /**
     * 根据业务ID获取业务流程关联信息
     * @param businessId
     * @return
     */
    List<FlowVo> getFlowBusRelationByBusId(@Param("businessId") Long businessId);

    /**
     * 添加业务流程关联信息
     * @param flowPo
     * @return
     */
    void addFlowBusRelation(FlowPo flowPo);

    /**
     * 删除业务流程关联信息
     * @param id
     * @return
     */
    void deleteFlowBusRelation(@Param("id") Long id);

    /**
     * 根据流程ID获取关联的业务信息
     * @param flowId
     * @return
     */
    List<BusinessVo> getBusinessByFlowId(@Param("flowId") String flowId);

    /**
     * 批量添加流程业务关联信息
     * @param flowId
     * @param busIds
     * @return
     */
    void addBatchFlowBusRelations(@Param("flowId") String flowId,
                                  @Param("busIds") List<Long> busIds);

    /**
     * 根据业务ID批量删除流程业务关联信息
     * @param list
     * @return
     */
    void deleteRelationByBusId(@Param("list") List<Long> list);

}
