package com.shiyq.wuye.entity.DO;

import lombok.Data;

/**
 * 评论表
 */
@Data
public class Comment {
    /**
    * 自增主键
    */
    private Integer id;

    /**
    * 评价人ID
    */
    private Integer userId;

    /**
     * 评价的用户对象
     */
    private User user;

    /**
     * 评价的公告对象
     */
    private Notice notice;

    /**
    * 评价内容
    */
    private String content;

    /**
    * 评价内容
    */
    private String createDate;

    /**
    * 评价的那条公告
    */
    private Integer noticeId;
}
