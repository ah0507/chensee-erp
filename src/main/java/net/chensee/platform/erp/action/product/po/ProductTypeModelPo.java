package net.chensee.platform.erp.action.product.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.IdPo;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductTypeModelPo extends IdPo {

    public static final Integer Status_Able = 0;
    public static final Integer Status_Disable = 1;

    /**产品类型*/
    private Long typeId;
    /**产品型号*/
    @NotBlank(message = "产品型号不可为空")
    private String modelName;
    /**备注*/
    private String remark;

    private Date createTime;
    private Long createBy;
    private Date updateTime;
    private Long updateBy;
    private Integer version;
    private Integer status;
}
