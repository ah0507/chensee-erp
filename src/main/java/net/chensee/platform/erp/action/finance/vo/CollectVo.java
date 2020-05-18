package net.chensee.platform.erp.action.finance.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.Folderable;
import net.chensee.base.common.vo.BaseInfoVo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author : shibo
 * @date : 2019/5/27 16:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CollectVo extends BaseInfoVo implements Folderable {

    /**收款名称*/
    @NotBlank(message = "收款名称不可为空")
    private String name;
    /**收款编号*/
    @NotBlank(message = "收款编号不可为空")
    private String number;
    /**客户ID*/
    @NotNull(message = "客户ID不可为空")
    private Long customerId;
    @NotBlank(message = "客户名称不可为空")
    private String customerName;
    /**订单ID*/
    @NotNull(message = "订单ID不可为空")
    private Long orderId;
    @NotBlank(message = "订单名称不可为空")
    private String orderName;
    /**收款人ID*/
    @NotNull(message = "收款人ID不可为空")
    private Long payeeId;
    @NotBlank(message = "收款人名称不可为空")
    private String payeeName;
    /**合同ID*/
    @NotNull(message = "合同ID不可为空")
    private Long contractId;
    @NotBlank(message = "合同编号不可为空")
    private String contractNumber;
    @NotBlank(message = "合同名称不可为空")
    private String contractName;
    /**收款日期*/
    private Date collectTime;
    /**收款金额*/
    @NotNull(message = "收款金额不可为空")
    @Min(value = 0,message = "收款金额不可为负数")
    private Double amount;
    /**项目负责人ID*/
    @NotNull(message = "项目负责人ID不可为空")
    private Long chargeManId;
    @NotBlank(message = "项目负责人名称不可为空")
    private String chargeManName;
    /**付款方式*/
    @NotBlank(message = "付款方式不可为空")
    private String collectMethod;
    /**票据ID*/
    @NotNull(message = "票据ID不可为空")
    private Long billId;
    @NotBlank(message = "票据编号不可为空")
    private String billNumber;
    private Date billTime;
    /**备注*/
    private String remark;
    @NotNull(message = "所属部门ID不可为空")
    /**所属部门ID*/
    private Long deptId;

    /**
     * 是否审批
     */
    private Integer isApprove;
}
