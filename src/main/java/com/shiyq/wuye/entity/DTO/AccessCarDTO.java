package com.shiyq.wuye.entity.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 */
@Data
public class AccessCarDTO {

    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 车牌号
     */
    private String licencePlate;

    /**
     * 车牌是否已登记（0否1是）
     */
    private String registered;

    /**
     * 登记类型 0入 1出
     */
    private String type;

    /**
     * 事由
     */
    private String desc;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 创建管理员
     */
    private String createBy;

}
