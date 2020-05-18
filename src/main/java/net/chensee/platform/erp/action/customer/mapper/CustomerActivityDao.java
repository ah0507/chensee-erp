package net.chensee.platform.erp.action.customer.mapper;

import net.chensee.platform.erp.action.customer.po.CustomerActivityPo;
import net.chensee.platform.erp.action.customer.vo.CustomerActivityVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author : shibo
 * @date : 2019/5/17 17:40
 */
@Repository
public interface CustomerActivityDao {

    /**
     * 增加客户活动
     * @param customerActivityPo
     */
    void addCustomerActivity(CustomerActivityPo customerActivityPo);

    /**
     * 修改客户活动
     * @param customerActivityPo
     */
    void updateCustomerActivity(CustomerActivityPo customerActivityPo);

    /**
     * 删除客户活动
     * @param id
     */
    void deleteCustomerActivity(Long id);

    /**
     * 获取所有的客户活动
     * @return
     */
    List<CustomerActivityVo> getAllCustomerActivity(Integer pageStart, Integer pageSize, @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 根据活动主题和编号查找活动
     * @param theme
     * @param number
     * @return
     */
    List<CustomerActivityVo> getByThemeAndNo(String theme, String number, Integer pageStart, Integer pageSize);

    /**
     * 获取数据条数
     * @param theme
     * @param number
     * @return
     */
    Integer getCount(String theme, String number, @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 根据ID获取客户活动
     * @param id
     * @param currentFolders
     * @return
     */
    List<CustomerActivityVo> getCustomerActivityById(Long id, Set<Long> currentFolders);
}
