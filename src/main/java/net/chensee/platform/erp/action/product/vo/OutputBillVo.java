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
public class OutputBillVo extends BaseInfoVo implements Folderable {

    /**合同ID*/
    @NotNull(message = "合同ID不可为空")
    private Long contractId;
    @NotBlank(message = "合同编号不可为空")
    private String contractNumber;
    /**订单ID*/
    @NotNull(message = "订单ID不可为空")
    private Long orderId;
    /**出库日期*/
    //@NotNull(message = "出库日期不可为空")
    private Date outputTime;
    /**出库单编号*/
    @NotBlank(message = "出库单编号不可为空")
    private String number;
    /**销售人员*/
    //@NotNull(message = "销售人员ID不可为空")
    private Long saleManId;
    //@NotBlank(message = "销售人员名称不可为空")
    private String saleManName;

    /**领用人*/
    @NotNull(message = "领用人ID不可为空")
    private Long employeeId;
    @NotBlank(message = "领用人名称不可为空")
    private String employeeName;
    /**领用部门*/
    @NotNull(message = "领用部门ID不可为空")
    private Long employeeDeptId;
    @NotBlank(message = "领用部门名称不可为空")
    private String employeeDeptName;
    /**负责人*/
    @NotNull(message = "负责人ID不可为空")
    private Long chargeManId;
    @NotBlank(message = "负责人名称不可为空")
    private String chargeManName;
    /**合计*/
    @NotNull(message = "合计不可为空")
    @Min(value = 0,message = "合计不可为负数")
    private Double sumAmount;
    //@NotBlank(message = "关联入库信息的ID集合不可为空")
    private String inputIds;
    @NotNull(message = "所属部门ID不可为空")
    /**所属部门ID*/
    private Long deptId;

    /**出库商品*/
    @Valid
    @NotNull(message = "出库商品不可为空")
    List<ProductOutputVo> outputVos;
}
