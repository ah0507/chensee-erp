package net.chensee.platform.erp.action.product.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.vo.BaseInfoVo;

import java.util.Set;

/**
 * @author : shibo
 * @date : 2019/5/21 14:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductTypeVo extends BaseInfoVo {

    /**名称*/
    private String name;
    /**父类型*/
    private Long parentId;
    private String parentName;
    /**单位*/
    private Long unitId;
    private String unitName;
    /**型号*/
    private String model;
    private Set<Integer> modelSet;
    /**型号名称*/
    private String modelName;
    private Set<String> modelNameSet;
    /**备注*/
    private String remark;
}
