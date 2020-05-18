package net.chensee.platform.erp.action.file.po;

import lombok.Data;

import java.util.Date;

/**
 * @author ah
 * @title: FilePo
 * @date 2019/11/11 9:39
 */
@Data
public class FileInfoPo {

    private String id;

    private Long userId;
    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件原名称
     */
    private String originalName;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件摘要
     */
    private String document;

    /**
     * 文件状态（临时 0，永久 1）
     */
    private Integer status;

    /**
     * 文件状态（1 可用，0 已移除）
     */
    private Integer opr;

    private Date createTime;

    private Long createBy;
}
