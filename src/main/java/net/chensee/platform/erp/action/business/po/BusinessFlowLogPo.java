package net.chensee.platform.erp.action.business.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.IdPo;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessFlowLogPo extends IdPo {

    /**ID*/
    private Long id;
    /**业务CODE*/
    private String busCode;
    /**业务ID*/
    private Long busId;
    /**流程ID*/
    private String flowId;
    /**创建时间*/
    private Date createTime;
}
