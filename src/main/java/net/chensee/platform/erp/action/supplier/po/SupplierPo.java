package net.chensee.platform.erp.action.supplier.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;

/**
 * 供应商实体类
 * @author : shibo
 * @date : 2019/6/17 17:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SupplierPo extends BaseInfoPo {

    /**公司ID*/
    private Long enterpriseId;
    /**供应商编号*/
    private String number;
    /**供应商名称*/
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
    private Long chargeManId;
    /**备注*/
    private String remark;
    /**所属部门ID*/
    private Long deptId;
}
