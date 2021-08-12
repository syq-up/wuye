package com.shiyq.wuye.entity.DO;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 */
@Data
@Accessors(chain = true)
public class AccessCar {

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
     * 逻辑删除（0未删除1已删除）
     */
    private String deleted;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 创建管理员id（0系统创建）
     */
    private Integer createBy;

}
