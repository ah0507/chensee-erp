package net.chensee.platform.erp.action.product.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.Folderable;
import net.chensee.base.common.vo.BaseInfoVo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author : shibo
 * @date : 2019/6/18 15:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InputBillVo extends BaseInfoVo implements Folderable {

    /**合同ID*/
    @NotNull(message = "合同ID不可为空")
    private Long contractId;
    @NotNull(message = "合同编号不可为空")
    private String contractNumber;
    /**订单ID*/
    @NotNull(message = "订单ID不可为空")
    private Long orderId;
    /**入库日期*/
    //@NotNull(message = "入库日期不可为空")
    private Date inputTime;
    /**编号*/
    @NotBlank(message = "编号不可为空")
    private String number;
    /**采购人员*/
    @NotNull(message = "采购人员ID不可为空")
    private Long purchaserId;
    @NotBlank(message = "采购人员名称不可为空")
    private String purchaserName;
    /**采购部门*/
    @NotNull(message = "采购部门ID不可为空")
    private Long purchaserDeptId;
    @NotBlank(message = "采购部门名称不可为空")
    private String purchaserDeptName;
    /**负责人*/
    @NotNull(message = "负责人ID不可为空")
    private Long chargeManId;
    @NotBlank(message = "负责人名称不可为空")
    private String chargeManName;
    /**合计*/
    @NotNull(message = "合计不可为空")
    @Min(value = 0,message = "合计不可为负数")
    private Double sumAmount;
    //@NotNull(message = "是否已出库不可为空")
    private Integer alreadyOutput;
    //@NotNull(message = "剩余数量不可为空")
    //@Min(value = 0,message = "剩余数量不可为负数")
    private Long surplusNumber;
    @NotNull(message = "所属部门ID不可为空")
    /**所属部门ID*/
    private Long deptId;

    /**入库商品*/
    @Valid
    @NotNull(message = "入库商品不可为空")
    List<ProductInputVo> inputVos;
}
