package net.chensee.platform.erp.action.customer.mapper;

import net.chensee.platform.erp.action.customer.po.LinkmanPo;
import net.chensee.platform.erp.action.customer.vo.LinkmanVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author : shibo
 * @date : 2019/5/17 17:40
 */
@Repository
public interface LinkmanDao {

    /**
     * 增加联系人
     * @param customerLinkmanPo
     */
    void addLinkman(LinkmanPo customerLinkmanPo);

    /**
     * 修改联系人
     * @param customerLinkmanPo
     */
    void updateLinkman(LinkmanPo customerLinkmanPo);

    /**
     * 删除联系人
     * @param id
     */
    void deleteLinkman(Long id);

    /**
     * 获取所有的客户联系人
     * @return
     */
    List<LinkmanVo> getAllCustomerLinkman(Integer pageStart,
                                          Integer pageSize,
                                          @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 获取所有的供应商联系人
     * @return
     */
    List<LinkmanVo> getAllSupplierLinkman(Integer pageStart,
                                          Integer pageSize,
                                          @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 根据客户名称和联系人名称查找联系人
     * @return
     */
    List<LinkmanVo> getByCustomerNameAndName(String customerName,
                                             String name,
                                             Integer pageStart,
                                             Integer pageSize,
                                             @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 根据供应商名称和联系人名称查找联系人
     * @return
     */
    List<LinkmanVo> getBySupplierNameAndName(String supplierName,
                                             String name,
                                             Integer pageStart,
                                             Integer pageSize,
                                             @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 根据ID获取客户联系人
     * @param customerId
     * @param currentFolders
     * @return
     */
    List<LinkmanVo> getCustomerLinkmanById(@Param("customerId") Long customerId,
                                           @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 根据ID获取供应商联系人
     * @param supplierId
     * @param currentFolders
     * @return
     */
    List<LinkmanVo> getSupplierLinkmanById(@Param("supplierId") Long supplierId,
                                           @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 获取数据条数
     * @param customerName
     * @param name
     * @return
     */
    Integer getCustomerCount(String customerName,
                             String name,
                             @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 获取数据条数
     * @param supplierName
     * @param name
     * @return
     */
    Integer getSupplierCount(String supplierName,
                             String name,
                             @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 查找客户的联系人
     * @param customerId
     * @return
     */
    List<LinkmanVo> getByCustomer(@Param("customerId") Long customerId,
                                  Integer pageStart,
                                  Integer pageSize,
                                  @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 查找供应商的联系人
     * @param supplierId
     * @return
     */
    List<LinkmanVo> getBySupplier(@Param("supplierId") Long supplierId,
                                  Integer pageStart,
                                  Integer pageSize,
                                  @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 获取数据条数
     * @param customerId
     * @return
     */
    Integer getCountByCustomer(Long customerId,
                               @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 获取数据条数
     * @param supplierId
     * @return
     */
    Integer getCountBySupplier(Long supplierId,
                               @Param("currentFolders") Set<Long> currentFolders);
}
