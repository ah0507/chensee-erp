package net.chensee.platform.erp.action.business.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.vo.IdVo;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessRoleVo extends IdVo {

    /**业务名称*/
    private String businessName;
    /**业务CODE*/
    private String businessCode;
    /**业务路径*/
    private String businessPath;
    /**角色名称*/
    private Long roleId;
    private Set<Long> roleIdSet;
    private String roleName;
    private Set<String> roleNameSet;
    /**是否为内置业务*/
    private Integer isBuiltIn;

}
