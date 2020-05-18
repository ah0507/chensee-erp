package net.chensee.platform.erp.action.contract.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.Folderable;
import net.chensee.base.common.vo.BaseInfoVo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author : shibo
 * @date : 2019/5/23 15:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContractVo extends BaseInfoVo implements Folderable {

    /**合同编号*/
    @NotBlank(message = "合同编号不可为空")
    private String number;
    /**合同名称*/
    @NotBlank(message = "合同名称不可为空")
    private String name;
    /**订单ID*/
    private Long orderId;
    private String orderName;
    private String orderNumber;

    /**对方ID*/
    private Long otherPartyId;
    private String otherPartyName;
    /**对方负责人*/
    @NotNull(message = "对方负责人ID不可为空")
    private Long otherPartyManId;
    @NotBlank(message = "对方负责人名称不可为空")
    private String otherPartyManName;
    private String otherPartyManPhone;
    /**销售人员*/
    @NotNull(message = "销售人员ID不可为空")
    private Long saleManId;
    @NotBlank(message = "销售人员名称不可为空")
    private String saleManName;


    /**合同金额*/
    private Double amount;
    /**合同状态*/
    private String approvalStatus;
    /**签约日期*/
    private Date contractTime;
    /**合同类型*/
    private String type;
    /**备注*/
    private String remark;
    /**方向(1 销售 -1 采购)*/
    @NotNull(message = "direct不可为空")
    private Integer direct;
    @NotNull(message = "所属部门ID不可为空")
    /**所属部门ID*/
    private Long deptId;

    /**产品信息*/
    @Valid
    @NotNull(message = "产品信息不可为空")
    private List<ContractProductVo> products;
    /**付款方式信息*/
    @Valid
    @NotNull(message = "付款方式信息不可为空")
    private List<ContractPayStageVo> payMethods;
    /**履约阶段*/
    @Valid
    @NotNull(message = "履约阶段不可为空")
    private List<ContractTrackVo> steps;
}
