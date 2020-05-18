package net.chensee.platform.erp.action.enterprise.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.Folderable;
import net.chensee.base.common.po.BaseInfoPo;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author : shibo
 * @date : 2019/6/13 16:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EnterprisePo extends BaseInfoPo {

    /**公司编号*/
    @NotBlank(message = "公司编号不可为空")
    private String number;
    /**公司名称*/
    @NotBlank(message = "公司名称不可为空")
    private String name;
    /**上级公司*/
    private Long parentId;
    /**公司等级*/
    private String grade;
    /**备注*/
    private String remark;
    /**省份*/
    private Long provinceId;
    /**市*/
    private Long cityId;
    /**区*/
    private Long areaId;
    /**详细地址*/
    private String detailAddress;
    /**邮箱*/
    @Pattern(regexp = "^([A-Za-z0-9_\\-\\.\\u4e00-\\u9fa5])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,8})$",
            message = "邮箱格式不正确")
    private String mail;
    /**联系电话*/
    @Pattern(regexp = "^1(3|4|5|6|7|8|9)\\d{9}$", message = "联系电话格式不正确")
    private String telephone;
    /**网址*/
    private String website;
    /**邮政编码*/
    @Pattern(regexp = "^\\d{6}$", message = "邮政编码格式不正确")
    private String postalCode;
    /**手机*/
    @Pattern(regexp = "^1(3|4|5|6|7|8|9)\\d{9}$",message = "手机号格式不正确")
    private String mobilePhone;
    /**公司生日*/
    private Date birthday;
}
