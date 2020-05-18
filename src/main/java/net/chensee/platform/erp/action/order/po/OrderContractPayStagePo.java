package net.chensee.platform.erp.action.order.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;


/**
 * 订单可生产合同的付款阶段实体类
 * @author : shibo
 * @date : 2019/5/17 14:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderContractPayStagePo extends BaseInfoPo {

    /**阶段名称*/
    private String name;
    /**付款比例*/
    private Integer rate;
    /**付款金额*/
    private Integer amount;
    /**合同ID*/
    private Long orderContractId;
    /**订单ID*/
    private Long orderId;
}
