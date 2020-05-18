package net.chensee.platform.erp.action.business.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.IdPo;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessRolePo extends IdPo {

    public static final Integer Status_Able = 0;
    public static final Integer Status_Disable = 1;
    public static final Integer IsBuiltIn = 1;
    public static final Integer IsNotBuiltIn = 0;

    /**业务名称*/
    private String businessName;
    /**业务CODE*/
    private String businessCode;
    /**业务路径*/
    private String businessPath;
    /**创建时间*/
    private Date createTime;
    /**创建人*/
    private Long createBy;
    /**更新时间*/
    private Date updateTime;
    /**更新人*/
    private Long updateBy;
    /**是否为内置业务*/
    private Integer isBuiltIn;
    /**状态*/
    private Integer status;

}
