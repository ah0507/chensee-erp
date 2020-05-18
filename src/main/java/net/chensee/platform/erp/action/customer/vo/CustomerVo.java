package net.chensee.platform.erp.action.customer.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.Folderable;
import net.chensee.base.common.vo.BaseInfoVo;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author : shibo
 * @date : 2019/5/21 9:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerVo extends BaseInfoVo implements Folderable {

    /**公司ID*/
    @NotNull(message = "公司ID不可为空")
    private Long enterpriseId;
    /**客户名称*/
    @NotBlank(message = "客户名称不可为空")
    private String name;
    /**公司名称
    @NotBlank(message = "公司名称不可为空")
    private String enterpriseName;*/
    /**客户编号*/
    @NotBlank(message = "客户编号不可为空")
    private String number;
    /**客户类别*/
    private String type;
    /**客户来源*/
    private String resource;
    /**客户状态（是否签约）*/
    private String signStatus;
    /**客户阶段*/
    private String stage;
    /**客户关系*/
    private String relation;
    /**销售负责人(己方)*/
    @NotNull(message = "销售负责人ID不可为空")
    private Long chargeManId;
    @NotBlank(message = "销售负责人名称不可为空")
    private String chargeManName;
    /**备注*/
    private String remark;

    /**客户财务信息ID*/
    private Long financialId;
    /**财务名称*/
    private String financialName;
    /**纳税人识别号*/
    private String financialTaxpayerCode;
    /**地址电话*/
    private String financialAddrPhone;
    /**开户行及账号*/
    private String financialBankAccount;

    /**客户电子邮件*/
    private String enterpriseEmail;

    @NotNull(message = "所属部门ID不可为空")
    /**所属部门ID*/
    private Long deptId;
}
