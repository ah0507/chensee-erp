package net.chensee.platform.erp.action.contract.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;

/**
 * 合同产品实体类
 * @author : shibo
 * @date : 2019/5/17 10:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContractProductPo extends BaseInfoPo {

    /**产品ID*/
    private Long productId;
    /**产品数量*/
    private Integer amounts;
    /**合同ID*/
    private Long contractId;
    /**订单ID*/
    private Long orderId;
}
