package net.chensee.platform.erp.action.finance.vo;

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
 * @date : 2019/5/27 11:16
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BillVo extends BaseInfoVo implements Folderable {

    /**票据编号*/
    @NotBlank(message = "票据编号不可为空")
    private String number;
    /**合同ID*/
    @NotNull(message = "合同ID不可为空")
    private Long contractId;
    @NotBlank(message = "合同编号不可为空")
    private String contractNumber;
    /**甲方ID*/
    @NotNull(message = "客户ID不可为空")
    private Long firstPartyId;
    @NotBlank(message = "客户名称不可为空")
    private String firstPartyName;
    @NotBlank(message = "客户税号不可为空")
    private String firstPartyTaxpayerCode;
    @NotBlank(message = "客户地址电话不可为空")
    private String firstPartyAddrPhone;
    @NotBlank(message = "客户开户行及账号不可为空")
    private String firstPartyBankAccount;
    /**乙方ID*/
    @NotNull(message = "销售方客户ID不可为空")
    private Long secondPartyId;
    @NotBlank(message = "销售方名称不可为空")
    private String secondPartyName;
    @NotBlank(message = "销售方税号不可为空")
    private String secondPartyTaxpayerCode;
    @NotBlank(message = "销售方地址电话不可为空")
    private String secondPartyAddrPhone;
    @NotBlank(message = "销售方开户行及账号不可为空")
    private String secondPartyBankAccount;
    /**开票日期*/
    private Date billTime;
    /**金额合计*/
    @NotNull(message = "金额合计不可为空")
    @Min(value = 0,message = "金额合计不可为负数")
    private Double totalAmount;
    /**税额合计*/
    @NotNull(message = "税额合计不可为空")
    @Min(value = 0,message = "税额合计不可为负数")
    private Double totalTaxAmount;
    /**大写价税合计*/
    @NotNull(message = "大写价税合计不可为空")
    private String chineseAmount;
    /**小写价税合计*/
    @NotNull(message = "小写价税合计不可为空")
    @Min(value = 0,message = "小写价税合计不可为负数")
    private Double numeralAmount;
    /**备注*/
    private String remark;
    /**收款人(收款人)*/
    @NotNull(message = "收款人ID不可为空")
    private Long payeeId;
    @NotBlank(message = "收款人名称不可为空")
    private String payeeName;
    /**复核人*/
    @NotNull(message = "复核人ID不可为空")
    private Long approvalId;
    @NotBlank(message = "复核人名称不可为空")
    private String approvalName;
    /**开票方*/
    @NotBlank(message = "开票方不可为空")
    private String billToParty;
    /**方向(1 提供服务 -1 请求服务)*/
    private Integer direct;
    @NotNull(message = "所属部门ID不可为空")
    /**所属部门ID*/
    private Long deptId;

    /**票据产品*/
    @Valid
    @NotNull(message = "票据产品不可为空")
    private List<BillProductVo> billProductVos;

    /**订单ID*/
    private Long orderId;
    /**
     * 是否审批
     */
    private Integer isApprove;
}
