package net.chensee.platform.erp.action.business.mapper;

import net.chensee.platform.erp.action.business.po.BusinessRolePo;
import net.chensee.platform.erp.action.business.vo.BusinessRoleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRolesDao {

    /**
     * 获取业务角色信息
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<BusinessRoleVo> getBusinessRoles(@Param("pageStart") Integer pageStart,
                                          @Param("pageSize") Integer pageSize);

    /**
     * 根据名字获取业务角色的关联信息
     * @param pageStart
     * @param pageSize
     * @param name
     * @return
     */
    List<BusinessRoleVo> getRolesByName(@Param("pageStart") Integer pageStart,
                                        @Param("pageSize") Integer pageSize,
                                        @Param("name") String name);

    /**
     * 获取业务信息条数
     * @param name
     * @return
     */
    Integer getCountBusRoles(@Param("name") String name);

    /**
     * 根据请求路径获取业务角色的关联信息
     * @param path
     * @return
     */
    List<BusinessRoleVo> getRolesByPath(@Param("path") String path);


    /**
     * 添加业务角色的关联信息
     * @param businessRolePo
     * @return
     */
    void addBusinessRole(BusinessRolePo businessRolePo);

    /**
     * 修改业务角色的信息
     * @param businessRolePo
     * @return
     */
    void updateBusinessRole(BusinessRolePo businessRolePo);

    /**
     * 删除业务角色信息
     * @param id
     * @return
     */
    void deleteBusinessRole(@Param("id") Long id);

    /**
     * 添加业务角色的关联信息
     * @param id
     * @param roleId
     * @return
     */
    void allotBusinessRole(@Param("id") Long id, @Param("roleId") Long roleId);

    /**
     * 添加业务角色的关联信息
     * @param businessId
     * @param roleIds
     * @return
     */
    void allotBusinessRole(@Param("businessId") Long businessId,
                           @Param("roleIds") List<Long> roleIds);


    /**
     * 根据业务ID添加角色关联信息
     * @param id
     * @param roleId
     * @return
     */
    void addBusRoleRelation(@Param("id") Long id, @Param("roleId") Long roleId);

    /**
     * 根据业务ID删除所有角色关联信息
     * @param businessId
     * @return
     */
    void deleteBusRoleInfos(@Param("businessId") Long businessId);

    /**
     * 根据业务ID删除角色关联信息
     * @param businessId
     * @param roleId
     * @return
     */
    void delBusRoleRelation(@Param("businessId") Long businessId, @Param("roleId") Long roleId);

    /**
     * 根据业务ID获取角色关联信息
     * @param busRoleId
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<BusinessRoleVo> getBusRoleRelation(@Param("busRoleId") Long busRoleId,
                                            @Param("pageStart") Integer pageStart,
                                            @Param("pageSize") Integer pageSize);

    /**
     * 根据业务ID获取角色关联信息数量
     * @param busRoleId
     * @return
     */
    Integer getCountBusRoleRelation(@Param("busRoleId") Long busRoleId);
}
