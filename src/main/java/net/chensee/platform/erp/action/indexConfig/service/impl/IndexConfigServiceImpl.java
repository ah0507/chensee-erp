package net.chensee.platform.erp.action.indexConfig.service.impl;

import net.chensee.base.action.dept.vo.DeptVo;
import net.chensee.base.action.role.vo.RoleVo;
import net.chensee.base.action.user.business.UserBus;
import net.chensee.base.common.ObjectResponse;
import net.chensee.base.utils.UserUtil;
import net.chensee.platform.erp.action.indexConfig.mapper.IndexConfigDao;
import net.chensee.platform.erp.action.indexConfig.po.IndexConfigPo;
import net.chensee.platform.erp.action.indexConfig.po.IndexInfoPo;
import net.chensee.platform.erp.action.indexConfig.po.UserToIndexPo;
import net.chensee.platform.erp.action.indexConfig.service.IndexConfigService;
import net.chensee.platform.erp.action.indexConfig.vo.IndexConfigVo;
import net.chensee.platform.erp.action.indexConfig.vo.IndexResourceConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author ah
 * @title: IndexService
 * @date 2019/12/5 11:29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class IndexConfigServiceImpl
        implements IndexConfigService {

    @Autowired
    private IndexConfigDao indexConfigDao;

    @Autowired
    private UserBus userBus;

    @Override
    public ObjectResponse saveIndexConfig(IndexConfigVo indexConfigVo) {
        IndexInfoPo indexInfoPo = new IndexInfoPo();
        BeanUtils.copyProperties(indexConfigVo, indexInfoPo);
        indexInfoPo.setCreateTime(new Date());
        indexInfoPo.setCreateBy(UserUtil.getCurrentUser().getId());
        indexInfoPo.setStatus(1);
        this.saveIndexInfoPo(indexInfoPo);

        List<IndexResourceConfig> indexResourceConfigs = indexConfigVo.getIndexResourceConfigs();
        IndexConfigPo indexConfigPo;
        for (IndexResourceConfig indexResourceConfig : indexResourceConfigs) {
            indexConfigPo = new IndexConfigPo();
            indexConfigPo.setIndexId(indexInfoPo.getId());
            BeanUtils.copyProperties(indexResourceConfig, indexConfigPo);
            indexInfoPo.setCreateTime(new Date());
            indexInfoPo.setCreateBy(UserUtil.getCurrentUser().getId());
            indexConfigPo.setStatus(1);
            this.saveIndexConfigPo(indexConfigPo);
        }
        return ObjectResponse.ok();
    }

    @Override
    public void saveIndexInfoPo(IndexInfoPo indexInfoPo) {
        indexConfigDao.saveIndexInfoPo(indexInfoPo);
    }

    @Override
    public void saveIndexConfigPo(IndexConfigPo indexConfigPo) {
        indexConfigDao.saveIndexConfigPo(indexConfigPo);
    }

    @Override
    public ObjectResponse delIndexConfig(Long indexId) {
        indexConfigDao.delIndexInfo(indexId);
        indexConfigDao.delIndexConfigPo(indexId);
        return ObjectResponse.ok();
    }

    @Override
    public ObjectResponse getIndexInfoConfigPage(Integer pageSize, Integer pageNumber) {
        List<IndexInfoPo> indexInfoPos = indexConfigDao.getIndexInfoPage((pageNumber-1)*pageSize, pageSize);
        List<IndexConfigVo> indexConfigVos = new ArrayList<>();
        for (IndexInfoPo indexInfoPo : indexInfoPos) {
            indexConfigVos.add(this.getIndexConfigVo(indexInfoPo));
        }
        Integer total = indexConfigDao.getIndexInfoCount();
        Map map = new HashMap<>();
        map.put("data", indexConfigVos);
        map.put("total", total);
        return ObjectResponse.ok(map);
    }

    @Override
    public ObjectResponse saveUserIndex(Long indexId) {
        UserToIndexPo userToIndexPo = this.getUserIndex();
        if (userToIndexPo == null) {
            userToIndexPo = new UserToIndexPo();
            userToIndexPo.setUserId(UserUtil.getCurrentUser().getId());
            userToIndexPo.setIndexId(indexId);
            indexConfigDao.insertUserToIndexPo(userToIndexPo);
        }else{
            userToIndexPo.setIndexId(indexId);
            indexConfigDao.updateUserToIndexPo(userToIndexPo);
        }
        return ObjectResponse.ok();
    }

    private UserToIndexPo getUserIndex() {
        Long userId = UserUtil.getCurrentUser().getId();
        return indexConfigDao.getUserIndex(userId);
    }

    @Override
    public ObjectResponse getUserIndexConfig() {
        UserToIndexPo userToIndexPo = this.getUserIndex();
        IndexInfoPo indexInfoPo;
        if (userToIndexPo != null) {
            Long indexId = userToIndexPo.getIndexId();
            indexInfoPo = indexConfigDao.getIndexInfoPoByIndexId(indexId);
        }else{
            // 通过用户ID,用户所在角色,用户所在部门获得最近的一条首页信息
            Set<Long> roleIds = this.getRoleIds();
            Set<Long> deptIds = this.getDeptIds();
            indexInfoPo = indexConfigDao.getUserIndexConfig(UserUtil.getCurrentUser().getId(), roleIds, deptIds);
        }
        if (indexInfoPo == null) {
            return ObjectResponse.fail();
        }
        IndexConfigVo indexConfigVo = this.getIndexConfigVo(indexInfoPo);
        return ObjectResponse.ok(indexConfigVo);
    }

    private Set<Long> getDeptIds() {
        Long userId = UserUtil.getCurrentUser().getId();
        List<DeptVo> deptVos = userBus.getDeptsByUserId(userId);
        Set<Long> deptIds = new HashSet<>();
        for (DeptVo deptVo : deptVos) {
            deptIds.add(deptVo.getId());
        }
        return deptIds;
    }

    private Set<Long> getRoleIds() {
        Long userId = UserUtil.getCurrentUser().getId();
        List<RoleVo> roleVos = userBus.getRolesByUserId(userId);
        Set<Long> roleIds = new HashSet<>();
        for (RoleVo roleVo : roleVos) {
            roleIds.add(roleVo.getId());
        }
        return roleIds;
    }

    @Override
    public ObjectResponse getIndexInfosByUser() {
        List<IndexInfoPo> indexInfoPos = indexConfigDao.getIndexInfosByUser(UserUtil.getCurrentUser().getId());
        return ObjectResponse.ok(indexInfoPos);
    }

    @Override
    public ObjectResponse getIndexInfosByRole() {
        Set<Long> roleIds = this.getRoleIds();
        List<IndexInfoPo> indexInfoPos = indexConfigDao.getIndexInfosByRole(roleIds);
        return ObjectResponse.ok(indexInfoPos);
    }

    @Override
    public ObjectResponse getIndexInfosByDept() {
        Set<Long> deptIds = this.getDeptIds();
        List<IndexInfoPo> indexInfoPos = indexConfigDao.getIndexInfosByDept(deptIds);
        return ObjectResponse.ok(indexInfoPos);
    }

    private IndexConfigVo getIndexConfigVo(IndexInfoPo indexInfoPo) {
        List<IndexConfigPo> indexConfigPos = this.getIndexConfigPosByIndexId(indexInfoPo.getId());
        IndexConfigVo indexConfigVo = new IndexConfigVo();
        BeanUtils.copyProperties(indexInfoPo, indexConfigVo);
        List<IndexResourceConfig> indexResourceConfigs = new ArrayList<>();
        IndexResourceConfig indexResourceConfig;
        for (IndexConfigPo indexConfigPo : indexConfigPos) {
            indexResourceConfig = new IndexResourceConfig();
            BeanUtils.copyProperties(indexConfigPo, indexResourceConfig);
            indexResourceConfigs.add(indexResourceConfig);
        }
        indexConfigVo.setIndexResourceConfigs(indexResourceConfigs);
        return indexConfigVo;
    }

    private List<IndexConfigPo> getIndexConfigPosByIndexId(Long indexId) {
        return indexConfigDao.getIndexConfigPosByIndexId(indexId);
    }
}
