package net.chensee.platform.erp.action.order.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 订单实体类
 * @author : shibo
 * @date : 2019/5/17 11:18
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderPo extends BaseInfoPo {

    /**订单编号*/
    private String number;
    /**名称*/
    private String name;
    /**甲方ID*/
    private Long firstPartyId;
    /**甲方联系人ID*/
    private Long firstPartyManId;
    /**乙方ID*/
    private Long secondPartyId;
    /**乙方联系人ID*/
    private Long secondPartyManId;
    /**新建时间*/
    private Date startTime;
    /**关闭时间*/
    private Date endTime;
    /**订单类型*/
    private String orderType;
    /**订单金额*/
    private Integer amount;
    /**订单状态*/
    private String orderStatus;
    /**备注*/
    private String remark;
    /**方向(1 销售 -1 采购)*/
    private Integer direct;
    /**所属部门ID*/
    private Long deptId;
    /**是否为虚拟订单*/
    private Integer isVirtual;
    /**上传文件ID集合*/
    private String fileIds;
}
