package net.chensee.platform.erp.action.product.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductTypeUnitPo extends BaseInfoPo {

    /**ID*/
    private Long id;
    /**名称*/
    @NotBlank(message = "名称不可为空")
    private String name;
    /**code*/
    @NotBlank(message = "CODE不可为空")
    private String code;
    /**类型ID*/
    //@NotNull(message = "类型ID不可为空")
    /*private Long typeId;*/
    /**类型名称*/
    //@NotBlank(message = "类型名称不可为空")
    /*private String typeName;*/
    /**备注*/
    private String remark;

}
