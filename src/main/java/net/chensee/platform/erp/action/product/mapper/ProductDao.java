package net.chensee.platform.erp.action.product.mapper;

import net.chensee.platform.erp.action.product.po.ProductPo;
import net.chensee.platform.erp.action.product.vo.OutputBillVo;
import net.chensee.platform.erp.action.product.vo.ProductVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author : shibo
 * @date : 2019/5/17 14:57
 */
@Repository
public interface ProductDao {

    /**
     * 获取所有产品
     * @return
     */
    List<ProductVo> getAllProducts(@Param("pageStart") Integer pageStart,
                                   @Param("pageSize") Integer pageSize,
                                   @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 根据ID获取产品
     * @param id
     * @return
     */
    List<ProductVo> getProductById(Long id, @Param("currentFolders") Set<Long> currentFolder);

    /**
     * 通常名称和类型查询商品
     * @param name
     * @param typeName
     * @return
     */
    List<ProductVo> getByNameAndType(@Param("name") String name,
                                     @Param("typeName") String typeName,
                                     @Param("pageStart") Integer pageStart,
                                     @Param("pageSize") Integer pageSize ,
                                     @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 增加产品
     * @param productPo
     */
    int addProduct(ProductPo productPo);

    /**
     * 修改产品
     * @param productPo
     */
    void updateProduct(ProductPo productPo);

    /**
     * 删除产品
     * @param id
     */
    void deleteProduct(Long id);

    /**
     * 获取数据条数
     * @param name
     * @param typeName
     * @return
     */
    Integer getCount(String name, String typeName, @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 根据ID获取产品库存数量
     * @param id
     * @return
     */
    Integer getStockAmountById(Long id);

    /**
     * 根据ID更新产品库存数量
     * @param id
     * @param amounts
     * @return
     */
    void updateStockAmount(Long id, Integer amounts);


}
