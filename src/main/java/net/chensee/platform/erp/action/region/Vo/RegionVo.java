package net.chensee.platform.erp.action.region.Vo;

import lombok.Data;
import net.chensee.platform.erp.action.region.Po.RegionPo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ah
 * @title: 省市区
 * @date 2019/11/15 10:39
 */
@Data
public class RegionVo extends RegionPo {

    private List<RegionVo> children = new ArrayList<>();
}
