package net.chensee.platform.erp.action.contract.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.vo.BaseInfoVo;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


/**
 * @author : shibo
 * @date : 2019/5/28 11:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ContractTrackVo extends BaseInfoVo {

    /**名称*/
    //@NotBlank(message = "名称不可为空")
    private String name;
    /**履约责任人*/
    //@NotNull(message = "履约负责人ID不可为空")
    private Long performManId;
    //@NotBlank(message = "履约负责人名称不可为空")
    private String performManName;
    /**履约状态(1已完成、0未完成、2进行中)*/
    //@NotBlank(message = "履约状态不可为空")
    private String processStatus;
    /**备注*/
    private String remark;
}
