package net.chensee.platform.erp.action.business.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.business.po.BusinessRolePo;
import net.chensee.platform.erp.action.business.service.BusRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@Slf4j
public class BusinessRoleController {

    @Autowired
    private BusRoleService busRoleService;

    //业务关联角色
    @ApiOperation(value = "获取业务关联的角色")
    @RequestMapping(value = "/busRoles", method = RequestMethod.GET)
    public ObjectResponse getBusinessRoles(@RequestParam(defaultValue = "1") Integer pageNumber,
                                           @RequestParam(defaultValue = "16") Integer pageSize) {
        return busRoleService.getBusinessRoles(pageNumber,pageSize);
    }

    @ApiOperation(value = "根据名字获取业务关联的角色")
    @RequestMapping(value = "/busRoles/name", method = RequestMethod.GET)
    public ObjectResponse getRolesByName(@RequestParam(defaultValue = "1") Integer pageNumber,
                                         @RequestParam(defaultValue = "16") Integer pageSize,
                                         @RequestParam(required = false) String name) {
        return busRoleService.getRolesByName(pageNumber, pageSize, name);
    }

    @ApiOperation(value = "根据请求路径获取业务关联的角色")
    @RequestMapping(value = "/busRoles/path", method = RequestMethod.GET)
    public ObjectResponse getRolesByPath(@RequestParam String path) {
        return busRoleService.getRolesByPath(path);
    }

    @ApiOperation(value = "添加业务关联角色")
    @RequestMapping(value = "/busRoles", method = RequestMethod.POST)
    public BaseResponse addBusinessRole(@RequestBody BusinessRolePo businessRolePo) {
        return busRoleService.addBusinessRole(businessRolePo);
    }

    @ApiOperation(value = "修改业务关联角色")
    @RequestMapping(value = "/busRoles", method = RequestMethod.PUT)
    public BaseResponse updateBusinessRole(@RequestBody BusinessRolePo businessRolePo) {
        return busRoleService.updateBusinessRole(businessRolePo);
    }

    @ApiOperation(value = "删除业务关联角色")
    @RequestMapping(value = "/busRoles/{id}", method = RequestMethod.DELETE)
    public BaseResponse deleteBusinessRole(@PathVariable(value = "id") Long id) {
        return busRoleService.deleteBusinessRole(id);
    }

    @ApiOperation(value = "根据业务ID添加角色关联信息")
    @RequestMapping(value = "/busRoles/relation/{busRoleId}", method = RequestMethod.POST)
    public BaseResponse addBusRoleRelation(@PathVariable(value = "busRoleId") Long busRoleId,
                                           @RequestParam(value = "roleId") Long roleId) {
        return busRoleService.addBusRoleRelation(busRoleId,roleId);
    }

    @ApiOperation(value = "根据业务ID删除角色关联信息")
    @RequestMapping(value = "/busRoles/relation/{busRoleId}", method = RequestMethod.DELETE)
    public BaseResponse delBusRoleRelation(@PathVariable(value = "busRoleId") Long busRoleId,
                                           @RequestParam(value = "roleId") Long roleId) {
        return busRoleService.delBusRoleRelation(busRoleId,roleId);
    }

    @ApiOperation(value = "根据业务ID获取角色关联信息")
    @RequestMapping(value = "/busRoles/relation/{busRoleId}", method = RequestMethod.GET)
    public ObjectResponse getBusRoleRelation(@PathVariable(value = "busRoleId") Long busRoleId,
                                             @RequestParam(defaultValue = "1") Integer pageNumber,
                                             @RequestParam(defaultValue = "16") Integer pageSize) {
        return busRoleService.getBusRoleRelation(busRoleId,pageNumber,pageSize);
    }



    //
    @ApiOperation(value = "分配管理业务角色关联信息")
    @RequestMapping(value = "/busRoles/relation", method = RequestMethod.POST)
    public BaseResponse allotBusinessRole(@RequestParam(value = "businessId") Long businessId,
                                          @RequestParam(value = "roleIds") String roleIds) {
        return busRoleService.allotBusinessRole(businessId,roleIds);
    }
}
