package net.chensee.platform.erp.action.queryConfig.po;

import lombok.Data;
import net.chensee.base.common.po.BaseInfoPo;

/**
 * @author ah
 * @title: QueryConfigPo
 * @date 2019/11/22 9:44
 */
@Data
public class QueryConfigPo extends BaseInfoPo {

    /**
     * 唯一key值
     */
    private String customKey;

    /**
     * 数据库查询别名
     */
    private String alias;

    /**
     * 字段的java类型
     */
    private String type;

    /**
     * 字段来自哪个controller路径
     */
    private String path;

    /**
     * 字段的值
     */
    private Object value;

    /**
     * sql操作符
     */
    private String opr;

    /**
     * 查询类型（普通 1，checkbox，radio查询字典等 2，模糊字段查询字典 3）
     */
    private Integer queryType;

}
