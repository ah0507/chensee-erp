package net.chensee.platform.erp.action.queryConfig.service.impl;

import net.chensee.base.common.ObjectResponse;
import net.chensee.base.utils.UserUtil;
import net.chensee.platform.erp.action.queryConfig.common.TypeConvertUtil;
import net.chensee.platform.erp.action.queryConfig.mapper.QueryConfigDao;
import net.chensee.platform.erp.action.queryConfig.po.QueryConfigPo;
import net.chensee.platform.erp.action.queryConfig.service.QueryConfigService;
import net.chensee.platform.erp.action.queryConfig.vo.QueryConfigParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author ah
 * @title: QueryConfigService
 * @date 2019/11/22 9:42
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class QueryConfigServiceImpl implements QueryConfigService {

    @Autowired
    private QueryConfigDao queryConfigDao;

    @Override
    public List<QueryConfigPo> getQueryConfigPos(List<QueryConfigParamVo> queryConfigParamVos, String classMethodName) {
        List<QueryConfigPo> queryConfigPoList = new ArrayList<>();
        for (QueryConfigParamVo queryConfigParamVo : queryConfigParamVos) {
            if (queryConfigParamVo == null) {
                continue;
            }
            QueryConfigPo queryConfigPo = queryConfigDao.getQueryConfigPos(queryConfigParamVo.getKey(), classMethodName);
            if (queryConfigParamVo.getQueryType() == 2) {
                queryConfigPo.setOpr(queryConfigParamVo.getOpr());
                queryConfigPo.setQueryType(queryConfigParamVo.getQueryType());
                queryConfigPoList.add(queryConfigPo);
                break;
            }
            //类型转换
            Object value = TypeConvertUtil.convertType(queryConfigPo.getType(), queryConfigParamVo.getOpr(),queryConfigParamVo.getValue());
            //特殊查询处理
            Object newVal = TypeConvertUtil.convertLikeQueryValue(queryConfigParamVo.getOpr(),value);
            if (newVal != null) {
                queryConfigPo.setOpr(queryConfigParamVo.getOpr());
                queryConfigPo.setValue(newVal);
                queryConfigPo.setQueryType(queryConfigParamVo.getQueryType());
                queryConfigPoList.add(queryConfigPo);
            }
        }
        return queryConfigPoList;
    }

    @Override
    public ObjectResponse addQueryConfig(QueryConfigPo queryConfigPo, String classMethodName) throws Exception{
        Integer count = queryConfigDao.isExistQueryConfigPo(queryConfigPo);
        if (count == 0) {
            queryConfigPo.setPath(classMethodName);
            queryConfigPo.setCreateTime(new Date());
            queryConfigPo.setCreateBy(UserUtil.getCurrentUser().getId());
            queryConfigDao.addQueryConfig(queryConfigPo);
        }
        return ObjectResponse.ok();
    }

    @Override
    public ObjectResponse delQueryConfig(Long id)  throws Exception{
        queryConfigDao.delQueryConfig(id);
        return ObjectResponse.ok();
    }

    @Override
    public ObjectResponse updateQueryConfig(QueryConfigPo queryConfigPo) throws Exception {
        queryConfigPo.setUpdateTime(new Date());
        queryConfigPo.setUpdateBy(UserUtil.getCurrentUser().getId());
        queryConfigDao.updateQueryConfig(queryConfigPo);
        return ObjectResponse.ok();
    }

    @Override
    public ObjectResponse getQueryConfigPage(Integer pageSize, Integer pageNumber) {
        List<QueryConfigPo> queryConfigPoList = queryConfigDao.getQueryConfigPage(pageSize * (pageNumber - 1), pageNumber);
        Integer count = queryConfigDao.getQueryConfigCount();
        Map resultMap = new HashMap<>();
        resultMap.put("data", queryConfigPoList);
        resultMap.put("total", count);
        return ObjectResponse.ok(resultMap);
    }

    @Override
    public ObjectResponse getQueryConfigAllPage(Integer pageSize, Integer pageNumber) {
        List<QueryConfigPo> queryConfigPoList = queryConfigDao.getQueryConfigAllPage(pageSize * (pageNumber - 1), pageNumber);
        Integer count = queryConfigDao.getQueryConfigAllCount();
        Map resultMap = new HashMap<>();
        resultMap.put("data", queryConfigPoList);
        resultMap.put("total", count);
        return ObjectResponse.ok(resultMap);
    }

}
