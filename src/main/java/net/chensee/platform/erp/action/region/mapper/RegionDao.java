package net.chensee.platform.erp.action.region.mapper;

import net.chensee.platform.erp.action.region.Po.RegionPo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ah
 * @title: RegionDao
 * @date 2019/11/15 10:50
 */
@Repository
public interface RegionDao {

    List<RegionPo> getRegion();
}
