package net.chensee.platform.erp.action.flow.service;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.business.po.BusinessRolePo;
import net.chensee.platform.erp.action.flow.po.FlowBatchBusPo;
import net.chensee.platform.erp.action.flow.po.FlowPo;

import java.util.List;

public interface FlowService {
    /**
     * 获取业务流程关联信息
     * @return
     */
    ObjectResponse getFlowBusRelations();

    /**
     * 根据ID获取业务流程关联信息
     * @param id
     * @return
     */
    ObjectResponse getFlowBusRelationById(Long id);

    /**
     * 添加业务流程关联信息
     * @param flowPo
     * @return
     */
    BaseResponse addFlowBusRelation(FlowPo flowPo);

    /**
     * 删除业务流程关联信息
     * @param id
     * @return
     */
    BaseResponse deleteFlowBusRelation(Long id);


    /**
     * 根据流程ID获取关联的业务信息
     * @param flowId
     * @return
     */
    BaseResponse getBusinessByFlowId(String flowId);

    /**
     * 批量添加流程业务关联信息
     * @param flowBatchBusPo
     * @return
     */
    BaseResponse addBatchFlowBusRelations(FlowBatchBusPo flowBatchBusPo);

    /**
     * 发起流程
     * @param busCode
     * @return
     */
    BaseResponse launchFlow(String busCode,Long busId);

    /**
     * 通过businessKey获取当前及之前节点的表单
     * @param businessKey
     * @return
     */
    ObjectResponse getFormsByTaskId(String businessKey);

    /**
     * 通过已完成任务的taskId获取关联的表单
     * @param taskId
     * @return
     */
    ObjectResponse getFormByDoneTaskId(String taskId);

    /**
     * 完成当前节点并提交当前表单
     * @param formId
     * @param businessKey
     * @param data
     * @return
     */
    BaseResponse submitFormData(Long formId, String businessKey, Long userId, String data);

    /**
     * 查看已办任务
     * @param userId
     * @return
     */
    ObjectResponse getAlreadyDoneTask(Long userId);

    /**
     * 查看待办任务
     * @param userId
     * @return
     */
    ObjectResponse getToDoTask(Long userId);

    /**
     * 签收待办任务
     * @param userId
     * @param taskId
     * @return
     */
    BaseResponse signTodoTask(Long userId, String taskId);

    /**
     * 签收待办任务
     * @param runFlowId
     * @param businessKey
     * @return
     */
    BaseResponse saveOrUpdateBusFlowState(String runFlowId, String businessKey);

    /**
     * 查询具体业务关联的所有表单
     * @param busCode
     * @param busId
     * @return
     */
    ObjectResponse getBusFormData(String busCode, String busId);

    /**
     * 查询所有流程
     * @return
     */
    ObjectResponse getAllFlows();
}
