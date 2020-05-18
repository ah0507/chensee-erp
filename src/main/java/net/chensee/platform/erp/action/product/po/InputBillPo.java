package net.chensee.platform.erp.action.product.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;

import java.util.Date;

/**
 * 入库单实体类
 * @author : shibo
 * @date : 2019/6/18 15:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InputBillPo extends BaseInfoPo {

    /**合同ID*/
    private Long contractId;
    /**订单ID*/
    private Long orderId;
    /**入库日期*/
    private Date inputTime;
    /**编号*/
    private String number;
    /**采购人员*/
    private Long purchaserId;
    /**领用部门*/
    private Long purchaserDeptId;
    /**负责人*/
    private Long chargeManId;
    /**合计*/
    private Integer sumAmount;
    /**所属部门ID*/
    private Long deptId;
}
