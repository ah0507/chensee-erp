package net.chensee.platform.erp.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;


/**
 * 附件实体类
 * @author : shibo
 * @date : 2019/5/17 10:37
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AttachmentPo extends BaseInfoPo {

    /**附件名称*/
    private String name;
    /**附件状态*/
    private String uploadStatus;
    /**附件路径*/
    private String url;
    /**附件对应业务名称*/
    private String business;
    /**附件对应业务ID*/
    private Long businessId;
}
