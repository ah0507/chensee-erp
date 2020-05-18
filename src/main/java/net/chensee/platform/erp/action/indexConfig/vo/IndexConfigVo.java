package net.chensee.platform.erp.action.indexConfig.vo;

import lombok.Data;
import net.chensee.base.common.vo.IdVo;

import java.util.Date;
import java.util.List;

/**
 * @author ah
 * @title: IndexConfigParamVo
 * @date 2019/12/5 10:32
 */
@Data
public class IndexConfigVo extends IdVo {

    private String title;

    private Long userId;

    private Long roleId;

    private Long deptId;

    private List<IndexResourceConfig> indexResourceConfigs;

    private Date createTime;

    private Long createBy;

}
