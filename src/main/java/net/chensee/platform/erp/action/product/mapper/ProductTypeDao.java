package net.chensee.platform.erp.action.product.mapper;

import io.swagger.models.auth.In;
import net.chensee.platform.erp.action.product.po.ProductTypeModelPo;
import net.chensee.platform.erp.action.product.po.ProductTypePo;
import net.chensee.platform.erp.action.product.vo.ProductTypeModelVo;
import net.chensee.platform.erp.action.product.vo.ProductTypeVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author : shibo
 * @date : 2019/5/17 14:57
 */
@Repository
public interface ProductTypeDao {

    /**
     * 获取所有产品类型
     * @return
     */
    List<ProductTypeVo> getAllProductTypes(@Param("pageStart") Integer pageStart,
                                           @Param("pageSize") Integer pageSize,
                                           @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 条件获取产品类型
     * @param name
     * @param parentName
     * @param unitName
     * @param pageStart
     * @param pageSize
     * @param currentFolder
     * @return
     */
    List<ProductTypeVo> getProductTypesByCondition(@Param("name") String name,
                                                   @Param("parentName") String parentName,
                                                   @Param("unitName") String unitName,
                                                   @Param("pageStart") Integer pageStart,
                                                   @Param("pageSize") Integer pageSize,
                                                   @Param("currentFolders") Set<Long> currentFolder);

    /**
     * 获取产品类型的数量
     * @param currentFolder
     * @return
     */
    Integer getCountProductTypes(@Param("name") String name,
                                 @Param("parentName") String parentName,
                                 @Param("unitName") String unitName,
                                 @Param("currentFolders") Set<Long> currentFolder);

    /**
     * 增加产品类型
     * @param productTypePo
     */
    void addProductType(ProductTypePo productTypePo);

    /**
     * 修改产品类型
     * @param productTypePo
     */
    void updateProductType(ProductTypePo productTypePo);

    /**
     * 删除产品类型
     * @param id
     */
    void deleteProductType(Long id);

    /**
     * 获得子分类ID
     * @param id
     * @return
     */
    List<Long> getChildrenIds(Long id);

    /**
     * 根据ID获取产品类型
     * @param id
     * @return
     */
    List<ProductTypeVo> getProductTypeById(@Param("typeId") Long id,
                                           @Param("currentFolders") Set<Long> currentFolder);

    /**
     * 根据类型ID添加产品型号
     * @param productTypeModelPo
     * @return
     */
    void addProductTypeModel(ProductTypeModelPo productTypeModelPo);

    /**
     * 根据类型ID获取产品型号
     * @param id
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<ProductTypeModelVo> getProductTypeModel(@Param("id") Long id,
                                                 @Param("pageStart") Integer pageStart,
                                                 @Param("pageSize") Integer pageSize);

    /**
     * 根据类型ID获取产品型号
     * @param id
     * @return
     */
    Integer getCountModelByTypeId(Long id);

    /**
     * 根据ID删除产品型号
     * @param id
     * @return
     */
    void deleteProductTypeModel(@Param("id") Long id);


}
