package net.chensee.platform.erp.action.indexConfig.po;

import lombok.Data;
import net.chensee.base.common.po.IdPo;

import java.util.Date;

/**
 * @author ah
 * @title: RelatedIndexConfigPo
 * @date 2019/12/5 10:36
 */
@Data
public class IndexInfoPo extends IdPo {

    /**
     * 首页标题
     */
    private String title;

    private Long userId;

    private Long roleId;

    private Long deptId;

    private Date createTime;

    private Long createBy;

    private Integer status;

}
