package net.chensee.platform.erp.action.finance.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;

import java.util.Date;

/**
 * 发票实体类
 * @author : shibo
 * @date : 2019/5/27 9:15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BillPo extends BaseInfoPo {

    /**票据编号*/
    private String number;
    /**合同ID*/
    private Long contractId;
    /**甲方ID*/
    private Long firstPartyId;
    /**乙方ID*/
    private Long secondPartyId;
    /**开票日期*/
    private Date billTime;
    /**金额合计*/
    private Integer totalAmount;
    /**税额合计*/
    private Integer totalTaxAmount;
    /**大写价税合计*/
    private String chineseAmount;
    /**小写价税合计*/
    private Integer numeralAmount;
    /**备注*/
    private String remark;
    /**收款人*/
    private Long payeeId;
    /**复核人*/
    private Long approvalId;
    /**开票方*/
    private String billToParty;
    /**方向(1 提供服务 -1 请求服务)*/
    private Integer direct;
    /**所属部门ID*/
    private Long deptId;

    /**订单ID*/
    private Long orderId;
    /**
     * 是否审批
     */
    private Integer isApprove;
}
