package net.chensee.platform.erp.action.region.Po;

import lombok.Data;

/**
 * @author ah
 * @title: 省市区
 * @date 2019/11/15 10:39
 */
@Data
public class RegionPo {

    private Long id;

    private String name;

    /**
     * 父id
     */
    private Long pid;

    private String sname;

    private Integer level;

    private String cityCode;

    private String yzCode;

    private String merName;

    private Double lat;

    private Double lng;

    private String pinyin;

}
