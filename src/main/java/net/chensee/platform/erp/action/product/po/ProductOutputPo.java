package net.chensee.platform.erp.action.product.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;


/**
 * 产品出库实体类
 * @author : shibo
 * @date : 2019/5/17 10:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductOutputPo extends BaseInfoPo {
    public static final Integer NO_OUTPUT_STATUS = 0;
    public static final Integer SOME_OUTPUT_STATUS = 1;
    public static final Integer ALL_OUTPUT_STATUS = 2;

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
    /**出库单ID*/
    private Long outputBillId;
    /**备注*/
    private String remark;
    /**产品出库策略*/
    private Integer strategy;
    /**所属部门ID*/
    private Long deptId;
}
