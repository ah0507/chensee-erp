package net.chensee.platform.erp.action.customer.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;

/**
 * 客户实体类
 * @author : shibo
 * @date : 2019/5/17 9:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerPo extends BaseInfoPo {

    /**公司ID*/
    private Long enterpriseId;
    /**客户名称*/
    private String name;
    /**客户编号*/
    private String number;
    /**客户类别*/
    private String type;
    /**客户来源*/
    private String resource;
    /**客户状态（是否签约）*/
    private String signStatus;
    /**客户阶段*/
    private String stage;
    /**客户关系*/
    private String relation;
    /**销售负责人(己方)*/
    private Long chargeManId;
    /**备注*/
    private String remark;
    /**所属部门ID*/
    private Long deptId;
}
