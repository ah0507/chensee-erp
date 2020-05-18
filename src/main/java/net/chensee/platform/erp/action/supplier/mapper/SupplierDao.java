package net.chensee.platform.erp.action.supplier.mapper;

import net.chensee.platform.erp.action.supplier.po.SupplierPo;
import net.chensee.platform.erp.action.supplier.vo.SupplierVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author : shibo
 * @date : 2019/6/17 17:34
 */
@Repository
public interface SupplierDao {

    /**
     * 添加供应商信息
     * @param supplierPo
     */
    void addSupplier(SupplierPo supplierPo);

    /**
     * 修改供应商信息
     * @param supplierPo
     */
    void updateSupplier(SupplierPo supplierPo);

    /**
     * 删除供应商
     * @param id
     */
    void deleteSupplier(Long id);

    /**
     * 获取所有供应商
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<SupplierVo> getAllSupplier(@Param("pageStart") Integer pageStart,
                                    @Param("pageSize") Integer pageSize,
                                    @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 根据公司名称和编号查询供应商
     * @param name
     * @param number
     * @return
     */
    List<SupplierVo> getByNameAndNo(String name, String number, Integer pageStart, Integer pageSize);

    /**
     * 获取数据条数
     * @param name
     * @param number
     * @return
     */
    Integer getCount(String name, String number, @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 根据ID获取供应商信息
     * @param supplierId
     * @param currentFolders
     * @return
     */
    List<SupplierVo> getById(@Param("supplierId") Long supplierId, @Param("currentFolders") Set<Long> currentFolders);
}
