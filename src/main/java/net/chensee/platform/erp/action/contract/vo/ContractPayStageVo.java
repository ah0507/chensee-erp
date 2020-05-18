package net.chensee.platform.erp.action.contract.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.vo.BaseInfoVo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * @author : shibo
 * @date : 2019/5/23 15:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContractPayStageVo extends BaseInfoVo {

    /**阶段名称*/
    @NotBlank(message = "阶段名称不可为空")
    private String name;
    /**付款比例*/
    @NotNull(message = "付款比例不可为空")
    private Double rate;
    /**付款金额*/
    @NotNull(message = "付款金额不可为空")
    @Min(value = 0,message = "付款金额不可为负数")
    private Double amount;
    /**合同ID*/
    @NotNull(message = "合同ID不可为空")
    private Long contractId;
    /**订单ID*/
    @NotNull(message = "订单ID不可为空")
    private Long orderId;
}
