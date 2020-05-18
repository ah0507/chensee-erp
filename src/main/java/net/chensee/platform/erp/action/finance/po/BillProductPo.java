package net.chensee.platform.erp.action.finance.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;


/**
 * 发票产品实体类
 * @author : shibo
 * @date : 2019/5/27 9:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BillProductPo extends BaseInfoPo {

    /**票据ID*/
    private Long billId;
    /**产品ID*/
    private Long productId;
    /**产品单位*/
    private String unit;
    /**产品数量*/
    private Integer count;
    /**产品单价*/
    private Integer price;
    /**金额*/
    private Integer amount;
    /**税率*/
    private Integer taxRate;
    /**税额*/
    private Integer taxAmount;
}
