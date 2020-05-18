package net.chensee.platform.erp.action.product.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * 产品实体类
 * @author : shibo
 * @date : 2019/5/17 10:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductPo extends BaseInfoPo {

    /**产品名称*/
    @NotBlank(message = "产品名称不可为空")
    private String name;
    /**产品类型*/
    @NotNull(message = "产品类型不可为空")
    private Long typeId;
    /**产品型号*/
    private Long model;
    /**厂家*/
    private String factory;
    /**产品数量*/
    private Integer amounts;
    /**备注*/
    private String remark;
    /**所属部门*/
    @NotNull(message = "所属部门不可为空")
    private Long deptId;
}
