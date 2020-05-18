package net.chensee.platform.erp.action.business.service;

import net.chensee.base.common.BaseResponse;
import net.chensee.base.common.ObjectResponse;
import net.chensee.platform.erp.action.business.po.BusinessRolePo;

import java.util.List;

public interface BusRoleService {

    /**
     * 获取业务关联的角色
     * @return
     */
    ObjectResponse getBusinessRoles(Integer pageNumber, Integer pageSize);

    /**
     * 根据名字获取业务关联的角色
     * @param name
     * @return
     */
    ObjectResponse getRolesByName(Integer pageNumber, Integer pageSize, String name);

    /**
     * 根据请求路径获取业务关联的角色
     * @param path
     * @return
     */
    ObjectResponse getRolesByPath(String path);

    /**
     * 添加业务关联角色
     * @param businessRolePo
     * @return
     */
    BaseResponse addBusinessRole(BusinessRolePo businessRolePo);

    /**
     * 修改业务关联角色
     * @param businessRolePo
     * @return
     */
    BaseResponse updateBusinessRole(BusinessRolePo businessRolePo);

    /**
     * 删除业务关联角色
     * @param id
     * @return
     */
    BaseResponse deleteBusinessRole(Long id);

    /**
     * 分配管理业务角色关联信息
     * @param businessId
     * @param roleIds
     * @return
     */
    BaseResponse allotBusinessRole(Long businessId, String roleIds);

    /**
     * 根据业务ID添加角色关联信息
     * @param busRoleId
     * @param roleId
     * @return
     */
    BaseResponse addBusRoleRelation(Long busRoleId, Long roleId);

    /**
     * 根据业务ID删除角色关联信息
     * @param businessId
     * @param roleId
     * @return
     */
    BaseResponse delBusRoleRelation(Long businessId, Long roleId);

    /**
     * 根据业务ID查询角色关联信息
     * @param busRoleId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    ObjectResponse getBusRoleRelation(Long busRoleId, Integer pageNumber, Integer pageSize);

}
