package com.shiyq.wuye.service;

import com.shiyq.wuye.entity.DO.PropertyChargeVisit;

import java.util.List;

/**
 * @author shiyq
 * @create 2021-06-09 18:43
 */
public interface PropertyChargeVisitService {
    int deleteByPrimaryKey(Integer id);

    int insert(PropertyChargeVisit record);

    int insertSelective(PropertyChargeVisit record);

    PropertyChargeVisit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PropertyChargeVisit record);

    int updateByPrimaryKey(PropertyChargeVisit record);

    List<PropertyChargeVisit> selectDataByPage(PropertyChargeVisit propertyChargeVisit, Integer page, Integer limit);

    Integer selectDataCount(PropertyChargeVisit propertyChargeVisit);

    Object selectByItemIdAndHouseNum(Integer itemId, String houseNum);
}
