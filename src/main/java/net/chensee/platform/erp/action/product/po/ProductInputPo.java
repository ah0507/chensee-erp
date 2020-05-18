package net.chensee.platform.erp.action.product.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;


/**
 * 产品入库实体类
 * @author : shibo
 * @date : 2019/5/17 10:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductInputPo extends BaseInfoPo {

    /**产品ID*/
    private Long productId;
    /**单位*/
    private String unit;
    /**单价*/
    private Integer price;
    /**产品数量*/
    private Integer amount;
    /**总金额*/
    private Integer total;
    /**备注*/
    private String remark;
    /**入库单ID*/
    private Long inputBillId;
    /**出库状态*/
    private Integer outputStatus;
    /**产品剩余数量*/
    private Integer surplusNumber;
    /**所属部门ID*/
    private Long deptId;
}
