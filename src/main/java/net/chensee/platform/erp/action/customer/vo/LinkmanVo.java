package net.chensee.platform.erp.action.customer.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.Folderable;
import net.chensee.base.common.vo.BaseInfoVo;

import java.util.Date;
import java.util.List;

/**
 * @author : shibo
 * @date : 2019/5/21 9:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LinkmanVo extends BaseInfoVo implements Folderable {

    /**客户ID*/
    private Long customerId;
    private String customerName;
    /**供应商ID*/
    private Long supplierId;
    private String supplierName;
    /**联系人名称*/
    private String name;
    /**性别*/
    private String sex;
    /**联系人类型*/
    private String type;
    /**负责业务*/
    private String business;
    /**联系人生日*/
    private Date birthday;
    /**联系人状态(当前负责人、曾经负责人)*/
    private String chargeStatus;
    /**联系人部门*/
    private String dept;
    /**联系人称谓*/
    private String appellation;
    /**联系人职位*/
    private String position;
    /**邮箱*/
    private String mail;
    /**联系电话*/
    private String telephone;
    /**邮政编码*/
    private String postalCode;
    /**手机号码*/
    private String mobilePhone;
    /**详细地址*/
    private String detailAddress;
    /**证件类型*/
    private String cardType;
    /**证件号码*/
    private String cardNo;
    /**照片*/
    private String photoUrl;
    /**上传文件ID集合*/
    private String fileIds;
    /**上传文件ID集合*/
    private List<String> fileIdsList;
    /**所属部门*/
    private Long deptId;
}
