package net.chensee.platform.erp.action.customer.mapper;

import net.chensee.platform.erp.action.customer.po.CustomerPo;
import net.chensee.platform.erp.action.customer.vo.CustomerVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author : shibo
 * @date : 2019/5/17 17:40
 */
@Repository
public interface CustomerDao {

    /**
     * 获取所有客户
     * @return
     */
    List<CustomerVo> getAllCustomers(@Param("pageStart") Integer pageStart,
                                     @Param("pageSize") Integer pageSize,
                                     @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 根据客户名称和编号查询客户
     * @param name
     * @param number
     * @return
     */
    List<CustomerVo> getByNameAndNo(@Param("name") String name,
                                    @Param("number") String number,
                                    @Param("pageStart") Integer pageStart,
                                    @Param("pageSize") Integer pageSize,
                                    @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 增加客户
     * @param customerPo
     */
    void addCustomer(CustomerPo customerPo);

    /**
     * 修改客户
     * @param customerPo
     */
    void updateCustomer(CustomerPo customerPo);

    /**
     * 删除客户
     * @param id
     */
    void deleteCustomer(Long id);

    /**
     * 获取数据条数
     * @param name
     * @param number
     * @return
     */
    Integer getCount(String name, String number, @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 根据ID获取客户信息
     * @param id
     * @param currentFolder
     * @return
     */
    List<CustomerVo> getById(@Param("id") Long id, @Param("currentFolders") Set<Long> currentFolder);
}
