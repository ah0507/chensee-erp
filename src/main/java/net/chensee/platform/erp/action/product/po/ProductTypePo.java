package net.chensee.platform.erp.action.product.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;

import org.hibernate.validator.constraints.NotBlank;


/**
 * 产品类型实体类
 * @author : shibo
 * @date : 2019/5/17 11:03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductTypePo extends BaseInfoPo {

    /**名称*/
    @NotBlank(message = "名称不可为空")
    private String name;
    /**单位*/
    //@NotNull(message = "单位不可为空")
    private Long unitId;
    /**父类型*/
    private Long parentId;
    /**备注*/
    private String remark;
}
