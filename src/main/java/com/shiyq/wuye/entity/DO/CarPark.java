package com.shiyq.wuye.entity.DO;

import java.io.Serializable;
import lombok.Data;

/**
 * 停车场表
 */
@Data
public class CarPark implements Serializable {
    /**
    * 自增ID
    */
    private Integer id;

    /**
    * 业主名（如果售出则不可为空）
    */
    private String ownerName;

    /**
    * 业主联系电话
    */
    private String phone;

    /**
     * 业主联系电话
     */
    private String licencePlate;

    /**
    * 车位状态（0未售出，1已售出）
    */
    private String parkState;

    /**
    * 车位类型
    */
    private String carParkType;

    private static final long serialVersionUID = 1L;
}
