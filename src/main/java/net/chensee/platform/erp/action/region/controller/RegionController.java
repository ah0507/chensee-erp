package net.chensee.platform.erp.action.region.controller;

import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.region.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ah
 * @title: 省市区
 * @date 2019/11/15 10:23
 */
@RestController
@RequestMapping(value = "/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping(value = "")
    public ObjectResponse getRegion(){
        return regionService.getRegionTree();
    }

}
