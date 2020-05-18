package net.chensee.platform.erp.action.order.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.vo.BaseInfoVo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author : shibo
 * @date : 2019/5/23 11:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderContractProductVo extends BaseInfoVo {

    /**产品ID*/
    @NotNull(message = "产品ID不可为空")
    private Long productId;
    @NotBlank(message = "产品名称不可为空")
    private String productName;
    /**产品数量*/
    @NotNull(message = "产品数量不可为空")
    @Min(value = 1,message = "产品数量必须为正数")
    private Integer amounts;
    /**合同ID*/
    //@NotNull(message = "合同ID不可为空")
    private Long orderContractId;
}
