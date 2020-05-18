package net.chensee.platform.erp.action.customField.po;

import lombok.Data;
import net.chensee.base.common.po.BaseInfoPo;

/**
 * @author ah
 * @title: 自定义字段值的存储
 * @date 2019/12/11 14:58
 */
@Data
public class CustomFieldValPo extends BaseInfoPo {

    /**
     * 业务数据ID
     */
    private String businessId;

    /**
     * 自定义字段配置表ID
     */
    private String customId;

    /**
     * 自定义字段的值
     */
    private String customValue;

}
