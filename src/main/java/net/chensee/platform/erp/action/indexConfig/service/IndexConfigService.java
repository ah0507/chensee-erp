package net.chensee.platform.erp.action.indexConfig.service;

import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.indexConfig.po.IndexConfigPo;
import net.chensee.platform.erp.action.indexConfig.po.IndexInfoPo;
import net.chensee.platform.erp.action.indexConfig.vo.IndexConfigVo;

/**
 * @author ah
 * @title: IndexService
 * @date 2019/12/5 11:29
 */
public interface IndexConfigService {

    /**
     * 添加首页信息及配置
     * @param indexConfigVo
     * @return
     */
    ObjectResponse saveIndexConfig(IndexConfigVo indexConfigVo) throws Exception;

    /**
     * 保存用户对应的首页
     * @param indexId
     * @return
     */
    ObjectResponse saveUserIndex(Long indexId) throws Exception;

    /**
     * 保存首页信息
     * @param indexInfoPo
     */
    void saveIndexInfoPo(IndexInfoPo indexInfoPo) throws Exception;

    /**
     * 保存首页配置
     * @param indexConfigPo
     */
    void saveIndexConfigPo(IndexConfigPo indexConfigPo) throws Exception;

    /**
     * 获得用户对应的首页及配置
     * @return
     */
    ObjectResponse getUserIndexConfig();

    /**
     * 获取用户个人的所有首页信息
     * @return
     */
    ObjectResponse getIndexInfosByUser();

    /**
     * 获取用户对应角色的所有首页信息
     * @return
     */
    ObjectResponse getIndexInfosByRole();

    /**
     * 获取用户对应部门的所有首页信息
     * @return
     */
    ObjectResponse getIndexInfosByDept();


    /**
     * 删除首页的信息及配置
     * @param indexId
     * @return
     */
    ObjectResponse delIndexConfig(Long indexId) throws Exception;

    /**
     * 分页获得用户对应的首页及配置
     * @param pageSize
     * @param pageNumber
     * @return
     */
    ObjectResponse getIndexInfoConfigPage(Integer pageSize, Integer pageNumber);
}
