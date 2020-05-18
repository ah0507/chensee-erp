package net.chensee.platform.erp.action.indexConfig.po;

import lombok.Data;
import net.chensee.base.common.po.IdPo;

/**
 * @author ah
 * @title: 用户对应的首页
 * @date 2019/12/5 17:09
 */
@Data
public class UserToIndexPo extends IdPo {

    private Long userId;

    private Long indexId;
}
