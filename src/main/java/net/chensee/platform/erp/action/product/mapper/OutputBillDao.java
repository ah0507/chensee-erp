package net.chensee.platform.erp.action.product.mapper;

import net.chensee.platform.erp.action.product.po.OutputBillPo;
import net.chensee.platform.erp.action.product.po.OutputLogPo;
import net.chensee.platform.erp.action.product.po.ProductInputPo;
import net.chensee.platform.erp.action.product.vo.OutputBillVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author : shibo
 * @date : 2019/6/18 15:32
 */
@Repository
public interface OutputBillDao {

    /**
     * 添加出库单信息
     * @param outputBillPo
     */
    void addOutputBill(OutputBillPo outputBillPo);

    /**
     * 删除出库单信息
     * @param id
     */
    void deleteOutputBill(Long id);

    /**
     * 获取所有出库单信息
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<OutputBillVo> getAllOutputBill(Integer pageStart, Integer pageSize);

    /**
     * 根据条件查询产品出库信息
     * @param startTime
     * @param endTime
     * @return
     */
    List<OutputBillVo> getByCondition(String contractNumber,
                                      String employeeDeptName,
                                      Date startTime,
                                      Date endTime,
                                      Integer pageStart,
                                      Integer pageSize);

    /**
     * 获取数据条数
     * @return
     */
    Integer getCount(String contractNumber, String deptName,
                     Date startTime, Date endTime);

    /**
     * 获得当前产品的总库存数量
     * @param productId
     * @return
     */
    Integer getCurrentProductNumber(@Param(value = "productId") Long productId);

    ProductInputPo getInputByAsc(@Param(value = "productId")Long productId,
                                 @Param(value = "count")int count);

    ProductInputPo getInputByDesc(@Param(value = "productId")Long productId,
                                  @Param(value = "count")int count);

    void updateInputInfo(ProductInputPo productInputPo);

    /**
     * 保存出库日志
     * @param outputLogPo
     */
    void addOutputLogPo(OutputLogPo outputLogPo);

    /**
     * 根据ID查询产品入库信息
     * @param id
     * @return
     */
    List<OutputBillVo> getProductOutputById(Long id);
}
