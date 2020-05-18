package net.chensee.platform.erp.action.order.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;


/**
 * 订单可生成合同的产品实体类
 * @author : shibo
 * @date : 2019/5/17 14:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderContractProductPo extends BaseInfoPo {

    /**产品ID*/
    private Long productId;
    /**产品数量*/
    private Integer amounts;
    /**合同ID*/
    private Long orderContractId;
    /**订单ID*/
    private Long orderId;
}
