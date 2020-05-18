package net.chensee.platform.erp.action.customField.po;

import lombok.Data;
import net.chensee.base.common.po.BaseInfoPo;

/**
 * @author ah
 * @title: 自定义字段配置表
 * @date 2019/12/11 14:58
 */
@Data
public class CustomFieldConfigPo extends BaseInfoPo {

    /**
     * 业务名称
     */
    private String businessName;

    /**
     * 自定义字段名
     */
    private String customField;

    /**
     * 自定义字段的类型
     */
    private String customType;

    /**
     * 描述
     */
    private String desc;

}
