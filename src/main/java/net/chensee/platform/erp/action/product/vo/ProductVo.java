package net.chensee.platform.erp.action.product.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.Folderable;
import net.chensee.base.common.vo.BaseInfoVo;


/**
 * @author : shibo
 * @date : 2019/5/21 15:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductVo extends BaseInfoVo implements Folderable {

    /**产品名称*/
    private String name;
    /**产品类型*/
    private Long typeId;
    private String typeName;
    /**产品型号*/
    private Long model;
    private String modelName;
    /**厂家*/
    private String factory;
    /**产品数量*/
    private Integer amounts;
    /**单位名称*/
    private String unitName;
    /**备注*/
    private String remark;
    /**所属部门*/
    private Long deptId;
}
