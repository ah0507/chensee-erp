package net.chensee.platform.erp.action.supplier.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.Folderable;
import net.chensee.base.common.vo.BaseInfoVo;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author : shibo
 * @date : 2019/6/18 11:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SupplierVo extends BaseInfoVo implements Folderable {

    /**公司ID*/
    @NotNull(message = "公司ID不可为空")
    private Long enterpriseId;
    /**公司名称
    private String enterpriseName;*/
    /**供应商编号*/
    @NotBlank(message = "供应商编号不可为空")
    private String number;
    /**供应商名称*/
    @NotBlank(message = "供应商名称不可为空")
    private String name;
    /**供应商类别*/
    private String type;
    /**供应商来源*/
    private String resource;
    /**供应商状态（是否签约）*/
    private String signStatus;
    /**供应商阶段*/
    private String stage;
    /**供应商关系*/
    private String relation;
    /**采购负责人(己方)*/
    @NotNull(message = "采购负责人ID不可为空")
    private Long chargeManId;
    @NotBlank(message = "采购负责人名称不可为空")
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
    @NotNull(message = "所属部门ID不可为空")
    /**所属部门ID*/
    private Long deptId;
}
