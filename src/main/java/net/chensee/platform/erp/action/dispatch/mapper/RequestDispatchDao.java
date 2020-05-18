package net.chensee.platform.erp.action.dispatch.mapper;

import net.chensee.platform.erp.action.dispatch.po.RequestDispatchPo;
import net.chensee.platform.erp.action.dispatch.vo.RequestDispatchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author : shibo
 * @date : 2019/6/19 10:39
 */
@Repository
public interface RequestDispatchDao {

    /**
     * 获取所有派工单
     * @return
     */
    List<RequestDispatchVo> getAllRequestDispatch(@Param("pageStart") Integer pageStart,
                                                  @Param("pageSize") Integer pageSize,
                                                  @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 添加派工单
     * @param requestDispatchPo
     * @return
     */
    void addRequestDispatch(RequestDispatchPo requestDispatchPo);

    /**
     * 修改派工单
     * @param requestDispatchPo
     * @return
     */
    void updateRequestDispatch(RequestDispatchPo requestDispatchPo);

    /**
     * 删除派工单
     * @param id
     * @return
     */
    void deleteRequestDispatch(Long id);

    /**
     * 根据供应商名称和编号获取派工单
     * @param supplierName
     * @param supplierNo
     * @return
     */
    List<RequestDispatchVo> getRequestDispatchBySupplier(String supplierName, String supplierNo, Integer pageStart, Integer pageSize);

    /**
     * 根据ID获取派工单
     * @param id
     * @return
     */
    List<RequestDispatchVo> getById(Long id);

    /**
     * 获取数据条数
     * @param supplierName
     * @param supplierNo
     * @return
     */
    Integer getCount(String supplierName, String supplierNo, @Param("currentFolders") Set<Long> currentFolders);
}
