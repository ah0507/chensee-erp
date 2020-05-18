package net.chensee.platform.erp.action.contract.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;


/**
 * 合同履约阶段实体类
 * @author : shibo
 * @date : 2019/5/17 10:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContractTrackPo extends BaseInfoPo {

    /**合同ID*/
    private Long contractId;
    /**名称*/
    private String name;
    /**履约责任人*/
    private Long performManId;
    /**履约状态(1已完成、0未完成、2进行中)*/
    private String processStatus;
    /**备注*/
    private String remark;
    /**序号*/
    private Integer index;
}
