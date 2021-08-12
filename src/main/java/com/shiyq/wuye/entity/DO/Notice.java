package com.shiyq.wuye.entity.DO;

import lombok.Data;

/**
 * 公告表
 */
@Data
public class Notice {
    /**
    * 自增主键
    */
    private Integer id;

    /**
    * 公告标题
    */
    private String title;

    /**
    * 公告内容
    */
    private String content;

    /**
    * 发布时间
    */
    private String createDate;

    /**
     * 公告图
     */
    private String newsImg;

    /**
     * 作者
     */
    private String userName;
}
