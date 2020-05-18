package net.chensee.platform.erp.action.indexConfig.vo;

import lombok.Data;
import net.chensee.base.common.vo.IdVo;

/**
 * @author ah
 * @title: IndexResouceConfig
 * @date 2019/12/5 11:19
 */
@Data
public class IndexResourceConfig extends IdVo {

    private String title;

    private String className;

    private String icon;

    private String code;

    private Integer pageSize;

    private String columns;
}
