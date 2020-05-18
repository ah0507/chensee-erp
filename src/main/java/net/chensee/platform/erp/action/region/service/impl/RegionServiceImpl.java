package net.chensee.platform.erp.action.region.service.impl;

import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.region.Po.RegionPo;
import net.chensee.platform.erp.action.region.Vo.RegionVo;
import net.chensee.platform.erp.action.region.mapper.RegionDao;
import net.chensee.platform.erp.action.region.service.RegionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ah
 * @title: RegionService
 * @date 2019/11/15 10:48
 */
@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionDao regionDao;

    @Override
    public ObjectResponse getRegionTree(){
        List<RegionPo> regionPoList = regionDao.getRegion();
        List<RegionVo> RegionVosTree = new ArrayList<>();
        List<RegionVo> regionVos = new ArrayList<>();
        for (RegionPo regionPo : regionPoList) {
            RegionVo regionVo = new RegionVo();
            BeanUtils.copyProperties(regionPo,regionVo);
            regionVos.add(regionVo);
            if (regionVo.getLevel() == 1) {
                RegionVosTree.add(regionVo);
            }
        }
        for (RegionVo regionVo : RegionVosTree) {
            this.handleTree(regionVo, regionVos);
        }
        return ObjectResponse.ok(RegionVosTree);
    }

    private void handleTree(RegionVo parentRegionVo, List<RegionVo> regionVos) {
        for (RegionVo regionVo : regionVos) {
            if (!regionVo.getPid().equals(parentRegionVo.getId())) {
                continue;
            }
            handleTree(regionVo,regionVos);
            parentRegionVo.getChildren().add(regionVo);
        }
    }
}
