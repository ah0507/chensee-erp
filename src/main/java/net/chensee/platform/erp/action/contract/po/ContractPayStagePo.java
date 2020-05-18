package net.chensee.platform.erp.action.contract.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;


/**
 * 合同付款阶段实体类
 * @author : shibo
 * @date : 2019/5/17 11:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContractPayStagePo extends BaseInfoPo {

    /**阶段名称*/
    private String name;
    /**付款比例*/
    private Integer rate;
    /**付款金额*/
    private Integer amount;
    /**合同ID*/
    private Long contractId;
    /**订单ID*/
    private Long orderId;
}
