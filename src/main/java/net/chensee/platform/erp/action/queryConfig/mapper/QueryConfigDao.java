package net.chensee.platform.erp.action.queryConfig.mapper;

import net.chensee.platform.erp.action.queryConfig.po.QueryConfigPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ah
 * @title: QueryConfigDao
 * @date 2019/11/22 14:31
 */
@Repository
public interface QueryConfigDao {

    QueryConfigPo getQueryConfigPos(@Param(value = "customKey") String key,
                                    @Param(value = "classMethodName") String classMethodName);

    Integer isExistQueryConfigPo(QueryConfigPo queryConfigPo);

    List<QueryConfigPo> getQueryConfigPage(@Param(value = "pageSize") Integer pageSize,
                                           @Param(value = "pageNumber") Integer pageNumber);

    void addQueryConfig(QueryConfigPo queryConfigPo);

    void delQueryConfig(@Param(value = "id") Long id);

    void updateQueryConfig(QueryConfigPo queryConfigPo);

    Integer getQueryConfigCount();

    List<QueryConfigPo> getQueryConfigAllPage(@Param(value = "pageSize") Integer pageSize,
                                              @Param(value = "pageNumber") Integer pageNumber);

    Integer getQueryConfigAllCount();
}
