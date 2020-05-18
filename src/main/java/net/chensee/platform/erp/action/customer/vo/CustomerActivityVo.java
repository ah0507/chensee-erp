package net.chensee.platform.erp.action.customer.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.Folderable;
import net.chensee.base.common.vo.BaseInfoVo;

import java.util.Date;

/**
 * @author : shibo
 * @date : 2019/5/21 9:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerActivityVo extends BaseInfoVo implements Folderable {

    /**活动编号*/
    private String number;
    /**活动主题*/
    private String theme;
    /**客户ID*/
    private Long customerId;
    private String customerName;
    /**活动类型*/
    private String type;
    /**负责人*/
    private Long chargeManId;
    private String chargeManName;
    /**联系人*/
    private Long linkmanId;
    private String linkmanName;
    /**社会阶段*/
    private String socialStage;
    /**联系日期*/
    private Date linkTime;
    /**下次联系日*/
    private Date nextTime;
    /**邮箱*/
    private String mail;
    /**联系电话*/
    private String telephone;
    /**活动内容*/
    private String content;
    /**结果反馈*/
    private String resultInfo;
    /**所属部门ID*/
    private Long deptId;
}
