package net.chensee.platform.erp.action.busFLowState.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wh
 * @version 1.0
 * @date 2020/3/25 10:06
 */
@Data
@EqualsAndHashCode
public class BusFlowVo {

    /**ID**/
    private Long id;
    /**业务Code**/
    private String busCode;
    /**业务ID**/
    private Long busId;
    /**运行流程ID**/
    private String runFlowId;
    /**流程状态码**/
    private Integer flowState;
}
