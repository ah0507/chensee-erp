package net.chensee.platform.erp.action.supplier.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;

import java.util.Date;

/**
 * 供应商联系人
 * @author : shibo
 * @date : 2019/6/17 17:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SupplierLinkmanPo extends BaseInfoPo {

    /**客户ID*/
    private Long supplierId;
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
    /**联系人状态(在职、离职)*/
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
}
