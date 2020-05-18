package net.chensee.platform.erp.action.product.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;

import java.util.Date;

/**
 * 出库单实体类
 * @author : shibo
 * @date : 2019/6/18 15:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OutputBillPo extends BaseInfoPo {

    /**合同ID*/
    private Long contractId;
    /**订单ID*/
    private Long orderId;
    /**出库日期*/
    private Date outputTime;
    /**编号*/
    private String number;
    /**销售人员*/
    private Long saleManId;
    /**领用人*/
    private Long employeeId;
    /**领用部门*/
    private Long employeeDeptId;
    /**负责人*/
    private Long chargeManId;
    /**合计*/
    private Integer sumAmount;
    /**所属部门ID*/
    private Long deptId;
}
