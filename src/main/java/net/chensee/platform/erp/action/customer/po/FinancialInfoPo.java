package net.chensee.platform.erp.action.customer.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;

/**
 * 客户财务信息实体类
 * @author : shibo
 * @date : 2019/5/17 11:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FinancialInfoPo extends BaseInfoPo {

    /**财务名称*/
    private String name;
    /**纳税人识别号*/
    private String taxpayerCode;
    /**地址电话*/
    private String addrPhone;
    /**开户行及账号*/
    private String bankAccount;
    /**客户ID*/
    private Long customerId;
    /**供应商ID*/
    private Long supplierId;
}
