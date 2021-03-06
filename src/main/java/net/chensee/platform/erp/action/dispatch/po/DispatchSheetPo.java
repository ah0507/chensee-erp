package net.chensee.platform.erp.action.dispatch.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 派工单实体类
 * @author : shibo
 * @date : 2019/5/17 10:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DispatchSheetPo extends BaseInfoPo {

    /**客户ID*/
    @NotNull(message = "客户ID不可为空")
    private Long customerId;
    /**订单*/
    @NotNull(message = "订单ID不可为空")
    private Long orderId;
    /**合同ID*/
    @NotNull(message = "合同ID不可为空")
    private Long contractId;
    /**甲方联系人*/
    @NotNull(message = "甲方联系人ID不可为空")
    private Long linkmanId;
    /**主题*/
    private String theme;
    /**销售状态*/
    private String saleStatus;
    /**客户级别*/
    private String customerLevel;
    /**用户打分*/
    private Integer grade;
    /**派工单状态(已派工、未派工)*/
    @NotBlank(message = "派工单状态不可为空")
    private String dispatchStatus;
    /**审核人*/
    /*@NotNull(message = "审核人ID不可为空")
    private Long approvalId;*/
    /**联系电话*/
    private String phone;
    /**项目阶段*/
    private String stage;
    /**服务阶段*/
    private String serviceStage;
    /**派工时间*/
    private Date workTime;
    /**完成时间*/
    private Date finishTime;
    /**需求来源*/
    private String resource;
    /**描述*/
    private String desc;
    /**满意度*/
    private Integer satisfied;
    /**反馈信息*/
    private String resultInfo;
    /**审核状态*/
    /*private String approvalStatus;*/
    /**所属部门ID*/
    @NotNull(message = "所属部门ID不可为空")
    private Long deptId;
    /**上传文件ID集合*/
    private String fileIds;
}
