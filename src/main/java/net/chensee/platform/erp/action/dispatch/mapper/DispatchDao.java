package net.chensee.platform.erp.action.dispatch.mapper;

import net.chensee.platform.erp.action.dispatch.po.DispatchSheetPo;
import net.chensee.platform.erp.action.dispatch.vo.DispatchSheetVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author : shibo
 * @date : 2019/5/17 16:07
 */
@Repository
public interface DispatchDao {

    /**
     * 获取所有派工单
     * @return
     */
    List<DispatchSheetVo> getAllDispatchSheet(@Param("pageStart") Integer pageStart,
                                              @Param("pageSize") Integer pageSize,
                                              @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 添加派工单
     * @param dispatchSheetPo
     * @return
     */
    void addDispatchSheet(DispatchSheetPo dispatchSheetPo);

    /**
     * 修改派工单
     * @param dispatchSheetPo
     * @return
     */
    void updateDispatchSheet(DispatchSheetPo dispatchSheetPo);

    /**
     * 删除派工单
     * @param id
     * @return
     */
    void deleteDispatchSheet(Long id);

    /**
     * 根据客户名称和编号获取派工单
     * @param customerName
     * @param customerNo
     * @return
     */
    List<DispatchSheetVo> getDispatchSheetByCustomers(String customerName, String customerNo, Integer pageStart, Integer pageSize);

    /**
     * 根据派工单获取ID
     * @param id
     * @return
     */
    List<DispatchSheetVo> getById(Long id);

    /**
     * 获取数据条数
     * @param customerName
     * @param customerNo
     * @return
     */
    Integer getCount(String customerName, String customerNo, @Param("currentFolders") Set<Long> currentFolders);
}
