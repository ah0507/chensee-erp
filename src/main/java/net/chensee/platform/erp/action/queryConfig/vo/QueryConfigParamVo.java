package net.chensee.platform.erp.action.queryConfig.vo;

import lombok.Data;

/**
 * @author ah
 * @title: QueryConfigParamVo
 * @date 2019/11/22 14:40
 */
@Data
public class QueryConfigParamVo {

    private String key;

    private String value;

    /**
     * 查询类型（普通 1，checkbox，radio，模糊字段查询等 2）
     */
    private Integer queryType;

    /**
     * 操作符
     */
    private String opr;
}
