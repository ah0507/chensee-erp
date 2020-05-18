package net.chensee.platform.erp.action.finance.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;

import java.util.Date;

/**
 * 收款实体类
 * @author : shibo
 * @date : 2019/5/27 16:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CollectPo extends BaseInfoPo {

    /**收款名称*/
    private String name;
    /**收款编号*/
    private String number;
    /**客户ID*/
    private Long customerId;
    /**订单ID*/
    private Long orderId;
    /**收款人ID*/
    private Long payeeId;
    /**合同ID*/
    private Long contractId;
    /**收款日期*/
    private Date collectTime;
    /**收款金额*/
    private Integer amount;
    /**项目负责人ID*/
    private Long chargeManId;
    /**付款方式*/
    private String collectMethod;
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
