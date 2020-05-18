package net.chensee.platform.erp.action.dispatch.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.Folderable;
import net.chensee.base.common.vo.BaseInfoVo;

import java.util.Date;
import java.util.List;

/**
 * @author : shibo
 * @date : 2019/6/19 10:36
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RequestDispatchVo extends BaseInfoVo implements Folderable {

    /**供应商ID*/
    private Long supplierId;
    private String supplierName;
    private String supplierNumber;
    /**订单*/
    private Long orderId;
    private String orderName;
    /**合同ID*/
    private Long contractId;
    private String contractNumber;
    private String contractName;
    /**联系人*/
    private Long linkmanId;
    private String linkmanName;
    /**主题*/
    private String theme;
    /**销售状态*/
    private String saleStatus;
    /**供应商级别*/
    private String supplierLevel;
    /**评分*/
    private Integer grade;
    /**派工单状态(已派工、未派工)*/
    private String dispatchStatus;
    /**审核人*/
    /*private Long approvalId;
    private String approvalName;*/
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
    /**上传文件ID list*/
    private List<String> fileIdList;
    /**上传文件ID集合*/
    private String fileIds;
    /**所属部门ID*/
    private Long deptId;
}
