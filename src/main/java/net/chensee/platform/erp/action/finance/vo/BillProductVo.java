package net.chensee.platform.erp.action.finance.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.vo.BaseInfoVo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author : shibo
 * @date : 2019/5/27 11:16
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BillProductVo extends BaseInfoVo {

    /**票据ID*/
    private Long billId;
    /**产品ID*/
    @NotNull(message = "货物及应税劳务服务ID不可为空")
    private Long productId;
    @NotBlank(message = "货物及应税劳务服务名称不可为空")
    private String productName;
    @NotBlank(message = "货物规格型号不可为空")
    private String productModel;
    /**产品单位*/
    @NotBlank(message = "产品单位不可为空")
    private String unit;
    /**产品数量*/
    @NotNull(message = "产品数量不可为空")
    @Min(value = 1,message = "产品数量必须为正数")
    private Integer count;
    /**产品单价*/
    @NotNull(message = "产品单价不可为空")
    @Min(value = 0,message = "产品单价不可为负数")
    private Double price;
    /**金额*/
    @NotNull(message = "金额不可为空")
    @Min(value = 0,message = "金额不可为负数")
    private Double amount;
    /**税率*/
    @NotNull(message = "税率不可为空")
    @Min(value = 0,message = "税率不可为负数")
    private Double taxRate;
    /**税额*/
    @NotNull(message = "税额不可为空")
    @Min(value = 0,message = "税额不可为负数")
    private Double taxAmount;
}
