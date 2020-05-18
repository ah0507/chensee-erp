package net.chensee.platform.erp.action.product.po;

import lombok.Data;
import net.chensee.base.common.po.BaseInfoPo;

/**
 * @author ah
 * @title: OutputLogPo
 * @date 2019/11/18 11:16
 */
@Data
public class OutputLogPo extends BaseInfoPo {

    /**
     * 出库id
     */
    private Long outputId;
    /**
     * 入库id
     */
    private Long inputId;
    /**
     * 出库前的数量
     */
    private Integer beforeOutputNumber;
    /**
     * 出库后数量
     */
    private Integer afterOutputNumber;
    /**
     * 出库后的数量
     */
    private Integer outputNumber;
}
