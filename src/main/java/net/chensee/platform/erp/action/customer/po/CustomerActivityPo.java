package net.chensee.platform.erp.action.customer.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.po.BaseInfoPo;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 客户活动实体类
 * @author : shibo
 * @date : 2019/5/17 11:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerActivityPo extends BaseInfoPo {

    /**活动编号*/
    @NotBlank(message = "活动编号不可为空")
    private String number;
    /**活动主题*/
    @NotBlank(message = "活动主题不可为空")
    private String theme;
    /**客户ID*/
    @NotNull(message = "客户ID不可为空")
    private Long customerId;
    /**活动类型*/
    private String type;
    /**负责人*/
    @NotNull(message = "负责人ID不可为空")
    private Long chargeManId;
    /**联系人*/
    @NotNull(message = "联系人ID不可为空")
    private Long linkmanId;
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
    @NotNull(message = "所属部门ID不可为空")
    /**所属部门ID*/
    private Long deptId;
}
