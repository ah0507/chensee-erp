package net.chensee.platform.erp.action.queryConfig.service;

import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.queryConfig.po.QueryConfigPo;
import net.chensee.platform.erp.action.queryConfig.vo.QueryConfigParamVo;

import java.util.List;

/**
 * @author ah
 * @title: QueryConfigService
 * @date 2019/11/22 9:42
 */
public interface QueryConfigService {

    /**
     * 通过解析高级配置在sql中进行动态查询
     * @param queryConfigParamVos
     * @param classMethodName
     * @return
     */
    List<QueryConfigPo> getQueryConfigPos(List<QueryConfigParamVo> queryConfigParamVos, String classMethodName);

    ObjectResponse addQueryConfig(QueryConfigPo queryConfigPo, String classMethodName) throws Exception;

    ObjectResponse delQueryConfig(Long id) throws Exception;

    ObjectResponse updateQueryConfig(QueryConfigPo queryConfigPo) throws Exception;

    ObjectResponse getQueryConfigPage(Integer pageSize, Integer pageNumber);

    ObjectResponse getQueryConfigAllPage(Integer pageSize, Integer pageNumber);
}
