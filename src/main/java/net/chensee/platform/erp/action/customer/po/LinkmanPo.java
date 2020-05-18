package net.chensee.platform.erp.action.customer.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 客户联系人实体类
 * @author : shibo
 * @date : 2019/5/17 11:06
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LinkmanPo extends BaseInfoPo {

    /**客户ID*/
    private Long customerId;
    /**供应商ID*/
    private Long supplierId;
    /**联系人名称*/
    @NotBlank(message = "联系人名称不可为空")
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
    @NotNull(message = "所属部门ID不可为空")
    /**所属部门ID*/
    private Long deptId;
    /**上传文件ID集合*/
    private String fileIds;
}
