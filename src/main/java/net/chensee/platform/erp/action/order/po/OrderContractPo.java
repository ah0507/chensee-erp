package net.chensee.platform.erp.action.order.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;

/**
 * 订单可生产的合同实体类
 * @author : shibo
 * @date : 2019/5/17 11:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderContractPo extends BaseInfoPo {

    /**订单ID*/
    private Long orderId;
    /**合同类型*/
    private String type;
}
