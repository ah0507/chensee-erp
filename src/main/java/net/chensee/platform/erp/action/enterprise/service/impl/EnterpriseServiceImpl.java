package net.chensee.platform.erp.action.enterprise.service.impl;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.base.common.po.BaseInfoPo;
import net.chensee.base.utils.CreateAndUpdateInfoUtil;
import net.chensee.base.utils.ResFolderUtils;
import net.chensee.platform.erp.action.enterprise.mapper.EnterpriseDao;
import net.chensee.platform.erp.action.enterprise.po.EnterprisePo;
import net.chensee.platform.erp.action.enterprise.service.EnterpriseService;
import net.chensee.platform.erp.action.enterprise.vo.EnterpriseVo;
import net.chensee.platform.erp.action.queryConfig.po.QueryConfigPo;
import net.chensee.platform.erp.action.queryConfig.service.QueryConfigService;
import net.chensee.platform.erp.action.queryConfig.vo.QueryConfigParamVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author : shibo
 * @date : 2019/6/17 17:51
 */
@Service
@Transactional
public class EnterpriseServiceImpl implements EnterpriseService {
    private static final Logger logger = LoggerFactory.getLogger(EnterprisePo.class);

    @Resource
    private EnterpriseDao enterpriseDao;

    @Autowired
    private QueryConfigService queryConfigService;

    @Override
    public ObjectResponse addEnterprise(EnterprisePo enterprisePo) {
        try {
            enterprisePo.setStatus(BaseInfoPo.Status_Able);
            CreateAndUpdateInfoUtil.addCreateInfo(enterprisePo);
            CreateAndUpdateInfoUtil.addUpdateInfo(enterprisePo);
            enterpriseDao.addEnterprise(enterprisePo);
            return ObjectResponse.ok(enterprisePo.getId());
        } catch (Exception e) {
            logger.error("添加公司出现异常", e);
            return ObjectResponse.fail("添加公司出现异常");
        }
    }

    @Override
    public ObjectResponse getById(Long id) {
        try {
            return ObjectResponse.ok(enterpriseDao.getById(id).get(0));
        } catch (Exception e) {
            logger.error("根据ID查询公司出现异常", e);
            return ObjectResponse.fail("查询公司出现异常");
        }
    }

    @Override
    public BaseResponse updateEnterprise(EnterprisePo enterprisePo) {
        try {
            CreateAndUpdateInfoUtil.addUpdateInfo(enterprisePo);
            enterpriseDao.updateEnterprise(enterprisePo);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("修改公司出现异常", e);
            return BaseResponse.fail("修改公司出现异常");
        }
    }

    @Override
    public BaseResponse deleteEnterprise(Long id) {
        try {
            enterpriseDao.deleteEnterprise(id);
            return BaseResponse.ok();
        } catch (Exception e) {
            logger.error("删除公司出现异常", e);
            return BaseResponse.fail("删除公司出现异常");
        }
    }

    @Override
    public ObjectResponse getAllEnterprise(Integer pageSize, Integer pageNumber) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<EnterpriseVo> enterpriseVos = enterpriseDao.getAllEnterprise(pageSize * (pageNumber - 1), pageSize, currentFolder);
            Integer count = enterpriseDao.getCount(null, null, currentFolder);
            Map map = new HashMap<>();
            map.put("data", enterpriseVos);
            map.put("total", count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("获取所有公司发生异常",e);
            return ObjectResponse.fail("获取所有公司发生异常");
        }
    }

    @Override
    public ObjectResponse getByNameAndNo(String name, String number, Integer pageSize, Integer pageNumber) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<EnterpriseVo> enterpriseVos = enterpriseDao.getByNameAndNo(name, number, pageSize * (pageNumber - 1), pageSize);
            Integer count = enterpriseDao.getCount(name, number, currentFolder);
            Map map = new HashMap<>();
            map.put("data", enterpriseVos);
            map.put("total", count);
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("根据名称和编号查询公司发生异常",e);
            return ObjectResponse.fail("根据名称和编号查询公司发生异常");
        }
    }

    @Override
    public ObjectResponse getEnterpriseByConfig(List<QueryConfigParamVo> queryConfigParamVos, String classMethodName, Integer pageSize, Integer pageNumber) {
        try {
            Set<Long> currentFolder = ResFolderUtils.getCurrentFolder();
            List<QueryConfigPo> queryConfigPos = queryConfigService.getQueryConfigPos(queryConfigParamVos, classMethodName);
            Map map = new HashMap<>();
            if (queryConfigPos.size() == 1 && queryConfigPos.get(0).getQueryType() != 1) {
                // 如果高级查询中只有一组查询的话（查询类型为2checkbox，radio，模糊字段查询）查询某一字段字典的列表
                List<Object> list = enterpriseDao.getQueryFieldByConfig(queryConfigPos.get(0));
                map.put("data", list);
            }else{
                //根据所有高级查询条件查询整体数据
                List<EnterpriseVo> enterpriseVos = enterpriseDao.getEnterpriseByConfig(queryConfigPos, pageSize * (pageNumber - 1), pageSize);
                Integer count = enterpriseDao.getEnterpriseCount(queryConfigPos, currentFolder);
                map.put("data", enterpriseVos);
                map.put("total", count);
            }
            return ObjectResponse.ok(map);
        } catch (Exception e) {
            logger.error("根据高级配置查询公司发生异常",e);
            return ObjectResponse.fail("根据高级配置查询公司发生异常");
        }
    }
}
