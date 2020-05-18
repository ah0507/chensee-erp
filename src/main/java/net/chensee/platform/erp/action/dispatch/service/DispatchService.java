package net.chensee.platform.erp.action.dispatch.service;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.dispatch.po.DispatchSheetPo;

/**
 * @author : shibo
 * @date : 2019/5/17 16:04
 */
public interface DispatchService {

    /**
     * 获取所有派工单
     * @return
     */
    ObjectResponse getAllDispatchSheet(Integer pageSize, Integer pageNumber);

    /**
     * 添加派工单
     * @param dispatchSheetPo
     * @return
     */
    ObjectResponse addDispatchSheet(DispatchSheetPo dispatchSheetPo);

    /**
     * 修改派工单
     * @param dispatchSheetPo
     * @return
     */
    BaseResponse updateDispatchSheet(DispatchSheetPo dispatchSheetPo);

    /**
     * 删除派工单
     * @param id
     * @return
     */
    BaseResponse deleteDispatchSheet(Long id);

    /**
     * 根据客户名称和编号获取派工单
     * @param customerName
     * @param customerNo
     * @return
     */
    ObjectResponse getDispatchSheetByCustomers(String customerName, String customerNo, Integer pageSize, Integer pageNumber);

    /**
     * 提交流程审批
     * @param dispatchSheetPo
     * @return
     */
    ObjectResponse submitFlow(DispatchSheetPo dispatchSheetPo) throws Exception;

    /**
     * 根据ID获取派工单
     * @param id
     * @return
     */
    ObjectResponse getById(Long id);
}
