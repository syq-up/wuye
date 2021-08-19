package com.shiyq.wuye.service;

import com.shiyq.wuye.entity.DO.CarPark;

import java.util.List;

/**
 * @author shiyq
 * @create 2021-06-09 18:40
 */
public interface CarParkService {
    int deleteByPrimaryKey(String id);

    int insert(CarPark record);

    int insertSelective(CarPark record);

    CarPark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarPark record);

    int updateByPrimaryKey(CarPark record);

    List<CarPark> selectDataByPage(CarPark carPark, Integer page, Integer limit);

    Integer selectDataCount(CarPark carPark);
}
