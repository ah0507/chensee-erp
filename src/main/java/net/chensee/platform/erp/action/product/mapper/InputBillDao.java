package net.chensee.platform.erp.action.product.mapper;

import net.chensee.platform.erp.action.product.po.InputBillPo;
import net.chensee.platform.erp.action.product.vo.InputBillVo;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author : shibo
 * @date : 2019/6/18 15:32
 */
@Repository
public interface InputBillDao {

    /**
     * 添加入库单信息
     * @param inputBillPo
     */
    void addInputBill(InputBillPo inputBillPo);

    /**
     * 删除入库单信息
     * @param id
     */
    void deleteInputBill(Long id);

    /**
     * 获取所有入库单信息
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<InputBillVo> getAllInputBill(Integer pageStart, Integer pageSize);

    /**
     * 根据条件查询产品入库信息
     * @param startTime
     * @param endTime
     * @return
     */
    List<InputBillVo> getByCondition(String contractNumber,
                                     String purchaserDeptName,
                                     Date startTime,
                                     Date endTime,
                                     Integer pageStart,
                                     Integer pageSize);

    /**
     * 获取数据条数
     * @return
     */
    Integer getCount(String contractNumber,
                     String purchaserDeptName,
                     Date startTime,
                     Date endTime);

    /**
     * 根据ID查询产品入库信息
     * @param id
     * @return
     */
    List<InputBillVo> getProductInputById(Long id);
}
