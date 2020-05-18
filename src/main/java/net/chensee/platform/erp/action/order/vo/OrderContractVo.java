package net.chensee.platform.erp.action.order.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.vo.BaseInfoVo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author : shibo
 * @date : 2019/5/23 11:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderContractVo extends BaseInfoVo {

    /**产品信息*/
    @Valid
    @NotNull(message = "产品信息不可为空")
    private List<OrderContractProductVo> products;
    /**付款方式信息*/
    @Valid
    @NotNull(message = "付款方式信息不可为空")
    private List<OrderContractPayStageVo> payMethods;
}
