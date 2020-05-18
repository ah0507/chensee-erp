package net.chensee.platform.erp.action.enterprise.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.Folderable;
import net.chensee.base.common.vo.BaseInfoVo;

import java.util.Date;

/**
 * @author : shibo
 * @date : 2019/6/17 17:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EnterpriseVo extends BaseInfoVo implements Folderable {

    /**公司编号*/
    private String number;
    /**公司名称*/
    private String name;
    /**上级公司*/
    private Long parentId;
    private String parentName;

    /**公司阶段*/
    private String grade;
    /**备注*/
    private String remark;
    /**省份*/
    private Long provinceId;
    private String province;
    /**市*/
    private Long cityId;
    private String city;
    /**区*/
    private Long areaId;
    private String area;
    /**详细地址*/
    private String detailAddress;
    /**邮箱*/
    private String mail;
    /**联系电话*/
    private String telephone;
    /**网址*/
    private String website;
    /**邮政编码*/
    private String postalCode;
    /**手机*/
    private String mobilePhone;
    /**公司生日*/
    private Date birthday;
}
