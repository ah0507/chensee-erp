package net.chensee.platform.erp.action.product.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.vo.BaseInfoVo;
import net.chensee.base.common.vo.IdVo;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductTypeModelVo extends IdVo {

    /**类型*/
    private Long typeId;
    /**型号名称*/
    private String modelName;
    /**备注*/
    private String remark;
}
