package net.chensee.platform.erp.action.product.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.Folderable;
import net.chensee.base.common.vo.BaseInfoVo;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductStockVo extends BaseInfoVo implements Folderable {

    /**产品名称*/
    private String name;
    /**产品类型*/
    private String typeName;
    /**产品型号*/
    private String modelName;
    /**单位名称*/
    private String unitName;
    /**厂家*/
    private String factory;
    /**产品数量*/
    private Integer amounts;
    /**备注*/
    private String remark;

}
