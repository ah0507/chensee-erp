package net.chensee.platform.erp.action.indexConfig.po;

import lombok.Data;
import net.chensee.base.common.po.IdPo;

import java.util.Date;

/**
 * @author ah
 * @title: IndexConfigPo
 * @date 2019/12/5 10:25
 */
@Data
public class IndexConfigPo extends IdPo {

    private String title;

    private String className;

    private String icon;

    /**
     * 资源code
     */
    private String code;

    /**
     * 行数
     */
    private Integer pageSize;

    /**
     * 所显示的列
     */
    private String columns;

    /**
     * 首页ID
     */
    private Long indexId;

    private Date createTime;

    private Long createBy;

    private Integer status;
}
