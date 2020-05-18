package net.chensee.platform.erp.action.product.mapper;

import net.chensee.platform.erp.action.product.po.ProductTypeUnitPo;
import net.chensee.platform.erp.action.product.vo.ProductTypeUnitVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author : shibo
 * @date : 2019/5/17 14:57
 */
@Repository
public interface ProductTypeUnitDao {

    /**
     * 分页获取所有产品类型单位
     * @return
     */
    List<ProductTypeUnitVo> getAllProductTypeUnitsPagination(@Param("pageStart") Integer pageStart,
                                                             @Param("pageSize") Integer pageSize,
                                                             @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 获取所有产品类型单位
     * @return
     */
    List<ProductTypeUnitVo> getAllProductTypeUnits(@Param("currentFolders") Set<Long> currentFolders);

    /**
     * 通过名称和类型查询单位
     * @param name
     * @param typeName
     * @param pageStart
     * @param pageSize
     * @param currentFolders
     * @return
     */
    List<ProductTypeUnitVo> getByNameAndType(@Param("name") String name,
                                             @Param("typeName") String typeName,
                                             @Param("pageStart") Integer pageStart,
                                             @Param("pageSize") Integer pageSize,
                                             @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 根据ID查询单位
     * @param id
     * @param currentFolders
     * @return
     */
    List<ProductTypeUnitVo> getProductTypeUnitById(Long id, @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 获取数据条数
     * @param name
     * @param typeName
     * @return
     */
    Integer getCount(@Param("name") String name,
                     @Param("typeName") String typeName,
                     @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 增加产品类型单位
     * @param productTypeUnitPo
     */
    void addProductTypeUnit(ProductTypeUnitPo productTypeUnitPo);

    /**
     * 修改产品类型单位
     * @param productTypeUnitPo
     */
    void updateProductTypeUnit(ProductTypeUnitPo productTypeUnitPo);

    /**
     * 删除产品类型单位
     * @param id
     */
    void deleteProductTypeUnit(Long id);


}
