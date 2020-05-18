package net.chensee.platform.erp.action.flow.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.vo.IdVo;

@Data
@EqualsAndHashCode(callSuper = true)
public class FlowVo extends IdVo {

    /**业务ID*/
    private Long businessId;
    /**流程ID*/
    private String flowId;

}
