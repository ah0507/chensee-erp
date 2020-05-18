package net.chensee.platform.erp.action.order.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.Folderable;
import net.chensee.base.common.vo.BaseInfoVo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author : shibo
 * @date : 2019/5/23 10:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderVo extends BaseInfoVo implements Folderable {

    /**订单编号*/
    @NotBlank(message = "订单编号不可为空")
    private String number;
    /**名称*/
    @NotBlank(message = "名称不可为空")
    private String name;
    /**甲方ID*/
    //@NotNull(message = "甲方ID不可为空")
    private Long firstPartyId;
    //@NotBlank(message = "甲方名称不可为空")
    private String firstPartyName;
    private String firstPartyPhone;
    private String firstPartyAddress;
    /**甲方联系人ID*/
    //@NotNull(message = "甲方联系人ID不可为空")
    private Long firstPartyManId;
    //@NotBlank(message = "甲方联系人名称不可为空")
    private String firstPartyManName;
    /**乙方ID*/
    //@NotNull(message = "乙方ID不可为空")
    private Long secondPartyId;
    //@NotBlank(message = "乙方名称不可为空")
    private String secondPartyName;
    private String secondPartyPhone;
    private String secondPartyAddress;
    /**乙方联系人ID*/
    //@NotNull(message = "乙方联系人ID不可为空")
    private Long secondPartyManId;
    //@NotBlank(message = "乙方联系人名称不可为空")
    private String secondPartyManName;
    /**新建时间*/
    private Date startTime;
    /**关闭时间*/
    private Date endTime;
    /**订单类型*/
    private String orderType;
    /**订单金额*/
    @NotNull(message = "订单金额不可为空")
    @Min(value = 0,message = "订单金额不可为负数")
    private Double amount;
    /**订单状态*/
    private String orderStatus;
    /**备注*/
    private String remark;
    /**方向(1 销售 -1 采购)*/
    private Integer direct;
    @NotNull(message = "所属部门ID不可为空")
    /**所属部门ID*/
    private Long deptId;
    /**是否为虚拟订单*/
    private Integer isVirtual;
    /**上传文件ID list*/
    private List<String> fileIdList;
    /**上传文件ID集合*/
    private String fileIds;

    /**合同信息*/
    @Valid
    @NotNull(message = "合同信息不可为空")
    private List<OrderContractVo> contracts;
}
