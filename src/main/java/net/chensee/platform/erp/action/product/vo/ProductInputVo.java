package net.chensee.platform.erp.action.product.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.Folderable;
import net.chensee.base.common.vo.BaseInfoVo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * @author : shibo
 * @date : 2019/5/24 16:19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductInputVo extends BaseInfoVo implements Folderable {

    /**产品ID*/
    @NotNull(message = "产品ID不可为空")
    private Long productId;
    @NotBlank(message = "产品名称不可为空")
    private String productName;
    @NotNull(message = "产品类型ID不可为空")
    private Integer productTypeId;
    @NotBlank(message = "产品类型名称不可为空")
    private String productTypeName;
    @NotBlank(message = "产品型号不可为空")
    private String productModel;

    /**单位*/
    @NotBlank(message = "单位不可为空")
    private String unit;
    /**单价*/
    @NotNull(message = "单价不可为空")
    @Min(value = 0,message = "单价不可为负数")
    private Double price;
    /**产品数量*/
    @NotNull(message = "产品数量不可为空")
    @Min(value = 1,message = "产品数量必须为正数")
    private Integer amount;
    /**剩余数量*/
    /*@NotNull(message = "剩余数量不可为空")
    @Min(value = 0,message = "剩余数量不可为负数")*/
    private Integer surplusNumber;
    /**总金额*/
    @NotNull(message = "总金额不可为空")
    @Min(value = 0,message = "总金额不可为负数")
    private Double total;
    /**备注*/
    private String remark;
    private Long deptId;
}
