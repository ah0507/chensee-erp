package net.chensee.platform.erp.action.enterprise.mapper;

import net.chensee.platform.erp.action.enterprise.po.EnterprisePo;
import net.chensee.platform.erp.action.enterprise.vo.EnterpriseVo;
import net.chensee.platform.erp.action.queryConfig.po.QueryConfigPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author : shibo
 * @date : 2019/6/17 17:34
 */
@Repository
public interface EnterpriseDao {

    /**
     * 添加公司信息
     * @param enterprisePo
     */
    void addEnterprise(EnterprisePo enterprisePo);

    /**
     * 修改公司信息
     * @param enterprisePo
     */
    void updateEnterprise(EnterprisePo enterprisePo);

    /**
     * 删除公司
     * @param id
     */
    void deleteEnterprise(Long id);

    /**
     * 获取所有公司
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<EnterpriseVo> getAllEnterprise(@Param("pageStart") Integer pageStart,
                                        @Param("pageSize") Integer pageSize,
                                        @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 根据ID查询公司
     * @param id
     * @return
     */
    List<EnterpriseVo> getById(@Param("id") Long id);

    /**
     * 根据名称和编号查询公司
     * @param name
     * @param number
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<EnterpriseVo> getByNameAndNo(String name, String number, Integer pageStart, Integer pageSize);

    /**
     * 获取数据条数
     * @return
     */
    Integer getCount(String name, String number, @Param("currentFolders") Set<Long> currentFolders);

    /**
     * 通过高级配置查询公司
     * @param queryConfigPos
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<EnterpriseVo> getEnterpriseByConfig(@Param("queryConfigPos") List<QueryConfigPo> queryConfigPos,
                                             @Param("pageStart") Integer pageStart,
                                             @Param("pageSize") Integer pageSize);

    /**
     * 获取高级配置查询公司的数量
     * @return
     */
    Integer getEnterpriseCount(@Param("queryConfigPos") List<QueryConfigPo> queryConfigPos,
                               @Param("currentFolders") Set<Long> currentFolder);

    List<Object> getQueryFieldByConfig(QueryConfigPo queryConfigPo);

}
