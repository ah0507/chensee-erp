package net.chensee.platform.erp.action.contract.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 合同实体类
 * @author : shibo
 * @date : 2019/5/17 10:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContractPo extends BaseInfoPo {

    /**合同编号*/
    private String number;
    /**合同名称*/
    private String name;
    /**订单ID*/
    private Long orderId;
    /**合同金额*/
    private Integer amount;
    /**合同状态*/
    private String approvalStatus;
    /**签约日期*/
    private Date contractTime;
    /**合同类型*/
    private String type;
    /**备注*/
    private String remark;
    /**方向(1 销售 -1 采购)*/
    private Integer direct;
    /**所属部门ID*/
    private Long deptId;
}
