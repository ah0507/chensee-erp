package net.chensee.platform.erp.action.flow.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.IdPo;

/**
 * @author wh
 * @version 1.0
 * @date 2020/4/2 17:17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BusColumnAndValuePo extends IdPo {

    private String busCode;

    private Long busId;

    private String columnAndValue;
}
