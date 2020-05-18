package net.chensee.platform.erp.action.finance.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;

import java.util.Date;

/**
 * 付款实体类
 * @author : shibo
 * @date : 2019/6/19 11:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PaymentPo extends BaseInfoPo {

    /**付款名称*/
    private String name;
    /**付款编号*/
    private String number;
    /**供应商ID*/
    private Long supplierId;
    /**订单ID*/
    private Long orderId;
    /**付款人ID*/
    private Long payerId;
    /**合同ID*/
    private Long contractId;
    /**付款日期*/
    private Date paymentTime;
    /**付款金额*/
    private Integer amount;
    /**项目负责人ID*/
    private Long chargeManId;
    /**付款方式*/
    private String paymentMethod;
    /**票据ID*/
    private Long billId;
    /**备注*/
    private String remark;
    /**所属部门ID*/
    private Long deptId;
    /**
     * 是否审批
     */
    private Integer isApprove;
}
