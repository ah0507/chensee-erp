package net.chensee.platform.erp.action.flow.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.IdPo;

@Data
@EqualsAndHashCode(callSuper = true)
public class FlowPo extends IdPo {

    /**业务ID*/
    private Long businessId;
    /**流程ID*/
    private String flowId;

}
