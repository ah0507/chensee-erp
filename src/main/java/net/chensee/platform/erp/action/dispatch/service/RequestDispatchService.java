package net.chensee.platform.erp.action.dispatch.service;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.dispatch.po.RequestDispatchPo;

/**
 * @author : shibo
 * @date : 2019/6/19 10:43
 */
public interface RequestDispatchService {

    /**
     * 获取所有派工单
     * @return
     */
    ObjectResponse getAllRequestDispatch(Integer pageSize, Integer pageNumber);

    /**
     * 添加派工单
     * @param requestDispatchPo
     * @return
     */
    ObjectResponse addRequestDispatch(RequestDispatchPo requestDispatchPo);

    /**
     * 修改派工单
     * @param requestDispatchPo
     * @return
     */
    BaseResponse updateRequestDispatch(RequestDispatchPo requestDispatchPo);

    /**
     * 删除派工单
     * @param id
     * @return
     */
    BaseResponse deleteRequestDispatch(Long id);

    /**
     * 根据供应商名称和编号获取派工单
     * @param supplierName
     * @param supplierNo
     * @return
     */
    ObjectResponse getRequestDispatchBySupplier(String supplierName, String supplierNo, Integer pageSize, Integer pageNumber);

    /**
     * 根据ID获取派工单
     * @param id
     * @return
     */
    ObjectResponse getById(Long id);
}
