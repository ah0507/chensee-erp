package net.chensee.platform.erp.action.business.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.business.po.BusinessRolePo;
import net.chensee.platform.erp.action.business.service.BusRoleService;
import net.chensee.platform.erp.action.business.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@Slf4j
public class BusinessController {

    @Autowired
    private BusService busService;

    @ApiOperation(value = "获取所有业务信息")
    @RequestMapping(value = "/business", method = RequestMethod.GET)
    public ObjectResponse getAllBusiness() {
        return busService.getAllBusiness();
    }
}
