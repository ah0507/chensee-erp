package net.chensee.platform.erp.action.product.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.chensee.base.common.Folderable;

import java.util.Date;

/**
 * @author : shibo
 * @date : 2019/5/21 14:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductTypeUnitVo implements Folderable {

    /**ID*/
    private Long id;
    /**名称*/
    private String name;
    /**code*/
    private String code;
    /**类型*/
    /*private Long typeId;
    private String typeName;*/
    /**备注*/
    private String remark;
    /**状态*/
    private Integer status;
    /**文件夹*/
    private Long folderId;
    private String folderName;

    /**创建时间*/
    private Date createTime;
    /**创建人ID*/
    private Long createBy;
    /**更新时间*/
    private Date updateTime;
    /**更新人ID*/
    private Long updateBy;
    /**版本*/
    private Integer version;
}
