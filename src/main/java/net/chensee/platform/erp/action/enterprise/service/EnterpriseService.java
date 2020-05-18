package net.chensee.platform.erp.action.enterprise.service;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.enterprise.po.EnterprisePo;
import net.chensee.platform.erp.action.queryConfig.vo.QueryConfigParamVo;

import java.util.List;

/**
 * @author : shibo
 * @date : 2019/6/17 17:50
 */
public interface EnterpriseService {

    /**
     * 添加公司信息
     * @param enterprisePo
     * @return
     */
    ObjectResponse addEnterprise(EnterprisePo enterprisePo);

    /**
     * 根据ID查询公司
     * @param id
     * @return
     */
    ObjectResponse getById(Long id);

    /**
     * 修改公司信息
     * @param enterprisePo
     * @return
     */
    BaseResponse updateEnterprise(EnterprisePo enterprisePo);

    /**
     * 删除公司
     * @param id
     * @return
     */
    BaseResponse deleteEnterprise(Long id);

    /**
     * 获取所有公司
     * @param pageSize
     * @param pageNumber
     * @return
     */
    ObjectResponse getAllEnterprise(Integer pageSize, Integer pageNumber);

    /**
     * 根据名称和编号查询公司
     * @param name
     * @param number
     * @param pageSize
     * @param pageNumber
     * @return
     */
    ObjectResponse getByNameAndNo(String name, String number, Integer pageSize, Integer pageNumber);

    /**
     * 根据高级配置查询公司
     * @param queryConfigParamVos
     * @param classMethodName
     * @param pageSize
     * @param pageNumber
     * @return
     */
    ObjectResponse getEnterpriseByConfig(List<QueryConfigParamVo> queryConfigParamVos, String classMethodName, Integer pageSize, Integer pageNumber);

}
