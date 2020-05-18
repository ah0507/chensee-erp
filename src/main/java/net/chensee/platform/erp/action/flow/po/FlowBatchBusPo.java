package net.chensee.platform.erp.action.flow.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.IdPo;

@Data
@EqualsAndHashCode(callSuper = true)
public class FlowBatchBusPo extends IdPo {

    /**业务ID*/
    private String busIds;
    /**流程ID*/
    private String flowId;

}
